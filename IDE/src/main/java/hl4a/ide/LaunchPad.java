package hl4a.ide;

import android.net.*;
import android.os.*;
import hl4a.ide.界面.*;
import 间.安卓.组件.*;
import 间.安卓.资源.布局.*;
import 间.安卓.工具.文件;
import 间.安卓.工具.线程;

public class LaunchPad extends 启动界面 {

    @Override
    public void 初始化完成事件() {
        请求权限();
    }

    public void 初始化() {
        Uri $文件 = getIntent().getData();
        if ($文件 != null) {
            跳转界面(主页.class, 文件.取URI路径($文件));
        } else {
            跳转界面(主页.class);
        }
        结束界面();
    }

    @Override
    public void 权限回调事件() {
        new 线程(this, "初始化").启动();
    }

}
