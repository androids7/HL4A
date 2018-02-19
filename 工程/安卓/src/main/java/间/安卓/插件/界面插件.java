package 间.安卓.插件;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import 间.安卓.组件.基本界面;
import android.view.View;

public class 界面插件 {
    
    public 基本界面 界面;
    
    public void 注册(基本界面 $界面) {
        $界面.注册插件(this);
    }

    public void 请求权限事件() {}
    public void 打开布局事件(View $视图) {}
    public void 界面创建事件(Bundle $恢复) {}
    public void 界面启动事件() {}
    public void 界面刷新事件() {}
    public void 界面遮挡事件() {}
    public void 界面回调事件(int $请求码,int $返回码,Intent $意图) {}
    public void 离开界面事件() {}
    public void 界面销毁事件() {}
    public void 取得焦点事件() {}
    public void 失去焦点事件() {}
    public Boolean 按键按下事件(int $按键码,KeyEvent $事件) {return null;}
    public Boolean 返回按下事件() {return null;}
    public void 收到意图事件(Intent $意图) {}
    public void 保存状态事件(Bundle $输出) {}
    public void 权限回调事件() {}

}
