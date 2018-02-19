package 间.安卓.插件;

import android.app.Activity;
import android.app.Application;
import 间.安卓.组件.基本应用;
import 间.安卓.组件.基本界面;

public class 应用插件 {

    public 基本应用 应用;
    
    public void 注册(基本应用 $应用) {
        $应用.注册插件(this);
    }
    
    public void 初始化() {}
    public void 界面新建(基本界面 $界面) {}
    public void 界面结束(基本界面 $结束) {}
    public void 应用出错(Thread $线程,Exception $错误) {}
    public void 应用事件(String $事件,Object[] $参数) {}

}
