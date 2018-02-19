package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.扩展.高级滑动;
import 间.安卓.资源.布局.布局_基本界面;

public class 布局_主页_底层 extends 布局_基本界面 {

    public 高级滑动 滑动;
    public 布局_主页_主页 主页;
    public 布局_主页_发现 发现;

    public 布局_主页_底层(Context $上下文) {
        super($上下文);
        标题.置标题("乐园之土");
        标题.置阴影(0);
        主页 = new 布局_主页_主页($上下文);
        发现 = new 布局_主页_发现($上下文);
        滑动 = new 高级滑动(底层);
        滑动.添加("应用", 主页);
        滑动.添加("发现", 发现);
    }

}
