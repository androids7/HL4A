package 间.安卓.后端.界面;

import 间.安卓.组件.界面;
import android.os.Bundle;
import 间.安卓.后端.布局.布局_注册界面;

public class 注册界面 extends 界面 {

    private 布局_注册界面 布局;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        置滑动返回(true);
        置返回值(返回码_失败);
        打开布局(new 布局_注册界面(此));
        布局 = 取视图();
        布局.标题.返回按钮(此);
    }
    
}
