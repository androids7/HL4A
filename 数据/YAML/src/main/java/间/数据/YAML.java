package 间.数据;

import android.util.Log;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import 间.工具.字符;
import 间.工具.错误;
import 间.收集.哈希表;

public class YAML {

    private static Yaml 解析器 = new Yaml();

    public static String 转换(Object $对象) {
        return 解析器.dumpAsMap($对象);
    }

    public static void 保存(String $地址,Object $对象) {
        字符.保存($地址, 转换($对象));
    }

    public static <类型>类型 解析(String $内容,Class<类型> $类) {
        try {
            return 解析器.loadAs($内容, $类);
        } catch (Exception $错误) {}
        return null;
    }

    public static <类型>类型 读取(String $地址,Class<类型> $类) {
        return 解析(字符.读取($地址), $类);
    }
    
    public static 哈希表 解析(String $内容) {
        try {
            return new 哈希表((Map)解析器.load($内容));
        } catch (Exception $错误) {
        }
        return null;
    }
    
    public static 哈希表 读取(String $地址) {
        return 解析(字符.读取($地址));
    }

}
