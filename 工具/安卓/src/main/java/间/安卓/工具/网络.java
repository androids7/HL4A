package 间.安卓.工具;

import android.net.wifi.WifiManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import 间.工具.反射;

public class 网络 {

    public static ConnectivityManager 网络管理 = (ConnectivityManager)环境.取应用().getSystemService(Context.CONNECTIVITY_SERVICE);
    private static WifiManager WIFI管理 = (WifiManager)环境.取应用().getSystemService(Context.WIFI_SERVICE);
    private static NetworkInfo WIFI信息 =  网络管理.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    public static boolean 网络可用() {
        return 取WiFi连接状态() || (!取WiFi打开状态() && 取数据打开状态());
    }

    public static boolean 取WiFi打开状态() {
        return WIFI管理.isWifiEnabled();
    }

    public static boolean 取WiFi连接状态() {
        return WIFI信息.isConnected();
    }

    public static boolean 置WIFI打开状态(boolean $状态) {
        return WIFI管理.setWifiEnabled($状态);
    }

    public static boolean 取数据打开状态() {
        return 反射.调用方法(网络管理, "getMobileDataEnabled");
    }

    public static boolean 置数据打开状态(boolean $状态) {
        try {
            反射.调用方法(网络管理, "setMobileDataEnabled", $状态);
            return true;
        } catch (Exception $错误) {}
        return false;
    }

}
