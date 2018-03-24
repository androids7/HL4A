package 间.安卓.插件;

import android.app.Activity;
import android.app.Application;
import 间.安卓.组件.基本应用;
import 间.安卓.组件.基本界面;
import android.content.Context;
import 间.安卓.工具.环境;

public class 应用插件 {

    public 基本应用 应用;
    
    public void 注册() {
        Application $应用 = 环境.取应用();
        if ($应用 instanceof 基本应用)
        ((基本应用)$应用).注册插件(this);
    }
    
    public void 初始化() {}
    public void 界面新建(Activity $界面) {}
    public void 界面结束(Activity $结束) {}
    public void 应用出错(Thread $线程,Exception $错误) {}
    public void 应用事件(String $事件,Object[] $参数) {}
    public void 处理环境(Context $环境) {}

}
