package 间.安卓.工具;

import hl4a.runtime.R.style;
import hl4a.runtime.R.color;
import android.graphics.*;

public class 颜色 {


    public static final int 白色 = 转换("#FFFFFF");
    public static final int 白烟 = 转换("#EBEBEB");
    public static final int 黑色 = 转换("#de000000");
    public static final int 透明 = 转换("#00000000");
    public static final int 半透明 = 转换("#10000000");
    public static final int 白透明 = 转换("#e6eaf7");
    public static final int 黑透明 = 转换("#202020");

    public static final 颜色 红色 = new 颜色(color.accent_color_red,color.primary_color_red,color.primary_color_red_dark,style.HL4A_Red);
    public static final 颜色 粉色 = new 颜色(color.accent_color_pink,color.primary_color_pink,color.primary_color_pink_dark,style.HL4A_Pink);
    public static final 颜色 紫色 = new 颜色(color.accent_color_purple,color.primary_color_purple,color.primary_color_purple_dark,style.HL4A_Purple);
    public static final 颜色 深紫 = new 颜色(color.accent_color_deep_purple,color.primary_color_deep_purple_dark,color.primary_color_deep_purple_dark,style.HL4A_DeepPurple);
    public static final 颜色 靛蓝 = new 颜色(color.accent_color_indigo,color.primary_color_indigo,color.primary_color_indigo_dark,style.HL4A_Indigo);
    public static final 颜色 蓝色 = new 颜色(color.accent_color_blue,color.primary_color_blue,color.primary_color_blue_dark,style.HL4A_Blue);
    public static final 颜色 亮蓝 = new 颜色(color.accent_color_light_blue,color.primary_color_light_blue,color.primary_color_light_blue_dark,style.HL4A_LightBlue);
    public static final 颜色 青色 = new 颜色(color.accent_color_cyan,color.primary_color_cyan,color.primary_color_cyan_dark,style.HL4A_Cyan);
    public static final 颜色 鸭绿 = new 颜色(color.accent_color_teal,color.primary_color_teal,color.primary_color_teal_dark,style.HL4A_Teal);
    public static final 颜色 绿色 = new 颜色(color.accent_color_green,color.primary_color_green,color.primary_color_green_dark,style.HL4A_Green);
    public static final 颜色 亮绿 = new 颜色(color.accent_color_light_green,color.primary_color_light_green,color.primary_color_light_green_dark,style.HL4A_LightGreen);
    public static final 颜色 酸橙 = new 颜色(color.accent_color_lime,color.primary_color_lime,color.primary_color_lime_dark,style.HL4A_Lime);
    public static final 颜色 黄色 = new 颜色(color.accent_color_yellow,color.primary_color_yellow,color.primary_color_yellow_dark,style.HL4A_Yellow);
    public static final 颜色 琥珀 = new 颜色(color.accent_color_amber,color.primary_color_amber,color.primary_color_amber_dark,style.HL4A_Amber);
    public static final 颜色 橙色 = new 颜色(color.accent_color_orange,color.primary_color_orange,color.primary_color_orange_dark,style.HL4A_Orange);
    public static final 颜色 暗橙 = new 颜色(color.accent_color_deep_orange,color.primary_color_deep_orange,color.primary_color_deep_orange_dark,style.HL4A_DeepOrange);
    public static final 颜色 棕色 = new 颜色(color.accent_color_brown,color.primary_color_brown,color.primary_color_brown_dark,style.HL4A_Brown);
    public static final 颜色 灰色 = new 颜色(color.accent_color_grey,color.primary_color_grey,color.primary_color_grey_dark,style.HL4A_Grey);
    public static final 颜色 蓝灰 = new 颜色(color.accent_color_blue_grey,color.primary_color_blue_grey,color.primary_color_blue_grey_dark,style.HL4A_BlueGrey);
    
    private int 基本;
    private int 深色;
    private int 控件;
    private int 资源ID;
    
    public 颜色(int $控件,int $基本,int $深色,int $ID) {
        资源ID = $ID;
        基本 = 读取($基本);
        深色 = 读取($深色);
        控件 = 读取($控件);
    }
    
    public int 取控件色() {
        return 控件;
    }
    
    public int 取基本色() {
        return 基本;
    }
    
    public int 取基本深色() {
        return 深色;
    }
    
    public int 取主题() {
        return 资源ID;
    }
    
    public static int 转换(String $颜色) {
        return Color.parseColor($颜色);
    }
    
    public static int 读取(int $颜色) {
        return 环境.取应用().getColor($颜色);
    }
    
}
