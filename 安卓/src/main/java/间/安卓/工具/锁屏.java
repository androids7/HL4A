package 间.安卓.工具;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

public class 锁屏 {

    /*
    <uses-permission android:name="android.permission.WAKE_LOCK"/>
    */
   
    
    private static PowerManager 管理;
    private static PowerManager.WakeLock 锁;

    protected static void 初始化() {
        管理 = (PowerManager) 环境.取应用().getSystemService(Context.POWER_SERVICE);
        锁 = 管理.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, "锁屏");
    }

    public static boolean 是亮屏() {
            return 管理.isScreenOn();
    }

    public static void 亮屏() {
        if (!锁.isHeld()) {
            锁.acquire();
        }
    }

    public static void 灭屏() {
        if (锁.isHeld()) {
            try {
                锁.release();
            } catch (Exception $错误) {}
        }
    }

}
