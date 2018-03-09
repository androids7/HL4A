package hl4a.ide.工具;

import hl4a.ide.应用配置信息;
import 间.安卓.工具.提示;
import 间.安卓.工具.应用;
import 间.数据.YAML;
import 间.网络.资源;
import 间.网络.连接;
import 间.工具.字符;
import 间.安卓.工具.环境;
import hl4a.ide.界面.更新界面;
import 间.安卓.工具.设置;
import 间.安卓.工具.网络;
import 间.安卓.组件.基本应用;

public class 更新 {

    public static 应用设置 信息;

    public static void 请求() {
        if (!网络.网络可用()) return;
        String $地址 = 应用配置信息.更新地址;
        资源 $返回 = new 连接($地址).同步();
        if (!$返回.成功()) return;
        信息 = YAML.解析($返回.文本(), 应用设置. class);
        if (信息 == null)return;
        信息.地址 = 信息.地址.replace("<中心>", 信息.中心);
        信息.应用 = 信息.应用.replace("<中心>", 信息.中心);
    }

    public static boolean 联网() {
        if (!网络.网络可用()) {
            return false;
        }
        if (信息 == null) {
            请求();
        } else return true;
        return 信息 != null;
    }

    public static boolean 检查() {
        Integer $保存 = (Integer)设置.读取("最新版本");
        if ($保存 != null && $保存 > 应用.取应用信息().版本号) {
            应用.结束界面();
            if (环境.取应用() instanceof 基本应用)
            ((基本应用)环境.取应用()).跳转界面(更新界面.class);
            return true;
        }
        if (联网()) {
            if (信息.最新 > 应用.取应用信息().版本号) {
                应用.结束界面();
                设置.保存("最新版本", 信息.最新);
                if (环境.取应用() instanceof 基本应用)
                ((基本应用)环境.取应用()).跳转界面(更新界面.class);
                return true;
            }
        }
        return false;
    }


}
