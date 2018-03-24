package 间.安卓.后端.布局;

import android.content.Context;
import 间.安卓.视图.按钮;
import 间.安卓.视图.编辑框;
import 间.安卓.资源.布局.布局_基本界面;

public class 布局_注册界面 extends 布局_基本界面 {

    public 编辑框 用户名;
    public 编辑框 密码;
    public 编辑框 邮箱;
    public 按钮 注册;
    
    public 布局_注册界面(Context $上下文) {
        super($上下文);
        标题.置标题("登录");
        底层.置填充("16dp");
        用户名 = new 编辑框(底层);
        用户名.置默认文本("用户名");
        密码 = new 编辑框(底层);
        密码.置默认文本("密码");
        密码.置上下边距("4dp");
        邮箱 = new 编辑框(底层);
        邮箱.置默认文本("邮箱");
        邮箱.置下边距("16dp");
        注册 = new 按钮(底层);
        注册.置宽度("最大");
        注册.置文本("注册");

    }
    
}
