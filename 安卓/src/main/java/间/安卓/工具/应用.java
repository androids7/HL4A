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
import 间.安卓.插件.应用插件;
import 间.安卓.组件.基本应用;
import 间.安卓.组件.基本界面;
import 间.工具.散列;
import 间.工具.线程;
import 间.工具.错误;
import 间.接口.调用;
import 间.收集.集合;
import 间.工具.字符;
import 间.工具.时间;

public class 应用 {

    private static 集合<Activity> 所有界面 = new 集合<Activity>();

    public static void 新图标(String $名称,Class<?> $启动类,int $图标资源) {
        Application $应用 = 环境.取应用();
        Intent $意图 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        $意图.putExtra("android.intent.extra.shortcut.NAME", $名称);
        $意图.putExtra("duplicate", false);
        Intent $启动 = new Intent("android.intent.action.MAIN");
        $启动.setClassName($应用, $启动类.getName());
        $意图.putExtra("android.intent.extra.shortcut.INTENT", $启动);
        Intent.ShortcutIconResource $资源 = Intent.ShortcutIconResource.fromContext($应用, $图标资源);
        $意图.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", $资源);
        $应用.sendBroadcast($意图);
    }


    public static boolean 有图标(String $名称) {
        boolean $返回 = false;
        ContentResolver cr = 环境.取应用().getContentResolver();
        Uri CONTENT_URI = Uri.parse("content://com.android.launcher.settings/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI, new String[] { "title", "iconResource" }, "title=?", new String[] { $名称 }, null);
        if ((c != null) && (c.getCount() > 0)) {
            $返回 = true;
        } else {
            Uri CONTENT_URI2 = Uri.parse("content://com.android.launcher2.settings/favorites?notify=true");
            Cursor c2 = cr.query(CONTENT_URI2, new String[] { "title", "iconResource" }, "title=?", new String[] { $名称 }, null);
            if ((c2 != null) && (c2.getCount() > 0)) {
                $返回 = true;
            }
        }
        return $返回;
    }

    public static void 初始化界面(Activity $界面) {
        新建界面($界面);
        设置主题($界面);
    }

    private static void 设置主题(Context $上下文) {
        $上下文.setTheme(hl4a.runtime.R.style.Theme_AppCompat_Light_NoActionBar);
    }

    public static void 新建界面(Activity $界面) {
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
        跳转错误($线程,$错误);
        System.exit(0);
    }
    
    public static void 跳转错误(Thread $线程,Exception $错误) {
        字符.保存("%HL4A/错误日志/" + 时间.格式() + ".log", $线程.getClass() + "\n" + 错误.取整个错误($错误));
        Application $应用 = 环境.取应用();
        if ($应用 instanceof 基本应用)
            for (应用插件 $单个 : ((基本应用)$应用).所有插件) {
                $单个.应用出错($线程, $错误);
            }
        Intent $意图 = new Intent(环境.取应用(), ErrorActivity.class);
        $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        $意图.putExtra("错误", "当前应用版本 :" + 应用.取版本名() + "\n" + 错误.取整个错误($错误));
        环境.取应用().startActivity($意图);
    }

    public static void 初始化应用(Application $应用) {
        //System.setOut(new 打印处理(调用.代理(提示.class,"普通")));
        环境.置应用($应用);
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
        主题.置主题颜色(颜色.靛蓝);
        提示.初始化($应用);
        if ($应用 instanceof 基本应用) {
            图片.初始化((基本应用)$应用);
        }
    }

    public static String 取安装包位置() {
        return 取安装包位置(环境.取应用().getPackageName());
    }

    public static String 取安装包位置(String $包名) {
        try {
            PackageManager $PM = 环境.取应用().getPackageManager();
            PackageInfo $INFO = $PM.getPackageInfo($包名, 0);
            return $INFO == null ? null : $INFO.applicationInfo.sourceDir;
        } catch (Exception $错误) {
            return null;
        }

    }

    public static void 启动(String $包名) {
        环境.取应用().startActivity(环境.取应用().getPackageManager().getLaunchIntentForPackage($包名));
    }

    public static String[] 取所有权限() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = 环境.取应用().getPackageManager().getPackageInfo(
                应用.取包名(), PackageManager.GET_PERMISSIONS);
        } catch (Exception $错误) {}
        if (packageInfo != null) {
            return packageInfo.requestedPermissions;
        }
        return null;
    }

    public static 集合 取用户应用() {
        return 取所有应用(false);
    }

    public static 集合 取系统应用() {
        return 取所有应用(true);
    }

    public static 集合 取所有应用() {
        return 取所有应用(null);
    }

    public static 集合 取所有应用(Boolean $类型) {
        集合 $列表 = new 集合();
        PackageManager $PM = 环境.取应用().getPackageManager();  
        List<PackageInfo> $所有 = $PM.getInstalledPackages(0);
        for (PackageInfo $单个 : $所有) {
            if (($单个.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                if (new Boolean(false).equals($类型)) continue;
            } else if (new Boolean(true).equals($类型)) { continue; }
            $列表.添加($单个.packageName);
        }
        return $列表;
    }

    public static String 取包名() {
        return 环境.取应用().getPackageName();
    }

    public static String 取应用名() {
        return 取应用名(取包名());
    }

    public static String 取应用名(String $包名) {
        try {
            return 环境.取应用().getPackageManager().getPackageInfo($包名, 64).applicationInfo.loadLabel(环境.取应用().getPackageManager()).toString();
        } catch (Exception $错误) {}
        return null;
    }

    public static String 取版本名() {
        return 取版本名(取包名());
    }

    public static String 取版本名(String $包名) {
        try {
            return 环境.取应用().getPackageManager().getPackageInfo($包名, 64).versionName;
        } catch (Exception $错误) {}
        return null;
    }

    public static Integer 取版本号() {
        return 取版本号(取包名());
    }

    public static Integer 取版本号(String $包名) {
        try {
            return 环境.取应用().getPackageManager().getPackageInfo($包名, 64).versionCode;
        } catch (Exception $错误) {}
        return null;
    }

    public static String 取MD5签名() {
        return 取MD5签名(取包名());
    }

    public static String 取MD5签名(String $包名) {
        try {
            return 散列.摘要("MD5", 环境.取应用().getPackageManager().getPackageInfo($包名, 64).signatures[0].toByteArray());
        } catch (Exception $错误) {}
        return null;
    }

    public static String 取SHA1签名() {
        return 取SHA1签名(取包名());
    }

    public static String 取SHA1签名(String $包名) {
        try {
            return 散列.摘要("SHA-1", 环境.取应用().getPackageManager().getPackageInfo($包名, 64).signatures[0].toByteArray());
        } catch (Exception $错误) {}
        return null;
    }


    public static Drawable 取图标() {
        return 取图标(取包名());
    }


    public static Drawable 取图标(String $包名) {
        try {
            PackageManager pm = 环境.取应用().getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo($包名, 0);
            return info.loadIcon(pm);
        } catch (Exception $错误) {}
        return null;
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
        WindowManager.LayoutParams params = window.getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setAttributes(params);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void 竖屏(Activity $界面) {
        $界面.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void 横屏(Activity $界面) {
        $界面.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
