package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.扩展.高级滑动;
import 间.安卓.资源.布局.布局_基本界面;
import 间.安卓.视图.列表视图;

public class 布局_主页_底层 extends 布局_基本界面 {

    public 高级滑动 滑动;

    public 列表视图 工程;
    public 布局_主页_主页 应用;

    public 布局_主页_底层(Context $上下文) {
        super($上下文);

        标题.置标题("乐园之土");
        标题.置阴影(0);

        应用 = new 布局_主页_主页($上下文);
        工程 = new 列表视图($上下文);

        滑动 = new 高级滑动(底层);
        滑动.添加("应用", 应用);
        滑动.添加("工程", 工程);
    }

}
