package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.图片视图;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.线性布局;
import 间.安卓.资源.布局.布局_滚动界面;
import 间.安卓.工具.主题;
import android.app.Activity;
import android.view.View;

public class 布局_界面_发现 extends 布局_滚动界面 {

    public 线性布局 内容;
    public 图片视图 图片;
    public 文本视图 名称;
    public 文本视图 简介;

    public 布局_界面_发现(Context $上下文) {
        super($上下文);
        标题.置标题("应用详情");
        内容 = new 线性布局(底层);
        内容.置填充("默认填充");
        内容.置方向("水平");
        图片 = new 图片视图(内容);
        图片.置边距("4dp");
        图片.置宽度("56dp");
        图片.置高度("56dp");
        内容.置重力("中间垂直");
        名称 = new 文本视图(内容);
        名称.置左边距("默认填充");
        简介 = new 文本视图(底层);
        简介.置左边距("20dp");
        简介.置上边距("8dp");
    }

}
