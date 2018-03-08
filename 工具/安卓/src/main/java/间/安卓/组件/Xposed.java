package 间.安卓.组件;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import 间.工具.字符;
import de.robv.android.xposed.XposedBridge;
import 间.安卓.工具.环境;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XC_MethodHook;
import 间.接口.方法;
import 间.接口.调用;
import android.app.Application;
import 间.安卓.工具.应用;
import 间.安卓.工具.提示;
import 间.安卓.工具.拦截;
import android.app.Activity;

public class Xposed implements IXposedHookLoadPackage {
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam $参数) throws Throwable {
        拦截.钩子方法(Application.class,"onCreate",null,调用.代理(this,"主程序"));
    }
    
    public void 主程序(XC_MethodHook.MethodHookParam $参数) {
        应用.初始化应用((Application)$参数.thisObject);
    }

    
}
