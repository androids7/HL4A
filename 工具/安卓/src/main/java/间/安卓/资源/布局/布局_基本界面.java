package 间.安卓.资源.布局;

import android.content.*;
import android.content.res.*;
import android.graphics.drawable.*;
import 间.安卓.工具.*;
import 间.安卓.视图.*;
import 间.安卓.视图.扩展.*;
import android.view.View;

public class 布局_基本界面 extends 线性布局 {
    
    public 标题栏 标题;
    public 线性布局 底层;
    
    public 布局_基本界面(Context $上下文) {
        super($上下文);
        标题 = new 标题栏($上下文);
        addView(标题);
        标题.置标题(应用.取信息().应用名);
        底层 = new 线性布局($上下文);
        addView(底层);
        底层.置背景颜色(颜色.白烟);
    }
    
    public void 置标题(String $标题) {
        标题.置标题($标题);
    }

    @Override
    public void 加入子元素(View $子元素) {
        底层.加入子元素($子元素);
    }
    
}
