package 间.安卓.后端.界面;

import android.os.Bundle;
import com.avos.avoscloud.后端错误;
import 间.安卓.后端.LeanCloud;
import 间.安卓.后端.布局.布局_登录界面;
import 间.安卓.后端.用户;
import 间.安卓.工具.提示;
import 间.安卓.工具.线程;
import 间.安卓.弹窗.进度弹窗;
import 间.安卓.组件.基本界面;
import 间.安卓.组件.界面;
import 间.工具.字符;
import 间.接口.返回值;
import 间.接口.调用;
import android.content.Intent;

public class 登录界面 extends 界面 {

    private 布局_登录界面 布局;

    @Override
    public void 界面创建事件(Bundle $恢复) {
        置滑动返回(true);
        置返回值(返回码_失败);
        打开布局(new 布局_登录界面(此));
        布局 = 取视图();
        布局.标题.返回按钮(此);
        布局.登录.置单击事件(调用.配置(this,"登录"));
        布局.注册.置单击事件(调用.配置(this,"注册"));
        进度 = new 进度弹窗(此);
        进度.置可关闭(false);
    }

    private 进度弹窗 进度;

    public void 注册() {
        跳转界面(请求码_注册用户,注册界面.class);
    }

    @Override
    public void 界面回调事件(int $请求码,int $返回码,Intent $意图) {
        if ($请求码 == 请求码_注册用户 || $返回码 == 返回码_成功) {
            结束界面();
        }
    }
    
    public void 登录() {
        String $用户名 = 布局.用户名.取文本();
        String $密码 = 布局.密码.取文本();
        if (字符.是空白($用户名)) {
            布局.用户名.setError("不能为空");
            提示.警告("用户名不能为空 ~");
            return;
        } else if (字符.是空白($密码)) {
            布局.密码.setError("不能为空");
            提示.警告("密码不能为空 ~");
            return;
        }
        进度.更新("正在登录");
        进度.显示();
        new 线程(this, "线程登录").启动($用户名, $密码);
    }

    public void 线程登录(String $用户名,String $密码) {
        返回值<用户> $返回值 = 用户.同步登录($用户名, $密码);
        if ($返回值.成功()) {
            进度.隐藏();
            置返回值(返回码_成功);
            结束界面();
            提示.普通("登录成功 ~");
        } else {
            进度.隐藏();
            提示.警告(((后端错误)$返回值.取错误()).取错误信息());
        }
    }


}
