package com.avos.avoscloud.upload;

import com.avos.avoscloud.AVErrorUtils;
import com.avos.avoscloud.后端错误;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.LogUtil;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.utils.AVFileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by summer on 13-5-27.
 */
class S3Uploader extends HttpClientUploader {
  private static String DEFAULT_HEADER_CACHE_CONTROL = "Cache-Control";
  private static String DEFAULT_HEADER_CACHE_CONTROL_VALUE = "public, max-age=31536000";

  private volatile Call call;
  private String uploadUrl;
  private int retryTimes = DEFAULT_RETRY_TIMES;

  /**
   * 默认的最小写操作时间，单位为秒
   */
  private static final int DEFAULT_MIN_WRITE_TIMEOUT = 30;

  /**
   * 默认的最大写操作时间，单位为秒
   */
  private static final int DEFAULT_MAX_WRITE_TIMEOUT = 4 * 60;

  /**
   * 默认最小的上传速率，50kb 每秒
   */
  private static final int DEFAULT_MIN_UPLOAD_RATE = 50 * 1024;

  /**
   * 写操作时间
   */
  private static int writeTimeout = 0;

  S3Uploader(AVFile avFile, String uploadUrl, SaveCallback saveCallback, ProgressCallback progressCallback) {
    super(avFile, saveCallback, progressCallback);
    this.uploadUrl = uploadUrl;
  }

  @Override
  public 后端错误 doWork() {
    try {
      byte[] bytes = avFile.getData();

      return executeWithRetry(bytes);
    } catch (Exception e) {
      return new 后端错误(e.getCause());
    }
  }

  private 后端错误 executeWithRetry(byte[] data){
    if (null != data && data.length > 0) {
      OkHttpClient.Builder okhttpBuilder = getOKHttpClient().newBuilder();

      int timeout = (writeTimeout > 0 ? writeTimeout : getWriteTimeoutByLength(data.length));
      okhttpBuilder.writeTimeout(timeout, TimeUnit.SECONDS);

      final OkHttpClient httpClient = okhttpBuilder.build();

      Response response = null;
      String serverResponse = null;
      try{
        // decide file mimetype.
        String mimeType = AVFileUtil.getFileMimeType(avFile);

        // upload to s3
        Request.Builder builder = new Request.Builder();
        builder.url(uploadUrl);
        // ================================================================================
        // setup multi part
        // ================================================================================

        Charset charset = Charset.forName("UTF-8");
        // support file for future

        RequestBody requestBody = RequestBody.create(MediaType.parse(mimeType), data);

        builder.put(requestBody);
        builder.addHeader("Content-Type", mimeType);
        if (!FileUploader.UPLOAD_HEADERS.containsKey(DEFAULT_HEADER_CACHE_CONTROL)) {
          builder.addHeader(DEFAULT_HEADER_CACHE_CONTROL, DEFAULT_HEADER_CACHE_CONTROL_VALUE);
        }
        for(String key: FileUploader.UPLOAD_HEADERS.keySet()) {
          builder.addHeader(key, FileUploader.UPLOAD_HEADERS.get(key));
        }

        // Send it
        call = httpClient.newCall(builder.build());

        response = call.execute();
        // The 204 status code implies no response is needed
        if (2 != (response.code() / 100)) {
          serverResponse = AVUtils.stringFromBytes(response.body().bytes());
          LogUtil.avlog.e(serverResponse);
          if(retryTimes>0){
            retryTimes -- ;
            executeWithRetry(data);
          }else {
            return AVErrorUtils.createException(后端错误.OTHER_CAUSE, "upload file failure:" + response.code());
          }
        }
      }catch (IOException exception){
        if(retryTimes >0){
          retryTimes -- ;
          return executeWithRetry(data);
        }else {
          return new 后端错误(exception.getCause());
        }
      }
    }
    return null;
  }

  /**
   * 根据文件长度获取写操作超时时间，默认按照 50kb 的速率计算
   * 最小不超过 30s，最大不超过 240s
   * @param dataLength
   * @return
   */
  private int getWriteTimeoutByLength(int dataLength) {
    int writeSecond = dataLength / DEFAULT_MIN_UPLOAD_RATE;
    if (writeSecond < DEFAULT_MIN_WRITE_TIMEOUT) {
      writeSecond = DEFAULT_MIN_WRITE_TIMEOUT;
    } else if (writeSecond > DEFAULT_MAX_WRITE_TIMEOUT) {
      writeSecond = DEFAULT_MAX_WRITE_TIMEOUT;
    }
    return writeSecond;
  }

  /**
   * Sets the write timeout for s3
   * @param seconds
   * @throws AVException
   */
  public static void setWriteTimeout(int seconds) throws 后端错误 {
    if (seconds <= 0) {
      throw new 后端错误(new IllegalArgumentException("Timeout too small"));
    }
    if (seconds > 60 * 60) {
      throw new 后端错误(new IllegalArgumentException("Timeout too large"));
    }
    writeTimeout = seconds;
  }
}
