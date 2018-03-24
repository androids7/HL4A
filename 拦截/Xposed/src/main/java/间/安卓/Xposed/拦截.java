package 间.安卓.Xposed;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import 间.接口.方法;
import 间.接口.调用;
import de.robv.android.xposed.XposedHelpers;

public class 拦截 {
    
    public static void 钩子方法(Class<?> $类,String $方法,方法 $调用后) {
        XposedBridge.hookAllMethods($类, $方法,new 方法钩子(null,$调用后));
    }
    
    public static void 钩子方法(Class<?> $类,String $方法,方法 $调用前,方法 $调用后) {
        XposedBridge.hookAllMethods($类, $方法,new 方法钩子($调用前,$调用后));
    }

    public static void 替换方法(Class<?> $类,String $方法,方法 $替换) {
        XposedBridge.hookAllMethods($类, $方法,new 方法替换($替换));
    }
    
    public static class 方法钩子 extends XC_MethodHook {

        public 方法 调用前;
        public 方法 调用后;

        public 方法钩子(方法 $调用前,方法 $调用后) {
            调用前 = $调用前;
            调用后 = $调用后;
        }

        @Override
        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam $参数) throws Throwable {
            super.beforeHookedMethod($参数);
            调用.事件(调用前,$参数);
        }

        @Override
        protected void afterHookedMethod(XC_MethodHook.MethodHookParam $参数) throws Throwable {
            super.afterHookedMethod($参数);
            调用.事件(调用后,$参数);
        }

    }

    public static class 方法替换 extends XC_MethodReplacement {

        public 方法 替换;

        public 方法替换(方法 $替换) {
            替换 = $替换;
        }

        @Override
        protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam $参数) throws Throwable {
            return 调用.事件(替换,$参数);
        }

    }
    
    
}
