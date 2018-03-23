package 间.安卓.工具;

import android.app.Application;
import 间.安卓.组件.基本应用;
import 间.收集.哈希表;
import 间.接口.方法;
import 间.接口.调用;
import 间.收集.集合;
import 间.工具.反射;

public class 环境 {

    private volatile static 哈希表<String,Object> 环境表 = new 哈希表<>();

    public static <类型> 类型 读取(String $键值) {
        if ($键值 == null)return null;
        synchronized (环境表) {
            return (类型)环境表.读取($键值);
        }
    }

    public static void 设置(String $键值,Object $内容) {
        if ($键值 == null)return;
        synchronized (环境表) {
            环境表.设置($键值, $内容);
        }
    }

    public static void 置应用(Application $上下文) {
        设置("应用", $上下文);
    }

    public static Application 取应用() {
        Application $应用 =  读取("应用");
        if ($应用 == null) {
            try {
                // android.app.AppGlobals.getInitialApplication
                // android.app.ActivityThread.currentApplication
                Application $全局 = 反射.调用(反射.取类("android.app.ActivityThread"), "currentApplication");
                置应用($全局);
                return $全局;
            } catch (Exception $错误) {}
        }
        return $应用;
    }

}
