package hl4a.ide.工具;

import hl4a.ide.应用配置信息;
import 间.安卓.工具.提示;
import 间.数据.YAML;
import 间.网络.资源;
import 间.网络.连接;
import 间.工具.字符;

public class 更新 {

    public static 应用设置 设置;

    public synchronized static void 请求() {
        String $地址 = 应用配置信息.更新地址;
        资源 $返回 = new 连接($地址).同步();
        if (!$返回.成功()) return;
        设置 = YAML.解析($返回.文本(), 应用设置. class);
        if (设置 == null)return;
        设置.地址 = 设置.地址.replace("<中心>", 设置.中心);
        设置.应用 = 设置.应用.replace("<中心>", 设置.中心);
    }

    public static boolean 检查() {
        if (设置 == null) {
            请求();
        } else return true;
        return 设置 != null;
    }


}
