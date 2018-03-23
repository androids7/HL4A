package 间.安卓.工具;

import android.media.MediaRecorder;
import java.io.IOException;
import 间.工具.文件;

public class 录音 {
    
    public MediaRecorder 记录器 = new MediaRecorder();
    
    public 录音() {
        记录器.setAudioSource(MediaRecorder.AudioSource.MIC);
        记录器.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        记录器.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        try {
            记录器.prepare();
        } catch (Exception $错误) {}
    }
    
    public void 置输出(String $地址) {
        记录器.setOutputFile(文件.检查地址($地址));
    }
   
    public void 开始() {
        记录器.start();
    }
    
    public void 结束() {
        记录器.stop();
    }
    
}
