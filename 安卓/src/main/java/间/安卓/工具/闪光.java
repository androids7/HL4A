package 间.安卓.工具;

import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;

public class 闪光 {
    
    
   /*
   <uses-permission android:name="android.permission.FLASHLIGHT"/>
   <uses-permission android:name="android.permission.CAMERA"/>
   */
    
    private Camera 摄像;
    private Handler 处理 = new Handler();

    private static final int 自动关闭时间 = 3 * 60 * 1000;

    public boolean 开启() {
        if (摄像 == null) {
            摄像 = Camera.open();
            摄像.startPreview();
            Camera.Parameters $设置 = 摄像.getParameters();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
                $设置.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            } else {
                $设置.set("flash-mode", "torch");
            }
            摄像.setParameters($设置);
            处理.removeCallbacksAndMessages(null);
            处理.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        关闭();
                    }
                }, 自动关闭时间);
        }
        return true;
    }

    public boolean 关闭() {
        if (摄像 != null) {
            处理.removeCallbacksAndMessages(null);
            Camera.Parameters parameter = 摄像.getParameters();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
                parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            } else {
                parameter.set("flash-mode", "off");
            }
            摄像.setParameters(parameter);
            摄像.stopPreview();
            摄像.release();
            摄像 = null;
        }
        return true;
    }
    
}
