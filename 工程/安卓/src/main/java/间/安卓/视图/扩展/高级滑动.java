package 间.安卓.视图.扩展;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import 间.安卓.工具.主题;
import 间.安卓.工具.设备;
import 间.安卓.工具.颜色;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.滑动视图;
import 间.安卓.视图.线性布局;
import 间.安卓.视图.适配器.界面适配器;
import 间.接口.调用;
import 间.安卓.工具.转换;

public class 高级滑动 extends 线性布局 {

    public 滑动视图 滑动;
    public 线性布局 指示;
    public 界面适配器 界面;
    public 线性布局 控制;

    private int 数量 = 0;
    private int 宽度 = 0;

    public 高级滑动(Context $上下文) {
        super($上下文);
        线性布局 $控制底层 = new 线性布局(this);
        $控制底层.置阴影("4dp");
        $控制底层.置高度("45dp");
        控制 = new 线性布局($控制底层);
        控制.置高度("42dp");
        控制.置方向("水平");
        $控制底层.置背景("基本");
        线性布局 $指示底层 = new 线性布局($控制底层);
        指示 = new 线性布局($指示底层);
        指示.置背景(颜色.白色);
        指示.置布局权重(1);
        线性布局 $填充 = new 线性布局($指示底层);
        $填充.置高度("1dp");
        滑动 = new 滑动视图(this);
        界面 = new 界面适配器();
        滑动.置适配器(界面);
        滑动.置滑动事件(调用.代理(this,"调整指示"),null,null);
    }

    public 高级滑动(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }

    public void 添加(String $名称,View $视图) {
        final Integer $当前 = 数量;
        数量 ++;
        界面.添加($名称,$视图);
        线性布局 $按钮 = new 线性布局(控制);
        $按钮.置背景("透明");
        $按钮.置重力("中间");
        $按钮.置布局权重(1);
        宽度 = 设备.取屏幕宽度() / 数量;
        指示.置宽度(宽度);
        $按钮.置单击事件(调用.配置(滑动,"置界面",$当前));
        $按钮.置长按事件(调用.配置(滑动,"置界面",$当前));
        文本视图 $文本 = new 文本视图($按钮);
        $文本.置文本颜色(颜色.白色);
        $文本.置文本($名称);
    }

    public void 调整指示(Integer $界面,Float $位置,Integer $) {
        指示.置左边距((宽度*$界面)+(宽度*$位置));
    }

}
