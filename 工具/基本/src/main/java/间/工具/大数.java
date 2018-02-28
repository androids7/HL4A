package 间.工具;

import java.math.BigDecimal;

public class 大数 {

    public static String 相加(String $参数1,String $参数2) {
        BigDecimal aa = new BigDecimal($参数1);
        BigDecimal bb = new BigDecimal($参数2);
        BigDecimal cc = aa.add(bb);
        return cc.toPlainString();
    }


    public static String 相减(String $参数1,String $参数2) {
        BigDecimal aa = new BigDecimal($参数1);
        BigDecimal bb = new BigDecimal($参数2);
        BigDecimal cc = aa.subtract(bb);
        return cc.toPlainString();
    }


    public static String 相乘(String $参数1,String $参数2) {
        BigDecimal aa = new BigDecimal($参数1);
        BigDecimal bb = new BigDecimal($参数2);
        BigDecimal cc = aa.multiply(bb);
        return cc.toPlainString();
    }


    public static String 相除(String $参数1,String $参数2,int 保留小数位数) {
        BigDecimal aa = new BigDecimal($参数1);
        BigDecimal bb = new BigDecimal($参数2);
        BigDecimal cc = aa.divide(bb, 保留小数位数, 4);
        return cc.toPlainString();
    }


    public static int 比较(String $参数1,String $参数2) {
        BigDecimal aa = new BigDecimal($参数1);
        BigDecimal bb = new BigDecimal($参数2);
        return aa.compareTo(bb);
    }


    public static String 求余(String $参数1,String $参数2) {
        BigDecimal aa = new BigDecimal($参数1);
        BigDecimal bb = new BigDecimal($参数2);
        BigDecimal cc = aa.remainder(bb);
        return cc.toPlainString();
    }


    public static String 科学转普通(String $参数) {
        BigDecimal aa = new BigDecimal($参数);
        return aa.toPlainString();
    }
}


