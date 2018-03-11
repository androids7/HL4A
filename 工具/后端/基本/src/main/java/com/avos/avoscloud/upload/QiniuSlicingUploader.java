package com.avos.avoscloud.upload;

import com.avos.avoscloud.AVErrorUtils;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.LogUtil;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;

import java.io.InputStream;

import java.util.List;
import java.util.ArrayList;

/**
 * Use one thread to upload file to qiniu, slicing with 4MB chunk.
 *
 * Created by fengjunwen on 2017/8/14.
 */

class QiniuSlicingUploader extends HttpClientUploader {
  private final String token;
  private final String fileKey;
  private FileUploader.ProgressCalculator progressCalculator;
  private int uploadChunkSize = QiniuAccessor.WIFI_CHUNK_SIZE;
  private QiniuAccessor qiniuAccessor;

  QiniuSlicingUploader(AVFile avFile, String token, String fileKey, SaveCallback saveCallback, ProgressCallback progressCallback) {
    super(avFile, saveCallback, progressCallback);
    this.token = token;
    this.fileKey = fileKey;
    this.qiniuAccessor = new QiniuAccessor(getOKHttpClient(), token, fileKey);
  }

  @Override
  public AVException doWork() {
    boolean isWifi = AVUtils.isWifi(AVOSCloud.applicationContext);
    if (!isWifi) {
      // 从七牛的接口来看block size为4M不可变，但是chunkSize是可以调整的
      uploadChunkSize = QiniuAccessor.NONWIFI_CHUNK_SIZE;
    }
    InputStream is = null;
    byte buf[] = new byte[uploadChunkSize];
    int fileSize = this.avFile.getSize();
    int blockCount = (fileSize / QiniuAccessor.BLOCK_SIZE) + (fileSize % QiniuAccessor.BLOCK_SIZE > 0 ? 1 : 0);
    List<String> uploadFileCtxs = new ArrayList<String>(blockCount);

    progressCalculator = new FileUploader.ProgressCalculator(blockCount, new FileUploader.FileUploadProgressCallback() {
      public void onProgress(int progress) {
        publishProgress(progress);
      }
    });

    try {
      is = this.avFile.getDataStream();
      // loop for read, upload block to qiniu.
      for (int i = 0; i< blockCount; i++) {
        int currentBlockSize = QiniuAccessor.BLOCK_SIZE;
        int currentBlockOffset = i * QiniuAccessor.BLOCK_SIZE;
        if (i == blockCount - 1) {
          // last block.
          currentBlockSize = fileSize - currentBlockOffset;
        }
        int chunkCount = currentBlockSize / uploadChunkSize + (currentBlockSize % uploadChunkSize > 0? 1 : 0);
        QiniuAccessor.QiniuBlockResponseData lastResponse = null;
        for (int j = 0; j < chunkCount; j++) {
          int currentChunkOffset = j * uploadChunkSize;
          int currentChunkSize = (j == chunkCount -1)? (currentBlockSize - currentChunkOffset): uploadChunkSize;

          // read BLOCK_SIZE content to buf until reach out block size or end-of-file.
          int totalReadCnt = 0;
          int curReadCnt = is.read(buf, totalReadCnt, currentChunkSize - totalReadCnt);
          totalReadCnt += curReadCnt;
          while (curReadCnt > 0 && totalReadCnt < currentChunkSize) {
            curReadCnt = is.read(buf, totalReadCnt, currentChunkSize - totalReadCnt);
            totalReadCnt += curReadCnt;
          }

          if (j == 0) {
            // 1.创建一个block,并且会上传第一个block的第一个chunk的数据
            lastResponse = this.qiniuAccessor.createBlockInQiniu(currentBlockSize, currentChunkSize, buf, DEFAULT_RETRY_TIMES);
          } else {
            // 2.分片上传
            lastResponse = this.qiniuAccessor.putFileBlocksToQiniu(lastResponse, currentBlockOffset, buf, currentChunkSize, DEFAULT_RETRY_TIMES);
          }
        }

        if (null != lastResponse){
          uploadFileCtxs.add(lastResponse.ctx);
          progressCalculator.publishProgress(i, 100);
        } else {
          // error.
          // FIXME: 2017/8/14 603 is hardcode.
          return new AVException(603, "failed to upload file to qiniu.");
        }
      }
      QiniuAccessor.QiniuMKFileResponseData finalResponse = this.qiniuAccessor.makeFile(fileSize, uploadFileCtxs, DEFAULT_RETRY_TIMES);
      if (finalResponse == null || !finalResponse.key.equals(fileKey)) {
        return AVErrorUtils.createException(AVException.OTHER_CAUSE, "upload file failure");
      }
    } catch (Exception ex) {
      return new AVException(ex);
    } finally {
      try {
        if (null != is) {
          is.close();
        }
      } catch (Exception e) {
        ;
      }
    }

    return null;
  }
}
