package 间.安卓.工具;

import android.util.TypedValue;
import java.lang.reflect.TypeVariable;
import android.util.DisplayMetrics;

public class 转换 {

    public static DisplayMetrics rdm = 环境.取应用().getResources().getDisplayMetrics();

    public static Float px(float $值) {
        return 转换大小(TypedValue.COMPLEX_UNIT_PX, $值);
    }

    public static Float dp(float $值) {
        return 转换大小(TypedValue.COMPLEX_UNIT_DIP, $值);
    }

    public static Float sp(float $值) {
        return 转换大小(TypedValue.COMPLEX_UNIT_SP, $值) * 倍数;
    }

    public static Float pt(float $值) {
        return 转换大小(TypedValue.COMPLEX_UNIT_PT, $值);
    }

    public static Float mm(float $值) {
        return 转换大小(TypedValue.COMPLEX_UNIT_MM, $值);
    }

    public static Float in(float $值) {
        return 转换大小(TypedValue.COMPLEX_UNIT_IN, $值);
    }
    
    public static Float kb(float $字节) {
        return $字节 / 1024;
    }
    
    public static Float mb(float $字节) {
        return $字节 / (1024 * 1024);
    }
    
    public static Float gb(float $字节) {
        return $字节 / (1024 * 1024 * 1024);
    }

    public static int 数字(String $值) {
        return Integer.parseInt($值);
    }
    
    public static float 单精(String $值) {
        return Float.parseFloat($值);
    }
    
    public static Double 双精(String $值) {
        return Double.parseDouble($值);
    }
    
    private static float 倍数 = 1 / 设备.取屏幕密度();
    
    private static Float 转换大小(int $类型,float $值) {
        return TypedValue.applyDimension($类型, $值, rdm);
    }

}
