package hl4a.app;

import android.net.*;
import android.os.*;
import 间.安卓.组件.*;
import 间.安卓.资源.布局.*;
import 间.安卓.工具.文件;
import 间.安卓.工具.线程;
import hl4a.app.界面.主页;

public class LaunchPad extends 启动界面 {

    @Override
    public void 初始化完成事件() {
        请求权限();
    }

    @Override
    public void 权限回调事件() {
        跳转界面(主页.class);
        结束界面();
    }

}
