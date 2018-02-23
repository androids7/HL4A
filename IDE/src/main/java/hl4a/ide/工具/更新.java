package hl4a.ide.工具;

import hl4a.ide.应用配置信息;
import java.util.Map;
import 间.安卓.工具.应用;
import 间.安卓.网络.请求;
import 间.安卓.网络.资源;
import 间.收集.哈希表;
import 间.数据.YAML;

public class 更新 {
    
    public static 应用设置 设置;
    
    public static void 请求() {
        String $地址 = 应用配置信息.更新地址;
        资源 $返回 = new 请求($地址).同步();
        if ($返回 == null) return;
        if (!$返回.是否成功()) return;
        设置 = YAML.解析($返回.取文本(),应用设置. class);
        if (设置 == null)return;
        设置.地址 = 设置.地址.replace("<中心>",设置.中心);
        设置.应用 = 设置.应用.replace("<中心>",设置.中心);
    }

}
