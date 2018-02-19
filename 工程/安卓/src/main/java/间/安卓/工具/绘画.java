package 间.安卓.工具;

import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import 间.安卓.绘画.按下变色绘画;
import 间.安卓.绘画.点九图绘画;
import android.graphics.Paint;

public class 绘画 {

    public static Drawable 透明() {
        return 生成背景(颜色.透明, 颜色.半透明,0);
    }

    public static Drawable 白色() {
        return 生成背景(颜色.白色, 颜色.白透明,0);
    }

    public static Drawable 黑色() {
        return 生成背景(颜色.黑色, 颜色.黑透明,0);
    }

    public static Drawable 主题() {
        return 生成背景(主题.取主题颜色().取控件色(), 主题.取主题颜色().取基本色(),主题.取默认圆角());
    }

    public static Drawable 生成背景(Object $普通,Object $按下,Object $圆角) {
        if (设备.取SDK() > 21) {
            ShapeDrawable $波纹 = new ShapeDrawable();
            int $角 = 视图.检查大小($圆角);
            float[] $外矩形 = {$角, $角, $角, $角, $角, $角, $角, $角};
            RectF $距离 = new RectF(0, 0, 0, 0);
            float[] $内矩形 = {0, 0, 0, 0, 0, 0, 0, 0};
            RoundRectShape $圆角形状 = new RoundRectShape($外矩形, $距离, $内矩形);
            $波纹.setShape($圆角形状);
            InsetDrawable $绘画 = new InsetDrawable($波纹, -1);
            ShapeDrawable $内容 = new ShapeDrawable();
            RoundRectShape $内容形状 = new RoundRectShape($外矩形, $距离, $内矩形);
            $内容.setShape($内容形状);
            $内容.getPaint().setColor(视图.检查颜色($普通));
            $内容.getPaint().setStyle(Paint.Style.FILL);
            RippleDrawable $涟漪 = new RippleDrawable(ColorStateList.valueOf(主题.取主题颜色().取淡色()), $内容, $绘画);
            return $涟漪;
        } else {
            return new 按下变色绘画($普通, $按下);
        }
        //return new 涟漪绘画($普通,$按下,颜色.白色);
    }

    public static Drawable 颜色转绘画(Object $颜色) {
        return new ColorDrawable(视图.检查颜色($颜色));
    }

    public static Drawable 图片转绘画(String $文件) {
        return new BitmapDrawable(图片.读取($文件));
    }

    public static Drawable 点九图转绘画(String $文件) {
        return new 点九图绘画(图片.读取($文件));
    }

}
