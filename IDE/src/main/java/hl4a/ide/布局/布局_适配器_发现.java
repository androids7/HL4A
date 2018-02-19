package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.图片视图;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.线性布局;
import 间.接口.调用;
import 间.安卓.视图.卡片视图;
import 间.安卓.工具.设备;
import 间.安卓.工具.主题;
import 间.安卓.工具.转换;
import 间.安卓.工具.应用;
import 间.收集.哈希表;

public class 布局_适配器_发现 extends 线性布局 {

    public 线性布局 底层;
    public 图片视图 图标;
    public 文本视图 名称;
    public String 包名;
    public String 地址;
    
    public 布局_适配器_发现(Context $上下文) {
        super($上下文);
        置高度("56dp");
        底层 = new 线性布局(this);
        底层.置重力("中间垂直");
        底层.置方向("水平");
        new 线性布局(底层).置宽度("默认填充");
        图标 = new 图片视图(底层);
        图标.置宽度("36dp");
        图标.置高度("36dp");
        new 线性布局(底层).置宽度("默认填充");
        名称 = new 文本视图(底层);
        名称.置文本("HL4A应用");
        名称.置文本显示在同一行();
        //名称.setMaxWidth(5);
    }
    
}
