package 间.数据;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import 间.工具.文件;
import 间.工具.流;

public class PROP {

    private static final JavaPropsMapper PROP转换 = new JavaPropsMapper();

    public static Properties 读取PRO(String $地址) {
        try {
            Properties $设置 = new Properties();
            InputStream $输入流 = 流.输入.文件($地址);
            $设置.load($输入流);
            流.关闭($输入流);
            return $设置;
        } catch (Exception $错误) {
        }
        return new Properties();
    }

    public static Properties 读取XML(String $地址) {
        try {
            Properties $设置 = new Properties();
            InputStream $输入流 = 流.输入.文件($地址);
            $设置.loadFromXML($输入流);
            流.关闭($输入流);
            return $设置;
        } catch (Exception $错误) {
        }
        return new Properties();
    }

    public static void 保存(String $地址, Properties $设置) {
        try {
            OutputStream $输出流 = 流.输出.文件($地址);
            $设置.store($输出流, "");
            流.关闭($输出流);
        } catch (Exception $错误) {
        }
    }

    public static void 保存XML(String $地址, Properties $设置) {
        try {
            OutputStream $输出流 = 流.输出.文件($地址);
            $设置.storeToXML($输出流, "");
            流.关闭($输出流);
        } catch (Exception $错误) {
        }
    }
    
    public static void 转换(Properties $设置,Object $对象) {
        if ($对象 == null) return;
        try { 
            PROP转换.writeValue($设置,$对象);
        } catch (IOException $错误) {}
    }
    
    public static Properties 转换(Object $对象) {
        if ($对象 == null) return null;
        try { 
            return PROP转换.writeValueAsProperties($对象);
        } catch (IOException $错误) {}
        return null;
    }

    public static Object 解析(Properties $设置,Class<?> $类) {
        if ($设置 == null || $类 == null) return null;
        try {
            return PROP转换.readPropertiesAs($设置,$类);
        } catch (IOException $错误) {}
        return null;
    }
    
    public static Object 读取(String $地址,Class<?> $类) {
        if (!文件.是文件($地址)) return null;
        return 解析(读取PRO($地址), $类);
    }
    
    public static Object 读取XML(String $地址,Class<?> $类) {
        if (!文件.是文件($地址)) return null;
        return 解析(读取XML($地址), $类);
    }

    public static void 保存(String $地址,Object $对象) {
        保存($地址,转换($对象));
    }
    
    public static void 保存XML(String $地址,Object $对象) {
        保存($地址,转换($对象));
    }

}
