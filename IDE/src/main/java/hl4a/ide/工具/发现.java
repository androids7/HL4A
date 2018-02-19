package hl4a.ide.工具;

import hl4a.ide.应用配置信息;
import 间.安卓.工具.文件;
import 间.数据.YAML;
import 间.工具.字符;
import 间.工具.字节;
import android.graphics.Bitmap;
import 间.安卓.工具.图片;

public class 发现 {

    private static 应用信息 解析(String... $文件) {
        return YAML.读取(字符.分解($文件, "/"), 应用信息.class);
    }

    public static synchronized void 缓存(应用信息 $信息,byte[] $图片) {
        String $缓存 = 应用配置信息.应用缓存 + "/" + $信息.包名;
        if (文件.是目录($缓存)) {
            应用信息 $原有 = 解析($缓存, $信息.包名, "应用.yml");
            if ($原有 != null && $原有.版本号 >= $信息.版本号) {
                if (!文件.是文件($缓存 + "/图标.png")) {
                    字节.保存($缓存 + "/图标.png", $图片);
                }
                return;
            } else {
                文件.删除($缓存);
            }
        }
        YAML.保存($缓存 + "/应用.yml", $信息);
        字节.保存($缓存 + "/图标.png", $图片);
    }

}
