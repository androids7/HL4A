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
import 间.安卓.工具.提示;

public class 高级滑动 extends 线性布局 {

    public 标签视图 标签;
    public 滑动视图 滑动;
    public 界面适配器 界面;
    
    public 高级滑动(Context $上下文) {
        super($上下文);
        标签 = new 标签视图(this);
        标签.置阴影("阴影");
        滑动 = new 滑动视图(this);
        界面 = new 界面适配器();
        滑动.置适配器(界面);
        标签.置滑动视图(滑动);
    }

    public 高级滑动(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }

    public void 添加(String $名称,View $视图) {
        界面.添加($名称,$视图);
        标签.notifyDataSetChanged();
    }

}
