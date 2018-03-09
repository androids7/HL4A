package hl4a.ide.工具;

import android.graphics.Bitmap;
import hl4a.ide.应用配置信息;
import java.io.File;
import 间.安卓.工具.图片;
import 间.安卓.工具.提示;
import 间.安卓.工具.文件;
import 间.安卓.工具.线程;
import 间.工具.字符;
import 间.数据.YAML;

public class 应用 {

    public 应用信息 信息;
    public String 地址;
    
    private 应用(应用信息 $信息,String $地址) {
        信息 = $信息;
        地址 = $地址;
    }

    public String 取地址(String... $地址) {
        return 地址 + "/" + 字符.分解($地址, "/");
    }

    public String 取源码(String... $地址) {
        return 地址 + "/源码/" + 字符.分解($地址, "/");
    }

    public static 应用 打开(String $包名) {
        String $目录 = 应用配置信息.应用保存 + "/" + $包名;
        File $地址 = 文件.取文件对象($目录 + "/应用.yml");
        应用信息 $信息 = YAML.读取($地址.getPath(), 应用信息.class);
        if ($信息 == null) {
            提示.警告("应用已损坏 开始删除 : " + 文件.取名称($目录));
            new 线程(文件.class, "删除").启动($目录);
            return null;
        }
        return new 应用($信息, $目录);
    }

    public static void 删除(String $包名) {
        String $地址 = 应用配置信息.应用保存 + "/" + $包名;
        if (文件.存在($地址)) {
            文件.删除($地址);
        }
    }

}
