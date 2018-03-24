package hl4a.app.界面;

import android.app.Application;
import android.os.Bundle;
import hl4a.app.布局.布局_主页;
import 间.安卓.后端.用户;
import 间.安卓.后端.界面.登录界面;
import 间.安卓.工具.提示;
import 间.安卓.组件.界面;
import 间.工具.反射;
import 间.接口.调用;
import 间.安卓.工具.文件;
import 间.安卓.工具.图片;
import 间.接口.返回值;
import android.graphics.Bitmap;
import 间.安卓.视图.图片视图;
import 间.安卓.资源.图标;
import 间.安卓.弹窗.基本弹窗;

public class 主页 extends 界面 {

    private 布局_主页 布局;

    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_主页(此));

        布局 = 取视图();

        布局.登录按钮.置单击事件(调用.配置(this, "登录"));

        检查用户();

    }

    private void 检查用户() {
        if (用户.取当前用户() == null) {
            布局.注册底层.显示();
            布局.用户底层.隐藏();
        } else {
            布局.用户底层.显示();
            布局.注册底层.隐藏();
            布局.用户名.置文本(用户.取当前用户().取用户名());
        }
    }

    public void 登录() {
        跳转界面(登录界面.class);
    }

    @Override
    public void 界面刷新事件() {
        检查用户();
    }

}
