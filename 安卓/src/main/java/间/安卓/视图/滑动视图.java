package 间.安卓.视图;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import 间.安卓.工具.视图;
import 间.安卓.视图.事件.界面滑动;
import 间.安卓.视图.实现.基本布局;
import 间.安卓.视图.实现.布局实现;
import 间.安卓.视图.实现.视图实现;
import 间.工具.反射;
import 间.接口.方法;
import android.support.v4.widget.EdgeEffectCompat;
import 间.安卓.工具.设备;

public class 滑动视图 extends ViewPager implements 基本布局 {

    public 滑动视图(Context $上下文) {
        super($上下文);
        视图实现.初始化控件(this);
        置宽度("最大");
        置高度("最大");
        变色("mLeftEdge", "mRightEdge");
    }

    public 滑动视图(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }

    private void 变色(String... $变量) {
        if (设备.取SDK() > 21) {
            int $颜色 = 视图.检查颜色("控件");
            for (String $单个 : $变量) {
                EdgeEffectCompat $兼容 = 反射.取变量(this, $单个);
                EdgeEffect $设置 = 反射.取变量($兼容, "mEdgeEffect");
                $设置.setColor($颜色);
            }
        }
    }


    public void 置界面(int $界面) {
        setCurrentItem($界面);
    }

    public void 取界面() {
        getCurrentItem();
    }

    public void 置滑动事件(方法 $进度,方法 $改变,方法 $状态) {
        setOnPageChangeListener(new 界面滑动($进度, $改变, $状态));
    }

    public void 置适配器(PagerAdapter $适配器) {
        setAdapter($适配器);
    }

    PointF downPoint = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下时候的坐标
                downPoint.x = evt.getX();
                downPoint.y = evt.getY();
                if (this.getChildCount() > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (this.getChildCount() > 1) { //有内容，多于1个时
                    // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                // 在up时判断是否按下和松手的坐标为一个点
                if (PointF.length(evt.getX() - downPoint.x, evt.getY()
                                  - downPoint.y) < (float) 5.0) {
                    onSingleTouch(this);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(evt);
    }

    public void onSingleTouch(View v) {
        if (onSingleTouchListener != null) {
            onSingleTouchListener.onSingleTouch(v);
        }
    }

    public interface OnSingleTouchListener {
        public void onSingleTouch(View v);
    }

    public void setOnSingleTouchListener(
        OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }



    @Override
    public void 加入子元素(View $子元素) {
        布局实现.加入子元素(this, $子元素);
    }

    @Override
    public void 置布局重力(String $重力) {
        视图实现.置布局重力(this, $重力);
    }

    @Override
    public void 置布局权重(float $权重) {
        视图实现.置布局权重(this, $权重);
    }

    @Override
    public View 取子元素(Object $标签) {
        return 布局实现.取子元素(this, $标签);
    }

    @Override
    public View 取子元素(int $键值) {
        return 布局实现.取子元素(this, $键值);
    }

    @Override
    public View[] 取所有子元素() {
        return 布局实现.取所有子元素(this);
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
        视图实现.置上下边距(this, $边距);
    }

    @Override
    public void 置左右边距(Object $边距) {
        视图实现.置左右边距(this, $边距);
    }

    @Override
    public void 置上下填充(Object $填充) {
        视图实现.置上下填充(this, $填充);
    }

    @Override
    public void 置左右填充(Object $填充) {
        视图实现.置左右填充(this, $填充);
    }

    @Override
    public void 置阴影(Object $阴影) {
        视图实现.置阴影(this, $阴影);
    }

}
