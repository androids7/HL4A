package 间.安卓.工具;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import 间.接口.方法;
import 间.接口.调用;

public abstract class 事件 extends BroadcastReceiver {

    private 方法 方法;
    private IntentFilter 选择器 = new IntentFilter();
    
    public 事件(方法 $方法) {
        方法 = $方法;
    }
    
    public 事件 当时区改变() {
        选择器.addAction(Intent.ACTION_TIME_CHANGED);
        return this;
    }
    
    public 事件 每分钟执行() {
        选择器.addAction(Intent.ACTION_TIME_TICK);
        return this;
    }
    
    public 事件 当设置时间() {
        选择器.addAction(Intent.ACTION_TIME_CHANGED);
        return this;
    }
    
    public 事件 当屏幕关闭() {
        选择器.addAction(Intent.ACTION_SCREEN_OFF);
        return this;
    }
    
    public 事件 当屏幕打开() {
        选择器.addAction(Intent.ACTION_SCREEN_ON);
        return this;
    }
    
    public 事件 注册() {
        反注册();
        环境.取应用().registerReceiver(this,选择器);
        return this;
    }

    public void 反注册() {
        环境.取应用().unregisterReceiver(this);
    }
    
    @Override
    public void onReceive(Context $上下文,Intent $意图) {
        调用.事件(方法,$上下文,$意图);
    }


}
