package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.下拉刷新布局;
import 间.安卓.视图.列表视图;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.线性布局;
import 间.安卓.视图.进度圈;

public class 布局_主页_发现 extends 线性布局 {

    public 列表视图 列表;
    public 进度圈 进度;
    public 文本视图 提示;
    
    public 布局_主页_发现(Context $上下文) {
        super($上下文);
        置重力("中间");
        
        列表 = new 列表视图(this);
        提示 = new 文本视图(this);
        进度 = new 进度圈(this);
    }
    
}
