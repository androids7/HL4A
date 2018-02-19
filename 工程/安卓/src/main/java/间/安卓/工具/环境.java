package 间.安卓.工具;

import android.app.Application;
import 间.安卓.组件.基本应用;
import 间.收集.哈希表;
import 间.接口.方法;
import 间.接口.调用;
import 间.收集.集合;

public class 环境 {

    private volatile static 哈希表<String,Object> 环境表 = new 哈希表<>();
    private volatile static 哈希表<String,集合<方法>> 事件表 = new 哈希表<>();

    public static Object 读取(String $键值) {
        if ($键值 == null)return null;
        synchronized (环境表) {
            return 环境表.读取($键值);
        }
    }

    public static void 设置(String $键值,Object $内容) {
        if ($键值 == null)return;
        synchronized (环境表) {
            环境表.设置($键值, $内容);
        }
    }

    public static Object[] 调用(String $键值,Object... $参数) {
        if ($键值 == null)return null;
        synchronized (事件表) {
            集合<方法> $事件 = 事件表.读取($键值);
            if ($事件 != null) {
                集合<Object> $返回 = new 集合<>();
                for (方法 $单个 : $事件) {
                    $返回.添加(调用.事件($单个, $参数));
                }
                return $返回.到数组();
            }
        }
        return null;
    }

    public static void 注册(String $键值,方法 $内容) {
        if ($内容 == null) return;
        synchronized (事件表) {
            集合<方法> $事件 = 事件表.读取($键值);
            if ($事件 != null) {
                $事件 = new 集合<>();
            }
            $事件.添加($内容);
            事件表.设置($键值, $事件);
        }
    }

    public static void 清空(String $内容) {
        if ($内容 == null) return;
        synchronized (事件表) {
            事件表.设置($内容, null);
        }
    }


    public static void 置应用(Application $上下文) {
        设置("应用", $上下文);
    }

    public static Application 取应用() {
        return (Application)读取("应用");
    }

}
