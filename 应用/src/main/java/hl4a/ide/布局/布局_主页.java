package hl4a.ide.布局;

import 间.安卓.资源.布局.布局_基本界面;
import android.content.Context;
import 间.安卓.视图.扩展.高级滑动;
import 间.安卓.视图.线性布局;
import 间.安卓.视图.卡片视图;
import 间.安卓.工具.绘画;
import 间.安卓.视图.按钮;
import 间.安卓.工具.颜色;
import 间.安卓.视图.复选框;
import 间.安卓.视图.开关视图;

public class 布局_主页 extends 布局_基本界面 {
    
    public 高级滑动 滑动;
    public 线性布局 我的;
    
    public 线性布局 卡片;
    
    public 布局_主页(Context $上下文) {
        super($上下文);
        标题.置阴影(0);
        滑动 = new 高级滑动(底层);
        
        我的 = new 线性布局($上下文);
        我的.置填充("默认填充");

        卡片 = new 线性布局(我的);
        卡片.置背景颜色(0xffffff);
        卡片.置高度("156dp");
        卡片.置阴影("2dp");
        
        new 开关视图(卡片);
        
        滑动.添加("我的",我的);
    }
    
}
