package 间.安卓.资源.布局;

import android.content.Context;
import 间.安卓.工具.主题;
import 间.安卓.工具.应用;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.线性布局;
import 间.安卓.视图.进度圈;
import 间.安卓.视图.进度条;

public class 布局_初始化 extends 布局_基本界面 {

    public 布局_初始化(Context $上下文) {

        super($上下文);

        标题.置标题(应用.取信息().应用名 + " - 初始化");

        底层.置填充("填充");
        底层.置重力("下边");

        进度条 $进度 = new 进度条(底层);
        $进度.置自动(true);
        $进度.置标签("进度");
        //$进度.置宽度("最大");
        $进度.置自动(true);
        //$进度.置填充("4dp");

    }

}
