package 间.安卓.视图.扩展;

import android.content.Context;
import android.view.ViewGroup;
import 间.安卓.工具.主题;
import 间.安卓.工具.应用;
import 间.安卓.工具.颜色;
import 间.安卓.视图.弹出菜单;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.线性布局;
import 间.安卓.资源.图标;
import 间.接口.方法;
import 间.接口.调用;
import android.app.Activity;
import 间.安卓.视图.侧滑布局;
import android.support.v4.view.ViewPager;
import 间.安卓.组件.基本界面;

public class 标题栏 extends 线性布局 {

    public 文本视图 标题对象;
    public 线性布局 左按钮栏;
    public 线性布局 右按钮栏;
    
    public 线性布局 菜单视图;
    public 弹出菜单 菜单对象;
    
    Context 上下文;

    public 标题栏(Context $上下文) {
        super($上下文);
        上下文 = $上下文;
        
        置阴影("4dp");
        
        置标签("标题");
        置方向("水平");
        置高度(主题.取中等填充());
        置重力("中间垂直");
        置背景颜色("基本");

        左按钮栏 = new 线性布局(this);
        左按钮栏.置宽度("自动");

        标题对象 = new 文本视图(this);
        标题对象.置文本(应用.取信息().应用名);
        标题对象.置文本大小(主题.取大文本大小());
        标题对象.置文本颜色(颜色.白色);
        标题对象.置左填充(主题.取默认填充());
        标题对象.setHorizontallyScrolling(true);

        右按钮栏 = new 线性布局(this);
        右按钮栏.置方向("水平");
        右按钮栏.置重力("右边");
        
        菜单视图 = 右按钮(图标.更多,调用.配置(菜单对象,"显示"));
        菜单对象 = new 弹出菜单(菜单视图);
        菜单视图.隐藏();

    }
    
    public 标题栏(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }

    public void 切换() {
        标题对象.置文本颜色(主题.取颜色().取控件色());
        置背景(颜色.白色);
        置阴影(0);
    }

    public String 取标题() {
        return 标题对象.取文本();
    }

    public void 置标题(String $内容) {
        标题对象.置文本($内容);
    }

    public 线性按钮 左按钮(Object $图片,方法 $事件) {
        线性按钮 $按钮 = new 线性按钮(上下文, $图片);
        $按钮.加入到(左按钮栏);
        $按钮.置单击事件($事件);
        return $按钮;
    }

    public 线性按钮 右按钮(Object $图片,方法 $事件) {
        线性按钮 $按钮 = new 线性按钮(上下文, $图片);
        $按钮.加入到(右按钮栏);
        $按钮.置单击事件($事件);
        return $按钮;
    }
    
    public 弹出菜单 取菜单() {
        菜单视图.显示();
        return 菜单对象;
    }
    
    public void 返回按钮(基本界面 $界面) {
        左按钮(图标.返回,调用.配置($界面,"结束界面"));
    }
    
    public void 菜单按钮 (侧滑布局 $侧滑) {
        左按钮(图标.菜单,调用.配置($侧滑,"打开侧滑"));
    }


}
