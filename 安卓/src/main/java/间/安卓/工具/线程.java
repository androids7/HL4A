package 间.安卓.工具;

import android.os.*;
import 间.接口.*;

public class 线程 extends 间.工具.线程 {
    
    public Looper L;
    
    public 线程(方法 $方法) {
        super($方法);
        
    }

    public 线程(Object $对象,String $方法名) {
        super($对象,$方法名);
    }

    @Override
    public void run() {
        Looper.prepare();
        L = Looper.myLooper();
        super.run();
        Looper.loop();
    }
    
    public void 处理(方法 $方法,Object... $参数) {
        处理.指定(L,$方法,$参数);
    }
    
    public static boolean 是主线程() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
    
}
