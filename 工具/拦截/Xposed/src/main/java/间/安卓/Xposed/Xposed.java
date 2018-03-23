package 间.安卓.Xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import 间.安卓.工具.文件;
import 间.安卓.工具.提示;
import android.app.Application;
import 间.接口.调用;
import 间.安卓.工具.应用;
import 间.安卓.工具.环境;
import 间.接口.方法;

public class Xposed implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam $参数) throws Throwable {
        拦截.钩子方法(Application.class, "onCreate", new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    应用.初始化应用(环境.取应用());
                    主程序();
                    return null;
                }
            });
    }

    public void 主程序() {
        
    }

}
