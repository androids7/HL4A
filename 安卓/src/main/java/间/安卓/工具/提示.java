package 间.安卓.工具;

import android.content.Context;
import android.widget.Toast;
import 间.安卓.视图.文本视图;
import 间.安卓.资源.布局.布局_提示;
import 间.工具.字符;
import 间.工具.时间;
import 间.接口.方法;
import 间.安卓.弹窗.基本弹窗;
import 间.接口.调用;
import 间.工具.回调;
import android.view.View;
import hl4a.runtime.BuildConfig;
import 间.工具.反射;
import android.util.Log;
import 间.工具.错误;

public class 提示 {

    private static Toast 提示对象;
    private static 文本视图 文本对象;
    private static long 上次提示;

    protected static void 初始化(Context $环境) {
        提示对象 = Toast.makeText($环境, "", Toast.LENGTH_SHORT);
        布局_提示 $视图 = new 布局_提示($环境);
        文本对象 = $视图.文本;
        提示对象.setView($视图);
        上次提示 = 时间.时间戳();
    }

    private static Boolean 调试 = 反射.取变量(应用.取信息().包名 + ".BuildConfig", "DEBUG");

    public static void 日志(String $日志,Object... $模板) {
        if ($日志 == null) $日志 = "null";
        if (调试) {
            Log.e(字符.截取结束(new Exception().getStackTrace()[1].getClassName(), ".", null), String.format($日志, $模板));
        }
    }
    
    public static void 日志(Object $日志) {
        if ($日志 == null) { 日志(null,new Object[0]); return; }
        if ($日志 instanceof Throwable) {
            $日志 = 错误.取整个错误((Throwable)$日志);
        }
        日志($日志.toString(),new Object[0]);
    }
    
    public static void 指定(final Object $内容,final Object $颜色) {
        处理.主线程(new 方法(){
                @Override
                public Object 调用(Object[] $参数) {
                    直接($内容, $颜色);
                    return null;
                }
            });
    }

    private synchronized static void 直接(Object $文本,Object $颜色) {
        synchronized (文本对象) {
            if ($文本 == null) $文本 = "null";
            else if ($文本 instanceof Object[]) {
                $文本 = 字符.分解((Object[])$文本, "\n");
            }
            long 上次 = 上次提示;
            if ((上次提示 = 时间.时间戳()) - 上次 < 233) {
                文本对象.置文本(文本对象.取文本() + "\n" + $文本);
            } else {
                初始化(环境.取应用()); 
                文本对象.置文本($文本.toString());
            }
            文本对象.置文本颜色($颜色);
            提示对象.show();
        }
    }

    public static void 普通(final Object $内容) {
        指定($内容, 主题.取颜色().取基本色());
    }

    public static void 警告(final Object $内容) {
        指定($内容, 颜色.红色.取基本色());
    }

    public static 基本弹窗 弹窗(Context $上下文,String $标题,Object $内容) {
        return 弹窗($上下文, $标题, $内容, null, null);
    }

    public static 基本弹窗 弹窗(Context $上下文,String $标题,Object $内容,String $右按钮,方法 $右方法) {
        return 弹窗($上下文, $标题, $内容, null, null, $右按钮, $右方法);
    }

    public static 基本弹窗 弹窗(Context $上下文,String $标题,Object $内容,String $中按钮,方法 $中方法,String $右按钮,方法 $右方法) {
        return 弹窗($上下文, $标题, $内容, null, null, $中按钮, $中方法, $右按钮, $右方法);
    }

    public static 基本弹窗 弹窗(Context $上下文,String $标题,Object $内容,String $左按钮,方法 $左方法,String $中按钮,方法 $中方法,String $右按钮,方法 $右方法) {
        return 弹窗($上下文, $标题, $内容, $左按钮, $左方法, $中按钮, $中方法, $右按钮, $右方法, true);
    }

    public static 基本弹窗 弹窗(Context $上下文,String $标题,Object $内容,String $左按钮,方法 $左方法,String $中按钮,方法 $中方法,String $右按钮,方法 $右方法,boolean $可关闭) {
        return 弹窗($上下文, $标题, $内容, $左按钮, $左方法, $中按钮, $中方法, $右按钮, $右方法, $可关闭, null);
    }

    public static 基本弹窗 弹窗(Context $上下文,String $标题,Object $内容,String $左按钮,方法 $左方法,String $中按钮,方法 $中方法,String $右按钮,方法 $右方法,boolean $可关闭,方法 $关闭事件) {
        基本弹窗 $弹窗 = new 基本弹窗($上下文);
        $弹窗.置标题($标题);
        if ($内容 instanceof View) {
            $弹窗.置内容((View)$内容);
        } else {
            $弹窗.置内容($内容 == null ? "null" : $内容.toString());
        }
        $弹窗.置左按钮($左按钮, $左方法);
        $弹窗.置中按钮($中按钮, $中方法);
        $弹窗.置右按钮($右按钮, $右方法);
        $弹窗.置可关闭($可关闭);
        $弹窗.置隐藏事件($关闭事件);
        $弹窗.显示();
        return $弹窗;
    }

}
