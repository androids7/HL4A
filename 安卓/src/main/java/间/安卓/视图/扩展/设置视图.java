package 间.安卓.视图.扩展;

import android.content.Context;
import android.view.ViewGroup;
import 间.安卓.视图.线性布局;
import 间.安卓.视图.文本视图;
import android.view.View;
import 间.接口.方法;
import 间.安卓.工具.设置;
import 间.安卓.视图.开关视图;
import 间.接口.调用;
import 间.数据.YAML;
import java.io.Serializable;
import 间.工具.反射;
import 间.工具.字符;
import 间.工具.字节;
import 间.安卓.弹窗.基本弹窗;
import 间.安卓.视图.编辑框;
import 间.安卓.工具.颜色;

public class 设置视图 extends 线性布局 {

    public 线性布局 标题布局;
    public 文本视图 标题文本;

    public 设置视图(Context $上下文) {
        super($上下文);
        置背景颜色("白色");
        标题布局 = new 线性布局(this);
        标题布局.置高度("自动");
        标题布局.置填充("16dp");
        标题布局.置重力("中间垂直");
        标题布局.隐藏();
        标题文本 = new 文本视图(标题布局);
        标题文本.置文本颜色("控件");
    }

    public 设置视图(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }
    
    public void 置标题(String $标题) {
        标题布局.显示();
        标题文本.置文本($标题);
    }

    public 开关设置项 开关设置(String $标题,String $副标题,boolean $默认,方法 $回调) {
        开关设置项 $项目 = new 开关设置项($回调,$默认);
        $项目.置标题($标题);
        if ($副标题 != null)
            $项目.置副标题($副标题);
        return $项目;
    }
    
    public 设置项 跳转设置(String $标题,String $副标题,方法 $回调) {
        设置项 $项目 = new 设置项();
        $项目.置标题($标题);
        if ($副标题 != null)
            $项目.置副标题($副标题);
        $项目.置单击事件($回调);
        return $项目;
    }

    @Override
    public void 加入子元素(View $子元素) {
        super.加入子元素($子元素);
        addView(分隔());
    }
    
    public 线性布局 分隔() {
        线性布局 $分隔 = new 线性布局(getContext());
        $分隔.置高度(1);
        $分隔.置背景颜色(颜色.白烟);
        return $分隔;
    }

    public class 开关设置项 extends 设置项<Boolean> {

        public 开关视图 开关;
        public 方法 事件;

        public 开关设置项(方法 $事件,boolean $默认) {
            super();
            事件 = $事件;
            开关 = new 开关视图(this);
            开关.置打开状态($默认);
            开关.setClickable(false);
            开关.置切换事件(调用.代理(this, "切换事件"));
            置单击事件(调用.配置(开关, "performClick"));
        }

        public void 切换事件(开关视图 $开关,boolean $状态) {
            调用.事件(事件,$状态);
        }

    }

    public class 设置项<类型> extends 线性布局 {

        protected 线性布局 左布局;
        protected 文本视图 标题;
        protected 文本视图 副标题;

        public 设置项() {
            super(设置视图.this);
            置高度("自动");
            置方向("水平");
            置背景("透明");
            置重力("中间垂直");
            置填充("填充");
            左布局 = new 线性布局(this);
            左布局.置布局权重(1);
            左布局.置重力("中间垂直");
            标题 = new 文本视图(左布局);
            标题.置文本大小("中文本");
            副标题 = new 文本视图(左布局);
            副标题.置文本大小("小文本");
            副标题.隐藏();
        }

        public String 取标题() {
            return 标题.取文本();
        }

        public void 置标题(String $标题) {
            标题.置文本($标题);
        }

        public String 取副标题() {
            return 副标题.取文本();
        }

        public void 置副标题(String $标题) {
            副标题.置文本($标题);
            副标题.显示();
        }

    }

}
