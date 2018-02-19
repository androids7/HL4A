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
        指定($内容, 主题.取主题颜色().取控件色());
    }

    public static void 警告(final Object $内容) {
        指定($内容, 颜色.红色.取控件色());
    }

    public static void 弹窗(Context $上下文,String $标题,String $内容,方法 $回调) {
        基本弹窗 $弹窗 = new 基本弹窗($上下文);
        $弹窗.置标题($标题);
        $弹窗.置内容($内容);
        $弹窗.置右按钮("了解", $弹窗.隐藏);
        $弹窗.置隐藏事件($回调);
        $弹窗.显示();
    }

}
