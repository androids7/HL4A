package hl4a.ide.工具;
import 间.数据.YAML;
import 间.工具.字符;
import 间.收集.哈希表;
import 间.收集.无序表;

public class 应用信息 {
 
    public String 工程名 = "HL4A应用";
    public String 包名 = "hl4a.client";
    public int 版本号 = 1;
    public String 版本名 = "1";
    public String 作者 = "MikaGuraN";
    
    public static 应用信息 解析(String $内容) {
        return YAML.解析($内容,应用信息.class);
    }
    
    public static 应用信息 读取(String $地址) {
        return 解析(字符.读取($地址));
    }
    

}
