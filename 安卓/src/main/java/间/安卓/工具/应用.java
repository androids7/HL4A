package 间.安卓.工具;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.Window;
import android.view.WindowManager;
import hl4a.runtime.ErrorActivity;
import java.util.List;
import 间.安卓.工具.应用;
import 间.安卓.插件.应用插件;
import 间.安卓.组件.基本应用;
import 间.工具.ZIP;
import 间.工具.字符;
import 间.工具.散列;
import 间.工具.时间;
import 间.工具.线程;
import 间.工具.错误;
import 间.接口.调用;
import 间.收集.集合;
import android.content.pm.PackageManager.NameNotFoundException;
import 间.收集.哈希表;
import org.json.JSONArray;

public class 应用 {

    private static 集合<Activity> 所有界面 = new 集合<Activity>();
    public static PackageManager 包管理;
    public static 信息 当前;
   
    public static class 信息 {

        public String 应用名;
        public String 包名;
        public int 版本号;
        public String 版本名;
        public String 地址;
        public Drawable 图标;
        public long 更新时间;

    }

    public static boolean 是更新() {
        Long $上次 =  ZIP.取CRC(取信息().地址);
        Long $记录 = (Long)设置.读取("安装包CRC");
        设置.保存("安装包CRC", $上次);
        return $记录 == null || !$上次.equals($记录);
    }

    public static void 初始化界面(Activity $界面) {
        新建界面($界面);
        if (设备.取SDK() >= 21) {
            $界面.setTheme(android.R.style.Theme_Material_Light_NoActionBar);
        } else {
            $界面.setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
        }
    }

    private static void 新建界面(Activity $界面) {
        所有界面.添加($界面);
    }

    public static void 结束界面() {
        for (Activity $单个 : 所有界面) {
            if ($单个 != null)
                $单个.finish();
        }
    }

    public static void 结束脚本() {
        for (Activity $单个 : 所有界面) {
            if ($单个 != null)
                if ($单个.getClass().getSimpleName().equals("ScriptActivity"))
                    $单个.finish();
        }
    }

    public static void 错误处理(Thread $线程,Exception $错误) {
        应用.结束脚本();
        跳转错误($线程, $错误);
        if (!($线程 instanceof 间.工具.线程))
            System.exit(0);
    }

    public static void 跳转错误(Thread $线程,Exception $错误) {
        字符.保存("%HL4A/错误日志/" + 时间.格式() + ".log", $线程.getClass() + "\n" + 错误.取整个错误($错误));
        if (环境.取应用() instanceof 基本应用)
        for (应用插件 $单个 : ((基本应用)环境.取应用()).所有插件) {
            $单个.应用出错($线程, $错误);
        }
        Intent $意图 = new Intent(环境.取应用(), ErrorActivity.class);
        $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        $意图.putExtra("错误", "当前应用版本 :" + 应用.取信息().版本号 + "\n" + 错误.取整个错误($错误));
        环境.取应用().startActivity($意图);
    }

    public static void 初始化应用(Application $应用) {
        包管理 = $应用.getPackageManager();
        //System.setOut(new 打印处理(调用.代理(提示.class,"普通")));
        环境.置应用($应用);
        当前 = 取信息();
        文件.初始化();
        锁屏.初始化();
        线程.置错误处理(调用.代理(应用.class, "错误处理"));
        主题.置圆角大小("3dp");
        主题.置大文本大小("8sp");
        主题.置文本大小("5sp");
        主题.置小文本大小("4sp");
        主题.置默认填充("16dp");
        主题.置中等填充("56dp");
        主题.置默认圆角("2dp");
        主题.置默认阴影("4dp");
        主题.置颜色(颜色.靛蓝);
        提示.初始化($应用);
        图片.初始化($应用);
    }
    
    public static void 启动(String $包名) {
        环境.取应用().startActivity(包管理.getLaunchIntentForPackage($包名));
    }

    public static String[] 取所有权限() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = 环境.取应用().getPackageManager().getPackageInfo(
                环境.取应用().getPackageName(), PackageManager.GET_PERMISSIONS);
        } catch (Exception $错误) {}
        if (packageInfo != null) {
            return packageInfo.requestedPermissions;
        }
        return new String[0];
    }

    private static 哈希表<String,信息> 信息缓存 = new 哈希表<>();

    public static 信息 取信息() {
        return 取信息(环境.取应用().getPackageName());
    }

    public static 信息 取信息(String $包名) {
        if (信息缓存.检查键值($包名)) {
            return 信息缓存.读取($包名);
        }
        try {
            PackageInfo $信息 = 包管理.getPackageInfo($包名, PackageManager.GET_ACTIVITIES);
            if ($信息 == null)return null;
            信息 $返回 = new 信息();
            $返回.地址 = $信息.applicationInfo.sourceDir;
            $返回.包名 = $信息.packageName;
            $返回.应用名 = $信息.applicationInfo.loadLabel(包管理).toString();
            $返回.版本号 = $信息.versionCode;
            $返回.版本名 = $信息.versionName;
            $返回.图标 = $信息.applicationInfo.loadIcon(包管理);
            $返回.更新时间 = $信息.lastUpdateTime;
            信息缓存.设置($包名, $返回);
            return $返回;
        } catch (PackageManager.NameNotFoundException $错误) {}
        return null;
    }


    public static 信息 取安装包信息(String $地址) {
        $地址 = 文件.检查地址($地址);
        PackageInfo $信息 = 包管理.getPackageArchiveInfo($地址, PackageManager.GET_ACTIVITIES);
        if ($信息 == null)return null;
        信息 $返回 = new 信息();
        $返回.地址 = $地址;
        $返回.包名 = $信息.packageName;
        $返回.应用名 = $信息.applicationInfo.loadLabel(包管理).toString();
        $返回.版本号 = $信息.versionCode;
        $返回.版本名 = $信息.versionName;
        $返回.图标 = $信息.applicationInfo.loadIcon(包管理);
        $返回.更新时间 = $信息.lastUpdateTime;
        return $返回;
    }

    public static int 取状态栏高度(Activity $界面) {
        int $ = $界面.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return $界面.getResources().getDimensionPixelSize($);
    }


    public static boolean 在后台() {
        ActivityManager am = (ActivityManager) 环境.取应用()
            .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null
                && !topActivity.getPackageName().equals(
                    环境.取应用().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean 是横屏() {
        return 环境.取应用().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean 已安装(String $包名) {
        PackageManager $PM = 环境.取应用().getPackageManager();
        try {
            $PM.getPackageInfo($包名, 0);
            return true;
        } catch (PackageManager.NameNotFoundException $错误) {
            return false;
        }
    }

    public static void 全屏(Activity $界面) {
        Window window = $界面.getWindow();
        WindowManager.LayoutParams $参数 = window.getAttributes();
        $参数.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setAttributes($参数);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void 竖屏(Activity $界面) {
        $界面.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void 横屏(Activity $界面) {
        $界面.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
