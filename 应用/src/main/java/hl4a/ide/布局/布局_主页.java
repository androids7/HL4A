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
import 间.安卓.视图.文本视图;

public class 布局_主页 extends 布局_基本界面 {

    public 高级滑动 滑动;
    public 线性布局 我的;

    public 线性布局 翻墙底层;
    public 按钮 翻墙按钮;
    
    public 线性布局 注册底层;
    public 文本视图 注册文本;
    public 线性布局 注册登录;
    public 按钮 注册按钮;
    public 按钮 登录按钮;

    public 布局_主页(Context $上下文) {
        super($上下文);
        标题.置阴影(0);
        滑动 = new 高级滑动(底层);

        翻墙底层 = new 线性布局($上下文);
        翻墙底层.置填充("16dp");
        翻墙底层.置重力("中间");
        
        翻墙按钮 = new 按钮(翻墙底层);
        翻墙按钮.置文本("单击连接");
        
        我的 = new 线性布局($上下文);
        我的.置背景颜色(颜色.白烟);

        注册底层 = new 线性布局(我的);
        注册底层.置背景颜色("白色");
        注册底层.置高度("自动");
        注册底层.置填充("16dp");
        注册底层.置阴影("4dp");
        注册底层.置重力("中间水平");
        注册底层.隐藏();

        注册文本 = new 文本视图(注册底层);
        注册文本.置文本颜色("黑色");
        注册文本.置文本("你还没有登录 ~");

        注册登录 = new 线性布局(注册底层);
        注册登录.置上填充("8dp");
        注册登录.置方向("水平");
        注册登录.置重力("中间");

        注册按钮 = new 按钮(注册登录);
        注册按钮.置文本("注册");

        登录按钮 = new 按钮(注册登录);
        登录按钮.置左边距("4dp");
        登录按钮.置文本("登录");

        滑动.添加("翻墙", 翻墙底层);
        滑动.添加("我的", 我的);
    }

}
