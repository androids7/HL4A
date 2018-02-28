package 间.安卓.工具;

import android.app.WallpaperManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.UUID;
import java.io.IOException;
import android.app.AppOpsManager;

public class 设备 {
    
    public static int 取SDK() {
        return Build.VERSION.SDK_INT;
    }
    
    public static int 取目标SDK() {
        try {
            PackageInfo $信息 = 环境.取应用().getPackageManager().getPackageInfo(
                应用.取应用信息().包名, 0);
            return $信息.applicationInfo.targetSdkVersion;
        } catch (Exception $错误) {}
        return 21;
    }
    
    public static int 取屏幕宽度() {
        DisplayMetrics dm = 环境.取应用().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int 取屏幕高度() {
        DisplayMetrics dm = 环境.取应用().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float 取屏幕密度() {
        DisplayMetrics dm = 环境.取应用().getResources().getDisplayMetrics();
        return dm.density;
    }

    public static int 取屏幕DPI() {
        DisplayMetrics dm = 环境.取应用().getResources().getDisplayMetrics();
        return dm.densityDpi;
    }
    
    public static void 置剪切板(String $内容) {
        ClipboardManager $剪切板 = (ClipboardManager) 环境.取应用().getSystemService(Context.CLIPBOARD_SERVICE);
        $剪切板.setPrimaryClip(ClipData.newPlainText(null, $内容));
    }

    public static String 取剪切板() {
        ClipboardManager $剪切板 = (ClipboardManager) 环境.取应用().getSystemService(Context.CLIPBOARD_SERVICE);
        if ($剪切板.hasPrimaryClip() && $剪切板.getPrimaryClipDescription().hasMimeType(
                ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            ClipData $数据 = $剪切板.getPrimaryClip();
            if ($数据 != null && $数据.getItemCount() > 0) {
                return String.valueOf($数据.getItemAt(0).coerceToText(环境.取应用()));
            }
        }
        return null;
    }
    
    public static String 取UUID() {
        final TelephonyManager tm = (TelephonyManager) 环境.取应用().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(环境.取应用().getContentResolver(), android.provider
                                                                    .Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();

        return uniqueId;
    }

    public static String 取IMEI() {
        TelephonyManager tm = (TelephonyManager) 环境.取应用().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String 取IMSI() {
        TelephonyManager tm = (TelephonyManager) 环境.取应用().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }
    
    public static void 震动(int $毫秒) {
        Vibrator $震动 = (Vibrator) 环境.取应用().getSystemService(Context.VIBRATOR_SERVICE);
        $震动.vibrate($毫秒);
    }
    
    public static boolean 置壁纸(Object $图片) {
        WallpaperManager $管理器 = WallpaperManager.getInstance(环境.取应用());  
        try {
            $管理器.setBitmap(视图.检查图片($图片));
            return true;
        } catch (IOException $错误) {}
        return false;
    }
    
}
