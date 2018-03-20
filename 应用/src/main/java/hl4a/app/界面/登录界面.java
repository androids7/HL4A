package hl4a.app.界面;

import 间.安卓.组件.界面;
import android.os.Bundle;
import hl4a.app.布局.布局_登录界面;

public class 登录界面 extends 界面 {

    private 布局_登录界面 布局;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_登录界面(此));
        布局 = 取视图();
    }
    
}
