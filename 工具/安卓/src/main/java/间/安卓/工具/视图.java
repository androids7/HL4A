package 间.安卓.工具;

import android.content.res.*;
import android.graphics.*;
import android.text.*;
import android.view.*;
import 间.工具.*;
import java.io.*;
import android.graphics.drawable.*;

public class 视图 {

    public static ColorStateList 创建颜色列表(Object $普通颜色,Object $按下颜色) {
        int $普通 = 检查颜色($普通颜色);
        int $按下 = 检查颜色($按下颜色);
        int[] $颜色 = new int[] {$按下,$普通};int[][] $状态 = new int[2][];
        $状态[0] = new int[] { android.R.attr.state_pressed};
        $状态[1] = new int[] {}; 
        return new ColorStateList($状态, $颜色);
    }

    public static ColorStateList 创建单颜色列表(Object $普通颜色) {
        int $普通 = 检查颜色($普通颜色);
        int[] $颜色 = new int[] {$普通};
        int[][] $状态 = new int[1][];
        $状态[0] = new int[] {}; 
        return new ColorStateList($状态, $颜色);
    }

    public static Bitmap 检查图片(Object $图片) {
        if ($图片 instanceof String) {
            if (文件.是文件((String)$图片)) {
                return 图片.读取((String)$图片);
            }
        }
        if ($图片 instanceof Bitmap) return (Bitmap)$图片;
        if ($图片 instanceof byte[]) return 图片.读取((byte[])$图片);
        if ($图片 instanceof InputStream) return 图片.读取((InputStream)$图片);
        if ($图片 instanceof BitmapDrawable)return 图片.读取((BitmapDrawable)$图片);
        return null;
    }


    public static Integer 检查输入类型(Object $类型) {
        if ($类型 instanceof Number) return ((Number)$类型).intValue();
        if ($类型 instanceof String)
            switch ((String)$类型) {
                case "文本":return InputType.TYPE_CLASS_TEXT;
                case "手机":return InputType.TYPE_CLASS_PHONE;
                case "数字":return InputType.TYPE_CLASS_NUMBER;
                case "日期":return InputType.TYPE_CLASS_DATETIME;
                case "日期-日期":return InputType.TYPE_DATETIME_VARIATION_DATE;
                case "日期-普通":return InputType.TYPE_DATETIME_VARIATION_NORMAL;
                case "日期-时间":return InputType.TYPE_DATETIME_VARIATION_TIME;
                case "数字-十进制":return InputType.TYPE_NUMBER_FLAG_DECIMAL;
                case "数字-带符号":return InputType.TYPE_NUMBER_FLAG_SIGNED;
                case "数字-普通":return InputType.TYPE_NUMBER_VARIATION_NORMAL;
                case "数字-密码":return InputType.TYPE_NUMBER_VARIATION_PASSWORD;
                case "文本-自动完成":return InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
                case "文本-自动矫正":return InputType.TYPE_TEXT_FLAG_AUTO_CORRECT;
                case "文本-大写字符":return InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
                case "文本-大写句子":return InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
                case "文本-大写单词":return InputType.TYPE_TEXT_FLAG_CAP_WORDS;
                case "文本-邮箱":return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
                case "文本-邮件":return InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT;
                case "文本-长信息":return InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE;
                case "文本-普通":return InputType.TYPE_TEXT_VARIATION_NORMAL;
                case "文本-密码":return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                case "文本-人名":return InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
                case "文本-联系人":return InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS;
                case "文本-短信":return InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE;
                case "文本-链接":return InputType.TYPE_TEXT_VARIATION_URI;
                case "文本-密码-显示":return InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                case "文本-网页-编辑框":return InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT;
                case "文本-网页-邮箱":return InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS;
                case "文本-网页-密码":return InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD;
            }
        return InputType.TYPE_NULL;
    }

    public static Integer 检查颜色(Object $颜色) {
        if ($颜色 == null) 错误.内容("传入颜色值为空");
        if ($颜色 instanceof Number)
            return ((Number) $颜色).intValue();
        if ($颜色 instanceof String) {
            if (字符.以开始((String)$颜色, "#")) {
                return 颜色.转换((String)$颜色);
            } else {
                switch (字符.取出现次数((String)$颜色, "#")) {
                    case 0:switch ((String)$颜色) {
                            case "透明":case "through":$颜色 =  颜色.透明;break;
                            case "白色":case "white":$颜色 = 颜色.白色;break;
                            case "黑色":case "black":$颜色 = 颜色.黑色;break;
                            default:return 取指定颜色(主题.取颜色(), $颜色);
                        }
                        return 颜色.转换((String)$颜色);
                    case 1:
                        String $类型 = 字符.截取开始((String)$颜色, null, "#");
                        颜色 $对象 = (颜色)反射.取变量(颜色.class, $类型);
                        if ($颜色 == null) 错误.内容("没有那样的颜色 :" + $类型);
                        return 取指定颜色($对象, 字符.截取开始((String)$颜色, "#", null));
                    default:错误.内容("这个颜色里不只一个# 所以不是一个颜色");
                }
                return null;
            }
        }
        错误.内容("未知的颜色 : " + $颜色);
        return null;
    }

    private static Integer 取指定颜色(颜色 $颜色,Object $内容) {
        switch ((String)$内容) {
            case "基本":return $颜色.取基本色();
            case "深色":return $颜色.取基本深色();
            case "控件":return $颜色.取控件色();
            case "淡色":return $颜色.取淡色();
        }
        颜色 $对象 = (颜色)反射.取变量(颜色.class,(String)$内容);
        if ($对象 != null) return $对象.取基本色();
        错误.内容("没有那样的颜色类型 :" + $内容);
        return null;
    }

    public static Integer 检查大小(Object $大小) {
        if ($大小 == null || "".equals($大小)) return null;
        if ($大小 instanceof Number) return ((Number)$大小).intValue();
        if ($大小 instanceof String) {
            switch ((String)$大小) {
                case "最大":case "填充":case "-1":return -1;
                case "最小":case "自动":case "-2":return -2;
                case "默认填充":return 主题.取默认填充();
                case "中等填充":return 主题.取中等填充();
                case "默认阴影":return 主题.取默认阴影();
                case "默认圆角":return 主题.取默认圆角();
            }
            if ("".equals(((String)$大小).replaceAll("[0-9]", ""))) {
                return new Integer((String)$大小);
            }
            String $结束 = 字符.小写(字符.取结束后((String)$大小, 2));
            Float $数量 = 转换.单精(字符.取结束前((String)$大小, 2));
            switch ($结束) {
                case "px":return 转换.px($数量).intValue();
                case "dp":return 转换.dp($数量).intValue();
                case "sp":return 转换.sp($数量).intValue();
                case "pt":return 转换.pt($数量).intValue();
                case "mm":return 转换.mm($数量).intValue();
                case "in":return 转换.in($数量).intValue();
            }
        }
        return -2;
    }

    public static Integer 检查重力(String $重力) {
        Integer $重力类型 = Gravity.NO_GRAVITY;
        String[] $设置 = $重力.toLowerCase().split("[|]");
        for (String $单个 : $设置) {
            switch ($单个) {
                case "start":case "开始":
                    $重力类型 = $重力类型 | Gravity.START;
                    break;
                case "end":case "结束":
                    $重力类型 = $重力类型 | Gravity.START;
                    break;
                case "center":case "中间":
                    $重力类型 = $重力类型 | Gravity.CENTER;
                    break;
                case "left":case "左边":
                    $重力类型 = $重力类型 | Gravity.LEFT;
                    break;
                case "textStart":case "文本开始":
                    $重力类型 = $重力类型 | Gravity.START;
                    break;
                case "right":case "右边":
                    $重力类型 = $重力类型 | Gravity.RIGHT;
                    break;
                case "textEnd":case "文本结束":
                    $重力类型 = $重力类型 | Gravity.END;
                    break;
                case "top":case "上边":
                    $重力类型 = $重力类型 | Gravity.TOP;
                    break;
                case "bottom":case "下边":
                    $重力类型 = $重力类型 | Gravity.BOTTOM;
                    break;
                case "center_horizontal":case "中左":case "中间水平":
                    $重力类型 = $重力类型 | Gravity.CENTER_HORIZONTAL;
                    break;
                case "center_vertical":case "中上":case "中间垂直":
                    $重力类型 = $重力类型 | Gravity.CENTER_VERTICAL;
                    break;
            }
        }
        return $重力类型;
    }

}
