package 间.安卓.视图.实现;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import 间.安卓.工具.主题;
import 间.安卓.工具.处理;
import 间.安卓.工具.布局;
import 间.安卓.工具.绘画;
import 间.安卓.工具.视图;
import 间.安卓.工具.颜色;
import 间.安卓.视图.事件.单击;
import 间.安卓.视图.事件.触摸;
import 间.安卓.视图.事件.长按;
import 间.工具.反射;
import 间.接口.方法;
import android.widget.Button;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.CompoundButton;
import android.support.v4.widget.CompoundButtonCompat;

public final class 视图实现 {

    private 视图实现() {}

    // 因为侧滑界面和其他一些也需要设置这个，所以干脆反射了

    public static void 置阴影(View $视图,Object $阴影) {
        ViewCompat.setTranslationZ($视图, 视图.检查大小($阴影));
    }

    public static void 置布局重力(View $视图,String $重力) {
        ViewGroup.LayoutParams $设置 = 取设置($视图);
        反射.置变量($设置, "gravity", 视图.检查重力($重力));
        置设置($视图, $设置);
    }

    public static void 置布局权重(View $视图,float $权重) {
        ViewGroup.LayoutParams $设置 = 取设置($视图);
        反射.置变量($设置, "weight", $权重);
        置设置($视图, $设置);
    }

    // AddView

    public static void 加入到(View $视图,ViewGroup $布局) {
        if ($布局 instanceof 基本布局) {
            ((基本布局)$布局).加入子元素($视图);
        } else {
            $布局.addView($视图);
        }
    }

    //setContentView

    public static void 打开(View $视图,Activity $界面) {
        布局.打开($界面, $视图);
    }

    // Tag

    public static void 置标签(View $视图,Object $标签) {
        $视图.setTag($标签);
    }

    public static Object 取标签(View $视图) {
        return $视图.getTag();
    }

    // Width/Height
    public static void 置单击事件(View $视图,方法 $事件) {
        $视图.setOnClickListener(new 单击($事件));
    }

    public static void 置长按事件(View $视图,方法 $事件) {
        $视图.setOnLongClickListener(new 长按($事件));
    }

    public static void 置触摸事件(View $视图,方法 $事件) {
        $视图.setOnTouchListener(new 触摸($事件));
    }


    public static void 置宽度(final View $视图,final Object $宽度) {
        处理.主线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    置宽度实现($视图, $宽度);
                    return null;
                }
            });
    }

    public static void 置高度(final View $视图,final Object $高度) {
        处理.主线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    置高度实现($视图, $高度);
                    return null;
                }
            });
    }


    public static void 置宽度实现(View $视图,Object $宽度) {
        ViewGroup.LayoutParams $设置 = 取设置($视图);
        $设置.width = 视图.检查大小($宽度).intValue();
        置设置($视图, $设置);
    }

    public static void 置高度实现(View $视图,Object $高度) {
        ViewGroup.LayoutParams $设置 = 取设置($视图);
        $设置.height = 视图.检查大小($高度).intValue();
        置设置($视图, $设置);
    }
    public static void 置边距(View $视图,Object $全局) {
        置边距($视图, $全局, $全局, $全局, $全局);
    }

    public static void 置上边距(View $视图,Object $边距) {
        置边距($视图, $边距, null, null, null);
    }

    public static void 置下边距(View $视图,Object $边距) {
        置边距($视图, null, $边距, null, null);
    }

    public static void 置左边距(View $视图,Object $边距) {
        置边距($视图, null, null, $边距, null);
    }

    public static void 置右边距(View $视图,Object $边距) {
        置边距($视图, null, null, null, $边距);
    }

    public static void 置上下边距(View $视图,Object $边距) {
        置边距($视图, $边距, $边距, null, null);
    }

    public static void 置左右边距(View $视图,Object $边距) {
        置边距($视图, null, null, $边距, $边距);
    }

    public static void 置边距实现(View $视图,Object $上,Object $下,Object $左,Object $右) {
        ViewGroup.MarginLayoutParams $设置 = 取边距设置($视图);
        int $上边距 = $设置.topMargin;
        int $下边距 = $设置.bottomMargin;
        int $左边距 = $设置.leftMargin;
        int $右边距 = $设置.rightMargin;
        $上边距 = (($上 = 视图.检查大小($上)) == null) ? $上边距 : (int) $上;
        $下边距 = (($下 = 视图.检查大小($下)) == null) ? $下边距 : (int) $下;
        $左边距 = (($左 = 视图.检查大小($左)) == null) ? $左边距 : (int) $左;
        $右边距 = (($右 = 视图.检查大小($左)) == null) ? $右边距 : (int) $右;
        $设置.setMargins($左边距, $上边距, $右边距, $下边距);
        置设置($视图, $设置);
    }

    public static void 置边距(final View $视图,final Object $上,final Object $下,final Object $左,final Object $右) {
        处理.主线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    置边距实现($视图, $上, $下, $左, $右);
                    return null;
                }
            });
    }

    // Visible

    public static void 置状态(final View $视图,final String $状态) {
        处理.主线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    置状态实现($视图, $状态);
                    return null;
                }});
    }

    public static void 置状态实现(View $视图,String $状态) {
        int $状态i = 0;
        switch ($状态) {
            case "占位":case "invisible":case "4":
                $状态i = 4;break;
            case "隐藏":case "gone":case "8":
                $状态i = 8;break;
        }
        $视图.setVisibility($状态i);
    }

    public static void 显示(View $视图) {
        置状态($视图, "显示");
    }

    public static void 占位(View $视图) {
        置状态($视图, "占位");
    }

    public static void 隐藏(View $视图) {
        置状态($视图, "隐藏");
    }

    public static String 取状态(View $视图) {
        int $状态 = $视图.getVisibility();
        switch ($状态) {
            case 0:return "显示";
            case 4:return "占位";
            case 8:return "隐藏";
        }
        return "未知";
    }

    // Background

    public static void 置背景(View $视图,Object $背景) {
        if ($背景 instanceof Drawable)
            $视图.setBackground((Drawable)$背景);
        else if ($背景 instanceof Integer)
            置背景颜色($视图, (Integer)$背景);
        else if ($背景 instanceof String)
            switch ((String)$背景) {
                case "透明":case "through":置背景($视图, 绘画.透明());break;
                case "白色":case "white":置背景($视图, 绘画.白色());break;
                case "黑色":case "black":置背景($视图, 绘画.黑色());break;
                case "主题":置背景($视图, 绘画.主题());break;
                default:置背景颜色($视图, (String)$背景);
            }
    }


    // BackgroundColor

    public static void 置背景颜色(View $视图,Object $颜色) {
        if ($颜色 instanceof Integer) {
            $视图.setBackgroundColor((Integer)$颜色);
            return;
        }
        if ($视图 instanceof CompoundButton) {
            Drawable $背景 = CompoundButtonCompat.getButtonDrawable((CompoundButton)$视图);
            if ($背景 != null) {
                DrawableCompat.setTint($背景, 视图.检查颜色($颜色));
                return;
            }
        } else if ($视图 instanceof Button) {
            DrawableCompat.setTint(((Button)$视图).getBackground(), 视图.检查颜色($颜色));
            return;
        }
        $视图.setBackgroundColor(视图.检查颜色($颜色));
    }

    public static void 置填充(View $视图,Object $全局) {
        置填充($视图, $全局, $全局, $全局, $全局);
    }

    public static void 置上填充(View $视图,Object $填充) {
        置填充($视图, $填充, null, null, null);
    }

    public static void 置下填充(View $视图,Object $填充) {
        置填充($视图, null, $填充, null, null);
    }

    public static void 置左填充(View $视图,Object $填充) {
        置填充($视图, null, null, $填充, null);
    }

    public static void 置右填充(View $视图,Object $填充) {
        置填充($视图, null, null, null, $填充);
    }

    public static void 置上下填充(View $视图,Object $填充) {
        置填充($视图, $填充, $填充, null, null);
    }

    public static void 置左右填充(View $视图,Object $填充) {
        置填充($视图, null, null, $填充, $填充);
    }


    public static void 置填充(View $视图,Object $上,Object $下,Object $左,Object $右) {

        Integer $上填充 = (($上 = 视图.检查大小($上)) == null) ? $视图.getPaddingTop() : (Integer) $上;
        Integer $下填充 = (($下 = 视图.检查大小($下)) == null) ? $视图.getPaddingBottom() : (Integer) $下;
        Integer $左填充 = (($左 = 视图.检查大小($左)) == null) ? $视图.getPaddingLeft() : (Integer) $左;
        Integer $右填充 = (($右 = 视图.检查大小($右)) == null) ? $视图.getPaddingRight() : (Integer) $右;
        $视图.setPadding($左填充, $上填充, $右填充, $下填充);

    }

    public static void 初始化控件(View $视图) {
        $视图.setLayoutParams(new LayoutParams(-2, -2));
    }

    public static MarginLayoutParams 取边距设置(View $视图) {
        LayoutParams $设置 = 取设置($视图);
        if ($设置 instanceof MarginLayoutParams) {
            return (MarginLayoutParams)$设置;
        } else {
            return new MarginLayoutParams($设置);
        }
    }

    public static LayoutParams 取设置(View $视图) {
        return $视图.getLayoutParams();
    }

    public static void 置设置(View $视图,LayoutParams $设置) {
        if ($设置 != null) {
            $视图.setLayoutParams($设置);
            $视图.requestLayout();
        }
    }


}
