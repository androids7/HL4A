package hl4a.ide.布局;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import 间.安卓.工具.环境;
import 间.安卓.工具.链接;
import 间.安卓.视图.按钮;
import 间.安卓.视图.文本视图;
import 间.安卓.资源.布局.布局_基本界面;
import 间.工具.字符;
import 间.接口.调用;
import hl4a.ide.工具.更新;
import 间.安卓.工具.提示;

public class 布局_界面_更新 extends 布局_基本界面 {

    public 文本视图 信息;
    public 按钮 直接;
    public 按钮 加群;
    public 按钮 酷安;

    public 布局_界面_更新(Context $上下文) {
        super($上下文);
        标题.置标题("更新界面");
        底层.置填充("16dp");
        信息 = new 文本视图(底层);
        信息.置文本(字符.读取("#assets/update.txt"));
        直接 = new 按钮(底层);
        直接.置边距("8dp", "4dp", 0, 0);
        直接.置文本("直接下载(可能失效) ~");
        直接.置单击事件(调用.配置(this, "直接"));
        加群 = new 按钮(底层);
        加群.置单击事件(调用.配置(this, "加群"));
        加群.置上下边距("4dp");
        加群.置文本("加群关注最新动态 ~");
        酷安 = new 按钮(底层);
        酷安.置单击事件(调用.异步配置(this, "酷安"));
        酷安.置上下边距("4dp");
        酷安.置文本("到酷安更新(省流量) ~");
    }

    public void 加群() {
        链接.QQ.乐园部();
    }

    public void 酷安() {
        Uri $链接 = Uri.parse("market://details?id=hl4a.ide");
        Intent $意图 = new Intent(Intent.ACTION_VIEW, $链接);
        $意图.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        $意图.setPackage("com.coolapk.market");
        环境.取应用().startActivity($意图);
    }

}
