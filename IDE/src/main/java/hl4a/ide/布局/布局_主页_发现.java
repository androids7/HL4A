package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.下拉刷新布局;
import 间.安卓.视图.列表视图;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.线性布局;

public class 布局_主页_发现 extends 线性布局 {

    public 下拉刷新布局 刷新;
    public 列表视图 列表;
    
    public 布局_主页_发现(Context $上下文) {
        super($上下文);
        刷新 = new 下拉刷新布局(this);
        列表 = new 列表视图(刷新);
    }
    
}
