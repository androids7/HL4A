package 间.安卓.工具;

import android.os.Handler;
import android.os.Looper;
import 间.工具.反射;
import 间.工具.回调;
import 间.接口.可运行类;
import 间.接口.方法;
import 间.接口.调用;

public class 处理 {

    public static Looper 主处理 = Looper.getMainLooper();

    public static void 主线程(方法 $方法,Object... $参数) {
        指定(主处理,$方法,$参数);
    }
    
    public static void 主线程(Object $对象,String $方法名,Object... $参数) {
        主线程(配置($对象,$方法名,$参数),$参数);
    }
    
    
    public static void 指定(Looper $处理,方法 $方法,Object... $参数) {
        new Handler($处理).post(new 可运行类($方法, $参数));
    }


    public static 方法 代理(final Object $对象,final String $方法名) {
        return 代理(主处理, $对象, $方法名);
    }

    public static 方法 配置(final Object $对象,final String $方法名,final Object... $指定参数) {
        return 配置(主处理, $对象, $方法名, $指定参数);
    }

    public static 方法 代理(final Looper $处理,final Object $对象,final String $方法名) {
        return new 方法() {
            @Override
            public Object 调用(Object[] $参数) {
                new Handler($处理).post((new 可运行类(调用.配置($对象, $方法名,$参数))));
                return null;
            }
        };
    }

    public static 方法 配置(final Looper $处理,final Object $对象,final String $方法名,final Object... $指定参数) {
        return new 方法() {
            @Override
            public Object 调用(Object[] $参数) {
                new Handler($处理).post(new 可运行类(调用.配置($对象, $方法名,$指定参数)));
                return null;
            }
        };
    }

}
