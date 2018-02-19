package 间.安卓.工具;

import android.os.Handler;
import android.os.Looper;
import 间.工具.回调;
import 间.接口.可运行类;
import 间.接口.方法;
import 间.接口.调用;
import 间.工具.断言;

public class 处理 {

    public static Looper 主处理 = Looper.getMainLooper();

    public static void 主线程(方法 $运行,Object... $参数) {
        指定(主处理, $运行, $参数);
    }
    
    public static void 主线程(Object $对象,String $方法,Object... $参数) {
        指定(主处理, 调用.配置($对象,$方法,$参数), $参数);
    }

    public static void 指定(Looper $处理,方法 $方法,Object... $参数) {
        new Handler($处理).post(new 可运行类($方法, $参数));
    }

    public static Object 同步(final 方法<?> $方法,Object... $参数) {
        final 回调<Object> $回调 = new 回调<>();
        指定(主处理, new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    $回调.返回(调用.事件($方法,$参数));
                    return null;
                }
            }, $参数);
        return $回调.等待();
    }


}
