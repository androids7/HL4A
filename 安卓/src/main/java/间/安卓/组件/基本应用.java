package 间.安卓.组件;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import hl4a.runtime.ProxyActivity;
import java.io.Serializable;
import 间.安卓.工具.应用;
import 间.安卓.工具.线程;
import 间.安卓.插件.应用插件;
import 间.工具.反射;
import 间.工具.错误;
import 间.收集.集合;

public class 基本应用 extends Application {

    public final 集合<应用插件> 所有插件 = new 集合<>();

    @Override
    public void onCreate() {
        super.onCreate();
        应用.初始化应用(this);
        应用创建事件();
        for (应用插件 $单个 : 所有插件) {
            $单个.初始化();
        }
    }

    public void 注册插件(应用插件 $插件) {
        if ($插件 == null)return;
        synchronized (所有插件) {
            所有插件.添加($插件);
        }
    }

    @Override
    public void attachBaseContext(Context $上下文) {
        super.attachBaseContext($上下文);
        处理环境事件($上下文);
    }

    @Override
    public void onConfigurationChanged(Configuration $新设置) {
        super.onConfigurationChanged($新设置);
        设置改变事件($新设置);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        应用销毁事件();
    }

    public void 跳转界面(Class<?> $类) {
        跳转界面($类, null);
    }

    public void 跳转界面(Class<?> $类,Object... $数据) {

        if (反射.是子类(界面.class, $类)) {
            Intent $意图 = new Intent(this, ProxyActivity.class);
            $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            $意图.putExtra("类", (Serializable)$类);
            if ($数据 != null) 
                $意图.putExtra("参数", (Serializable)$数据);
            startActivity($意图);
            return;
        }
        Intent $意图 = new Intent(this, $类);
        $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if ($数据 != null)
            $意图.putExtra("参数", (Serializable)$数据);
        startActivity($意图);
    }

    public void 跳转脚本(String $类) {
        跳转脚本($类, null);
    }

    public void 跳转脚本(String $类,Object[] $数据) {
        new 线程(this, "直接跳转脚本").启动($类, $数据);
    }

    public void 直接跳转脚本(Integer $请求码,String $类,Object[] $数据) {
        $请求码 = $请求码 == null ? -1 : $请求码;
        Class<?> $界面 = 反射.取类("hl4a.runtime.ScriptActivity");
        if ($界面 != null) {
            Intent $意图 = new Intent(this, $界面);
            $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            $意图.putExtra("脚本", $类);
            if ($数据 != null)
                $意图.putExtra("参数", (Serializable)$数据);
        } else {
            错误.内容("没有脚本运行时 ~");
        }
    }


    public void 应用创建事件() {}
    public void 应用销毁事件() {}
    public void 处理环境事件(Context $上下文) {}
    public void 设置改变事件(Configuration $新设置) {}

}
