package 间.安卓.视图;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.github.barteksc.pdfviewer.PDFView;
import 间.安卓.视图.实现.基本视图;
import 间.安卓.视图.实现.视图实现;
import 间.接口.方法;
import 间.安卓.工具.文件;

public class PDF视图 extends PDFView implements 基本视图 {
    
    public PDF视图(Context $上下文) {
        super($上下文);
    }
    
    public void 置PDF(String $地址) {
        fromFile(文件.取文件对象($地址)).defaultPage(1).load();
    }
    
    public void 可滑动(boolean $状态) {
        setSwipeEnabled($状态);
    }
    
    public int 取页数() {
        return getPageCount();
    }
    
    public int 取当前页数() {
        return getCurrentPage();
    }
    
    public void 置当前页数(int $页) {
        jumpTo($页);
    }
    
    
    @Override
    public void 加入到(ViewGroup $布局) {
        视图实现.加入到(this, $布局);
    }

    @Override
    public void 打开(Activity $界面) {
        视图实现.打开(this, $界面);
    }

    @Override
    public void 置标签(Object $标签) {
        视图实现.置标签(this, $标签);
    }

    @Override
    public Object 取标签() {
        return 视图实现.取标签(this);
    }


    @Override
    public void 置单击事件(方法 $事件) {
        视图实现.置单击事件(this, $事件);
    }

    @Override
    public void 置长按事件(方法 $事件) {
        视图实现.置长按事件(this, $事件);
    }

    @Override
    public void 置触摸事件(方法 $事件) {
        视图实现.置触摸事件(this, $事件);
    }

    @Override
    public void 置宽度(Object $宽度) {
        视图实现.置宽度(this, $宽度);
    }

    @Override
    public void 置高度(Object $高度) {
        视图实现.置高度(this, $高度);
    }

    @Override
    public void 置状态(String $状态) {
        视图实现.置状态(this, $状态);
    }

    @Override
    public String 取状态() {
        return 视图实现.取状态(this);
    }

    @Override
    public void 显示() {
        视图实现.显示(this);
    }

    @Override
    public void 占位() {
        视图实现.占位(this);
    }

    @Override
    public void 隐藏() {
        视图实现.隐藏(this);
    }

    @Override
    public void 置边距(Object $边距) {
        视图实现.置边距(this, $边距);
    }

    @Override
    public void 置边距(Object $上,Object $下,Object $左,Object $右) {
        视图实现.置边距(this, $上, $下, $左, $右);
    }

    @Override
    public void 置上边距(Object $边距) {
        视图实现.置上边距(this, $边距);
    }

    @Override
    public void 置下边距(Object $边距) {
        视图实现.置下边距(this, $边距);
    }

    @Override
    public void 置左边距(Object $边距) {
        视图实现.置左边距(this, $边距);
    }

    @Override
    public void 置右边距(Object $边距) {
        视图实现.置右边距(this, $边距);
    }

    @Override
    public void 置填充(Object $填充) {
        视图实现.置填充(this, $填充);
    }

    @Override
    public void 置填充(Object $上,Object $下,Object $左,Object $右) {
        视图实现.置填充(this, $上, $下, $左, $右);
    }

    @Override
    public void 置上填充(Object $填充) {
        视图实现.置上填充(this, $填充);
    }

    @Override
    public void 置下填充(Object $填充) {
        视图实现.置下填充(this, $填充);
    }

    @Override
    public void 置左填充(Object $填充) {
        视图实现.置左填充(this, $填充);
    }

    @Override
    public void 置右填充(Object $填充) {
        视图实现.置右填充(this, $填充);
    }

    @Override
    public void 置背景(Object $背景) {
        视图实现.置背景(this, $背景);
    }

    @Override
    public void 置背景颜色(Object $颜色) {
        视图实现.置背景颜色(this, $颜色);
    }

    @Override
    public void 置上下边距(Object $边距) {
        视图实现.置上下边距(this,$边距);
    }

    @Override
    public void 置左右边距(Object $边距) {
        视图实现.置左右边距(this,$边距);
    }

    @Override
    public void 置上下填充(Object $填充) {
        视图实现.置上下填充(this,$填充);
    }

    @Override
    public void 置左右填充(Object $填充) {
        视图实现.置左右填充(this,$填充);
    }

    @Override
    public void 置阴影(Object $阴影) {
        视图实现.置阴影(this, $阴影);
    }

    @Override
    public void 置布局重力(String $重力) {
        视图实现.置布局重力(this, $重力);
    }

    @Override
    public void 置布局权重(float $权重) {
        视图实现.置布局权重(this, $权重);
    }
    
}
