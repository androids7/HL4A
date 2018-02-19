package hl4a.ide.工具;

import hl4a.ide.应用配置信息;
import java.util.Map;
import 间.安卓.工具.应用;
import 间.安卓.网络.请求;
import 间.安卓.网络.资源;
import 间.收集.哈希表;
import 间.数据.YAML;

public class 更新 {

    public static String 中心;

    public static Integer 最新版本;
    public static String 版本地址;
    public static boolean 版本维护;
    public static String 维护信息;

    public static Integer 最新运行;
    public static String 运行地址;

    public static String 应用地址;

    public static void 请求() {
        String $地址 = 应用配置信息.更新地址;
        资源 $返回 = new 请求($地址).同步();
        if ($返回 == null) return;
        if (!$返回.是否成功()) return;
        哈希表 $内容 = YAML.解析($返回.取文本());
        if ($内容 == null) return;
        中心 = (String)$内容.读取("中心");
        哈希表 $更新 = new 哈希表((Map)$内容.读取("版本"));
        最新版本 = (Integer)$更新.读取("最新");
        版本维护 = $更新.读取("维护");
        维护信息 = (String)$更新.读取("信息");
        版本地址 = ((String)$更新.读取("地址")).replace("<中心>", 中心);
        哈希表 $运行 = new 哈希表((Map)$内容.读取("运行"));
        最新运行 = (Integer)$运行.读取("最新");
        运行地址 = ((String)$运行.读取("地址")).replace("<中心>", 中心);
        应用地址 = ((String)$内容.读取("应用")).replace("<中心>",中心);
    }

    public static boolean 需要安装运行时() {
        return !应用.已安装("hl4a.runtime") || !应用.取MD5签名().equals(应用.取MD5签名("hl4a.runtime"));
    }

    public static boolean 需要更新运行时() {
        if (最新运行 == null) {
            请求();
        }
        if (最新运行 == null) {
            return false;
        }
        if (最新运行.intValue() == 应用.取版本号("hl4a.runtime")) {
            return false;
        } else {
            return true;
        }
    }

    public static String 下载运行时() {
        if (运行地址 == null) {
            请求();
        }
        if (运行地址 == null) return "错误";
        资源 $连接 = new 请求(运行地址).同步();
        if ($连接 == null || !$连接.是否成功()) return "错误";
        String $地址 = "%HL4A/runtime-" + 最新运行 + ".apk";
        $连接.保存到($地址);
        return $地址;
    }

}
