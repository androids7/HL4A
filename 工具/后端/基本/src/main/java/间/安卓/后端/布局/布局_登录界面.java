package 间.安卓.后端.布局;

import 间.安卓.资源.布局.布局_基本界面;
import android.content.Context;
import 间.安卓.视图.按钮;
import 间.安卓.视图.编辑框;

public class 布局_登录界面 extends 布局_基本界面 {
    
    public 编辑框 用户名;
    public 编辑框 密码;
    public 按钮 登录;
    
    public 布局_登录界面(Context $上下文) {
        super($上下文);
        标题.置标题("登录");
        底层.置填充("16dp");
        用户名 = new 编辑框(底层);
        用户名.置默认文本("用户名");
        密码 = new 编辑框(底层);
        密码.置默认文本("密码");
        密码.置上边距("4dp");
        密码.置下边距("16dp");
        登录 = new 按钮(底层);
        登录.置宽度("最大");
        登录.置文本("登录");
    }
    
}
