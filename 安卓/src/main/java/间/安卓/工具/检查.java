package 间.安卓.工具;

import 间.工具.反射;

public class 检查 {
    
    public void 禁用Xposed() {
        Class $类 = 反射.取系统类("de.robv.android.xposed.XposedBridge");
        if ($类 != null) {
            反射.置变量($类,"disableHooks",true);
        }
    }
    
    public static boolean 已签名破解() {
        return 反射.取类("cc.binmt.signature.PmsHookApplication") != null;
    }
    
    public static boolean 已Hook() {
        return 应用.已安装("de.robv.android.xposed.installer") || 应用.已安装("com.saurik.substrate");
    }
    
}
