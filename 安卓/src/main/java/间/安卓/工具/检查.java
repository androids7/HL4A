package 间.安卓.工具;
import 间.工具.反射;

public class 检查 {
    
    public static boolean 已签名破解() {
        return 反射.取类("cc.binmt.signature.PmsHookApplication") == null;
    }
    
    public static boolean 检查签名(String $MD5,String $SHA1) {
        return 应用.取MD5签名().equals($MD5) && 应用.取SHA1签名().equals($SHA1);
    }
    
    public static boolean 已Hook() {
        return 应用.已安装("de.robv.android.xposed.installer") || 应用.已安装("com.saurik.substrate");
    }
    
}
