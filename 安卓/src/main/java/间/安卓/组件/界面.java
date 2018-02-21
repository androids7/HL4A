package 间.安卓.组件;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import 间.安卓.插件.界面插件;
import 间.收集.哈希表;

public class 界面 {

    public 基本界面 此;
    public Object[] 传入参数;
    public View 当前视图;

    public void 结束界面() {
        此.结束界面();
    }
    
    public void 打开布局(String $地址) {
        此.打开布局($地址);
    }

    public void 结束界面(Exception $错误) {
        此.结束界面($错误);
    }

    public void 注册插件(界面插件 $插件) {
        此.注册插件($插件);
    }

    public void 请求权限() {
        此.请求权限();
    }

    public void 置返回值(int $请求码) {
        此.置返回值($请求码);
    }

    public void 置滑动返回(boolean $是否) {
        此.置滑动返回($是否);
    }

    public void 置返回值(int $结果码,Intent $意图) {
        此.置返回值($结果码, $意图);
    }

    public void 结束界面(int $延时) {
        此.结束界面($延时);
    }

    public void 打开布局(View $视图) {
        此.打开布局($视图);
        当前视图 = 此.当前视图;
    }

    public void 打开布局(哈希表 $内容) {
        此.打开布局($内容);
        当前视图 = 此.当前视图;
    }

    public void 解析布局(String $内容) {
        此.解析布局($内容);
        当前视图 = 此.当前视图;
    }

    public View 取视图(Object $标签) {
        return 此.取视图($标签);
    }

    public void 跳转界面(Integer $请求码,Class<?> $类,Object... $数据) {
        此.跳转界面($请求码, $类, $数据);
    }

    public void 跳转界面(String $类) {
        此.跳转界面($类);
    }

    public void 跳转界面(Class<?> $类) {
        此.跳转界面($类);
    }

    public void 跳转脚本(String $类,Object... $数据) {
        此.跳转脚本($类, $数据);
    }

    public void 跳转脚本(Integer $请求码,String $类,Object... $数据) {
        此.跳转脚本($请求码, $类, $数据);
    }

    public void 跳转界面(String $类,Object... $数据) {
        此.跳转界面($类, $数据);
    }

    public void 跳转脚本(String $类) {
        此.跳转脚本($类);
    }

    public void 跳转界面(Integer $请求码,String $类,Object... $数据) {
        此.跳转界面($请求码, $类, $数据);
    }

    public void 跳转界面(Class<?> $类,Object... $数据) {
        此.跳转界面($类, $数据);
    }

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
