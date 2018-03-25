package 间.安卓.组件;

import android.os.Bundle;
import 间.工具.反射;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

public class 代理界面 extends 基本界面 {

    public 界面 内容;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        Class<界面> $类 = (Class<界面>)getIntent().getSerializableExtra("类");
        内容 = (界面)反射.实例化($类);
        内容.此 = this;
        内容.传入参数 = 传入参数;
        内容.界面创建事件($恢复);
    }

    @Override
    public void 界面启动事件() {
        内容.界面启动事件();
    }

    @Override
    public void 界面销毁事件() {
        内容.界面销毁事件();
    }

    @Override
    public void 界面刷新事件() {
        内容.界面刷新事件();
    }

    @Override
    public void 界面遮挡事件() {
        内容.界面遮挡事件();
    }

    @Override
    public void 界面回调事件(int $请求码,int $返回码,Intent $意图) {
        内容.界面回调事件($请求码, $返回码, $意图);
    }

    @Override
    public void 权限回调事件() {
        内容.权限回调事件();
    }

    @Override
    public boolean 按键按下事件(int $按键码,KeyEvent $事件) {
        return 内容.按键按下事件($按键码, $事件);
    }

    @Override
    public boolean 返回按下事件() {
        return 内容.返回按下事件();
    }
    
    public void 离开界面事件() {
        内容.离开界面事件();
    }
    public void 取得焦点事件() {
        内容.取得焦点事件();
    }
    
    public void 失去焦点事件() {
        内容.失去焦点事件();
    }
    
    public void 收到意图事件(Intent $意图) {
        内容.收到意图事件($意图);
    }
    
    public void 保存状态事件(Bundle $输出) {
        内容.保存状态事件($输出);
    }

}
