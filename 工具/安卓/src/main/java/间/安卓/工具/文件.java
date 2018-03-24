package 间.安卓.工具;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import java.io.File;
import 间.安卓.工具.图片;
import 间.安卓.工具.文件;
import 间.安卓.工具.环境;
import 间.安卓.插件.应用插件;
import 间.安卓.插件.界面插件;
import 间.安卓.组件.界面;
import 间.工具.反射;
import 间.工具.字符;
import 间.接口.方法;
import 间.接口.调用;

public class 文件 extends 间.工具.文件 {

    protected static void 初始化() {
        文件.替换地址("%", 取存储目录(""));
        文件.替换地址("$", 取数据目录(""));
        文件.替换地址("#", 取自身目录(""));
    }

    public static class 文件插件 extends 应用插件 {

        @Override
        public void 界面新建(Activity $界面) {
            new 界面插件() {
                @Override
                public boolean 界面回调事件(int $请求码,int $返回码,Intent $意图) {
                    if ($请求码 == 界面.请求码_文件选择) {
                        if ($返回码 == 界面.返回码_成功) {
                            Uri $文件 = $意图.getData();
                            String $地址 = 文件.取Uri路径($文件);
                            调用.事件(文件.回调, 文件.是文件($地址), $地址);
                        } else {
                            调用.事件(文件.回调, false, null);
                        }
                        文件.回调 = null;
                        return true;
                    }
                    return false;
                }
            }.注册($界面);
        }
    }

    public static Uri 取Uri(String $地址) {
        if (设备.取SDK() < 23 || 设备.取目标SDK() < 23) {
            return Uri.fromFile(文件.取文件对象($地址));
        } else {
            return FileProvider.getUriForFile(环境.取应用(), "hl4a.fileprovider", 文件.取文件对象($地址));
        }
    }

    public static String 取Uri路径(Uri $链接) {
        if (null == $链接) return null;
        String $类型 = $链接.getScheme();
        String $返回 = null;
        if ($类型 == null || ContentResolver.SCHEME_FILE.equals($类型)) {
            $返回 = $链接.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals($类型)) {
            String[] $查询 = new String[] {MediaStore.Files.FileColumns.DATA};
            Cursor $内容 = 环境.取应用().getContentResolver().query($链接, $查询, null, null, null);
            if (null != $内容 && $内容.moveToFirst()) {
                int $位置 = $内容.getColumnIndex($查询[0]);
                if ($位置 > -1) {
                    $返回 = $内容.getString($位置);
                }
            }
            $内容.close();
        }
        if ($返回 == null) {
            Object $工厂 = 反射.调用方法(FileProvider.class, "getPathStrategy", 环境.取应用(), $链接.getAuthority());
            File $文件 = 反射.调用($工厂, "getFileForUri", 反射.变参($链接));
            return $文件.getPath();
        }
        return $返回;
    }

    private static 方法 回调;

    public static void 选择(Activity $界面,方法 $回调) {
        回调 = $回调;
        Intent $意图 = new Intent(Intent.ACTION_GET_CONTENT);
        $意图.setType("*/*");
        $意图.addCategory(Intent.CATEGORY_OPENABLE);
        $界面.startActivityForResult($意图, 界面.请求码_文件选择);
    }

    public static void 打开(String $地址) {
        Intent $意图 = new Intent(Intent.ACTION_VIEW);
        $意图.setDataAndType(取Uri($地址), 取MIME($地址));
        $意图.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        环境.取应用().startActivity($意图);
    }

    public static String 取MIME(String $地址) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(取后缀($地址));
    }

    public static String 取存储目录() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String 取存储目录(String... $地址) {
        return 取存储目录() + "/" + 字符.分解($地址, "/");
    }

    public static String 取数据目录() {
        return 环境.取应用().getFilesDir().getParent();
    }

    public static String 取数据目录(String... $地址) {
        return 取数据目录() + "/" + 字符.分解($地址, "/");
    }

    public static String 取缓存目录() {
        return 环境.取应用().getCacheDir().getPath();
    }

    public static String 取缓存目录(String... $地址) {
        return 取数据目录() + "/" + 字符.分解($地址, "/");
    }

    public static String 取自身目录() {
        return 取数据目录("自身");
    }

    public static String 取自身目录(String... $地址) {
        return 取自身目录() + "/" + 字符.分解($地址, "/");
    }

}
