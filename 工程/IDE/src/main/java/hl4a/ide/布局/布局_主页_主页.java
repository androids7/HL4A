package hl4a.ide.布局;

import 间.安卓.视图.线性布局;
import android.content.Context;
import 间.安卓.视图.网格视图;
import 间.安卓.工具.设备;
import 间.安卓.工具.转换;
import 间.安卓.视图.文本视图;

public class 布局_主页_主页 extends 线性布局 {
    
    public 线性布局 底层;
    public 文本视图 提示;
    
    public 线性布局 所有;
    public 网格视图 列表;
    
    public 布局_主页_主页(Context $上下文) {
        super($上下文);
        置填充("默认填充");
        置重力("中间");
        提示 = new 文本视图(this);
        提示.置文本("还没有添加应用 ~");
        提示.隐藏();
        底层 = new 线性布局(this);
        所有 = new 线性布局(底层);
        所有.置高度("自动");
        所有.置下填充("默认填充");
        new 文本视图(所有).置文本("所有应用");
        列表 = new 网格视图(底层);
        列表.置行视图数(2);
        列表.置列边距("默认填充");
    }
    
}
