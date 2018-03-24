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
import 间.安卓.工具.二维码;
import 间.安卓.资源.图标;

public class 主页 extends 界面 {

    private 布局_主页 布局;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_主页(此));
        
        布局 = 取视图();
        
        布局.登录按钮.置单击事件(调用.配置(this,"登录"));
        布局.注册按钮.置单击事件(调用.配置(this,"注册"));
        
        检查用户();
        
        图片视图 $图片 = new 图片视图(此);
        $图片.置图片(二维码.创建("苟利国家生死以",1024,"基本","白色"));
        提示.弹窗(此,"qrcode",$图片);
    }
    
    public void 回调(返回值<String> $返回值) {
        if ($返回值.成功()) {
            提示.普通($返回值.取内容());
        }
    }
    
    private void 检查用户() {
        if (用户.取当前用户() == null) {
            布局.注册底层.显示();
        }
    }
    
    public void 登录() {
        跳转界面(登录界面.class);
    }
    
    public void 注册() {
        
    }
    
}
