package 间.安卓.工具;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import java.util.Map;
import 间.安卓.视图.线性布局;
import 间.工具.反射;
import 间.工具.字符;
import 间.工具.错误;
import 间.收集.哈希表;
import 间.收集.集合;
import 间.数据.YAML;

public class 布局 {

    static final boolean 可直接 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

    public static void 打开(Activity $界面,View $视图) {
        Window $窗口 = $界面.getWindow();
        if (可直接) {
            $窗口.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            $窗口.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            $窗口.setStatusBarColor(视图.检查颜色(主题.取主题颜色().取基本深色()));
            $界面.setContentView($视图);
            return;
        }
        $窗口.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if ($视图 instanceof ViewGroup) {
            if ($视图.findViewWithTag("$状态栏") != null) {
                $界面.setContentView($视图);
                return;
            }
        }
        $界面.setContentView(加入沉浸状态栏($界面, $视图));
    }

    public static View 加入沉浸状态栏(Activity $上下文,View $视图) {
        线性布局 $内容 = new 线性布局($上下文);
        $内容.置背景颜色(主题.取主题颜色().取基本深色());
        $内容.置宽度("最大");
        $内容.置高度(应用.取状态栏高度($上下文));
        线性布局 $状态栏 = new 线性布局($上下文);
        $状态栏.置标签("$状态栏");
        $状态栏.加入子元素($内容);
        $状态栏.加入子元素($视图);
        return $状态栏;
    }

    public static String[] 视图来源 = {
        "视图","视图.扩展","资源.布局",
    };

    public static View 读取(Context $上下文,String $地址) {
        return 解析($上下文, 字符.读取($地址));
    }

    public static View 解析(Context $上下文,String $数据) {
        哈希表 $表 = YAML.解析($数据, 哈希表.class);
        return 解析($上下文, $表);
    }

    public static View 解析(Context $上下文,哈希表<String,Object> $表) {
        if ($表 == null || $表.长度() == 0) {
            return new 线性布局($上下文);
        } else if ($表.长度() > 1) {
            线性布局 $底层 = new 线性布局($上下文);
            解析($上下文, $表, $底层);
            return $底层;
        } else {
            String $类名 = new 集合<String>($表.keySet()).读取(0);
            Class<?> $类 = 取视图($类名);
            if ($类 == null) {
                错误.内容("没有那样的将要作为底层的视图 : " + $类);
            }
            View $视图 = (View)反射.实例化($类, $上下文);
            解析($上下文, new 哈希表((Map)$表.读取($类名)), $视图);
            return $视图;
        }
    }

    public static void 解析(Context $上下文,哈希表 $表,View $上视图) {
        集合<String> $列表 = new 集合<>($表.keySet());
        for (String $单个 : $列表) {
            Object $内容 = $表.读取($单个);
            if (!($内容 instanceof Map)) {
                解析参数($上视图, $单个, $内容);
            } else if (!($上视图 instanceof ViewGroup)) {
                错误.内容($上视图.getClass().getName() + "不是布局，不能加入子元素");
            } else {
                Class<?> $类 = 取视图($单个);
                if ($类 == null) {
                    错误.内容("视图类不存在: " + $单个);
                }
                View $视图 = (View)反射.实例化($类, $上下文);
                ViewGroup $父视图 = (ViewGroup)$上视图;
                $父视图.addView($视图);
                解析($上下文, new 哈希表((Map)$内容), $视图);
            }
        }

    }

    private static void 解析参数(View $视图,String $单个,Object $内容) {
        String $方法名 = "置" + $单个;
        反射.调用方法($视图, $方法名, $内容);
    }

    private static Class<?> 取视图(String $视图) {
        Class<?> $类 = null;
        for (String $单个 : 视图来源) {
            $类 = 反射.取类("间.安卓." + $单个 + "." + $视图);
            if ($类 != null) {
                break;
            }
        }
        return $类;
    }

}
