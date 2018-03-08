package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.图片视图;
import 间.安卓.视图.线性布局;
import 间.安卓.资源.布局.布局_适配器_数组;

public class 布局_适配器_工程 extends 布局_适配器_数组 {

    public 图片视图 图片;
    public 线性布局 背景;
    
    public 布局_适配器_工程(Context $上下文) {
        super($上下文);
        背景 = new 线性布局(this);
        背景.置宽度("56dp");
        背景.置高度("56dp");
        背景.置重力("中间");
        图片 = new 图片视图(背景);
        图片.置宽度("36dp");
        图片.置高度("36dp");
    }
}
