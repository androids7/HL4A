package 间.工具;

import java.lang.reflect.Array;
import 间.收集.集合;
import java.util.Arrays;

public class 数组 {

    public static <类型> 类型[] 反转(类型[] $数组) {
        类型[] $返回 = 创建(取泛型数组类($数组), $数组.length);
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static <类型> 类型[] 创建(Class<类型> $类,int $长度) {
        return (类型[]) Array.newInstance($类, $长度);
    }

    public static <类型> 类型[] 添加(类型[] $数组,类型 $对象) {
        类型[] $返回 = 创建(取泛型数组类($数组), $数组.length + 1);
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static <类型> 类型[] 添加(类型[] $数组,int $键值,类型 $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        类型[] $返回 = 创建(取泛型数组类($数组), $数组.length + 1);
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static <类型> 类型[] 删除(类型[] $数组,类型 $对象) {
        集合<类型> $返回 = new 集合<类型>($数组);
        $返回.remove($对象);
        return $返回.到数组(取泛型数组类($数组));
    }

    public static <类型> 类型[] 删除(类型[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        类型[] $返回 = 创建(取泛型数组类($数组),$数组.length - 1);
        if ($键值 > 0)
        System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
        System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static <类型> 类型[] 截取(类型[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        类型[] $返回 = 创建(取泛型数组类($数组), $长度);
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static <类型> 类型[] 转换(Class<类型> $类,Object[] $数组) {
        if ($数组.getClass().getComponentType().equals($类)) {
            return (类型[])$数组;
        } else {
            类型[] $返回 = 创建($类, $数组.length);
            System.arraycopy($数组,0,$返回,0,$数组.length);
            return $返回;
        }
    }

    public static <类型> 类型[] 合并(类型[] $前,类型[] $后) {
        类型[] $返回 = 创建(取泛型数组类($前),$前.length + $后.length);
        if ($前.length > 0)
        System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
        System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }
    
    public static <类型> boolean 比较(类型[] $数组,类型[] $比较) {
        return Arrays.equals($数组,$比较);
    }
    
    public static <类型> boolean 以开始(类型[] $数组,类型[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }
    
    public static <类型> boolean 以结束(类型[] $数组,类型[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    
    public static int[] 反转(int[] $数组) {
        int[] $返回 = new int[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static int[] 创建int(int $长度) {
        return (int[]) Array.newInstance(int.class, $长度);
    }

    public static int[] 添加(int[] $数组,int $对象) {
        int[] $返回 = new int[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static int[] 添加(int[] $数组,int $键值,int $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        int[] $返回 = new int[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static int[] 删除(int[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        int[] $返回 = new int[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static int[] 截取(int[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        int[] $返回 = new int[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static int[] 合并(int[] $前,int[] $后) {
        int[] $返回 = new int[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(int[] $数组,int[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(int[] $数组,int[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(int[] $数组,int[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static long[] 反转(long[] $数组) {
        long[] $返回 = new long[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static long[] 创建long(int $长度) {
        return (long[]) Array.newInstance(long.class, $长度);
    }

    public static long[] 添加(long[] $数组,long $对象) {
        long[] $返回 = new long[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static long[] 添加(long[] $数组,int $键值,long $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        long[] $返回 = new long[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static long[] 删除(long[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        long[] $返回 = new long[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static long[] 截取(long[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        long[] $返回 = new long[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static long[] 合并(long[] $前,long[] $后) {
        long[] $返回 = new long[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(long[] $数组,long[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(long[] $数组,long[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(long[] $数组,long[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static double[] 反转(double[] $数组) {
        double[] $返回 = new double[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static double[] 创建double(int $长度) {
        return (double[]) Array.newInstance(double.class, $长度);
    }

    public static double[] 添加(double[] $数组,double $对象) {
        double[] $返回 = new double[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static double[] 添加(double[] $数组,int $键值,double $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        double[] $返回 = new double[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static double[] 删除(double[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        double[] $返回 = new double[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static double[] 截取(double[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        double[] $返回 = new double[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static double[] 合并(double[] $前,double[] $后) {
        double[] $返回 = new double[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(double[] $数组,double[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(double[] $数组,double[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(double[] $数组,double[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static float[] 反转(float[] $数组) {
        float[] $返回 = new float[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static float[] 创建float(int $长度) {
        return (float[]) Array.newInstance(float.class, $长度);
    }

    public static float[] 添加(float[] $数组,float $对象) {
        float[] $返回 = new float[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static float[] 添加(float[] $数组,int $键值,float $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        float[] $返回 = new float[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static float[] 删除(float[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        float[] $返回 = new float[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static float[] 截取(float[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        float[] $返回 = new float[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static float[] 合并(float[] $前,float[] $后) {
        float[] $返回 = new float[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(float[] $数组,float[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(float[] $数组,float[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(float[] $数组,float[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static short[] 反转(short[] $数组) {
        short[] $返回 = new short[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static short[] 创建short(int $长度) {
        return (short[]) Array.newInstance(short.class, $长度);
    }

    public static short[] 添加(short[] $数组,short $对象) {
        short[] $返回 = new short[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static short[] 添加(short[] $数组,int $键值,short $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        short[] $返回 = new short[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static short[] 删除(short[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        short[] $返回 = new short[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static short[] 截取(short[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        short[] $返回 = new short[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static short[] 合并(short[] $前,short[] $后) {
        short[] $返回 = new short[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(short[] $数组,short[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(short[] $数组,short[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(short[] $数组,short[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static char[] 反转(char[] $数组) {
        char[] $返回 = new char[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static char[] 创建char(int $长度) {
        return (char[]) Array.newInstance(char.class, $长度);
    }

    public static char[] 添加(char[] $数组,char $对象) {
        char[] $返回 = new char[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static char[] 添加(char[] $数组,int $键值,char $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        char[] $返回 = new char[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static char[] 删除(char[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        char[] $返回 = new char[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static char[] 截取(char[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        char[] $返回 = new char[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static char[] 合并(char[] $前,char[] $后) {
        char[] $返回 = new char[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(char[] $数组,char[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(char[] $数组,char[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(char[] $数组,char[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static byte[] 反转(byte[] $数组) {
        byte[] $返回 = new byte[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static byte[] 创建byte(int $长度) {
        return (byte[]) Array.newInstance(byte.class, $长度);
    }

    public static byte[] 添加(byte[] $数组,byte $对象) {
        byte[] $返回 = new byte[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static byte[] 添加(byte[] $数组,int $键值,byte $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        byte[] $返回 = new byte[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static byte[] 删除(byte[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        byte[] $返回 = new byte[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static byte[] 截取(byte[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        byte[] $返回 = new byte[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static byte[] 合并(byte[] $前,byte[] $后) {
        byte[] $返回 = new byte[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(byte[] $数组,byte[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(byte[] $数组,byte[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(byte[] $数组,byte[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static boolean[] 反转(boolean[] $数组) {
        boolean[] $返回 = new boolean[$数组.length];
        for (int $键值 = $数组.length - 1;$键值 >= 0;$键值 --)  {
            $返回[$键值] = $数组[$键值];
        }
        return $返回;
    }

    public static boolean[] 创建boolean(int $长度) {
        return (boolean[]) Array.newInstance(boolean.class, $长度);
    }

    public static boolean[] 添加(boolean[] $数组,boolean $对象) {
        boolean[] $返回 = new boolean[$数组.length + 1];
        System.arraycopy($数组, 0, $返回, 0, $数组.length);
        $返回[$返回.length - 1] = $对象;
        return $返回;
    }

    public static boolean[] 添加(boolean[] $数组,int $键值,boolean $对象) {
        if ($键值 < 0) $键值 = 0;
        if ($键值 > $数组.length - 1) $键值 = $数组.length;
        boolean[] $返回 = new boolean[$数组.length + 1];
        if ($键值 > 0)
            System.arraycopy($数组, 0, $返回, 0, $键值);
        $返回[$键值] = $对象;
        if ($数组.length - $键值 > 0)
            System.arraycopy($数组, $键值, $返回, $键值 + 1, $数组.length - $键值);
        return $返回;
    }

    public static boolean[] 删除(boolean[] $数组,int $键值) {
        if ($数组.length == 0)return $数组;
        else if($键值 > $数组.length - 1)return $数组;
        boolean[] $返回 = new boolean[$数组.length - 1];
        if ($键值 > 0)
            System.arraycopy($数组,0,$返回,0,$键值 - 1);
        if ($数组.length - $键值 - 1 > 0)
            System.arraycopy($数组,$键值,$返回,$键值,$数组.length - $键值);
        return $返回;
    }

    public static boolean[] 截取(boolean[] $数组,Integer $开始,Integer $结束) {
        int $开始位置 = $开始 == null ? 0 : $开始;
        int $结束位置 = $结束 == null ? $数组.length : $结束 + 1;
        if ($开始位置 == 0)return $数组;
        int $长度 = $结束位置 - $开始位置;
        boolean[] $返回 = new boolean[$长度];
        System.arraycopy($数组,$开始位置,$返回,0,$结束位置 - $开始位置);
        return $返回;
    }

    public static boolean[] 合并(boolean[] $前,boolean[] $后) {
        boolean[] $返回 = new boolean[$前.length + $后.length];
        if ($前.length > 0)
            System.arraycopy($前,0,$返回,0,$前.length);
        if ($后.length > 0)
            System.arraycopy($后,0,$返回,$前.length,$后.length);
        return $返回;
    }

    public static boolean 比较(boolean[] $数组,boolean[] $比较) {
        return Arrays.equals($数组,$比较);
    }

    public static boolean 以开始(boolean[] $数组,boolean[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,0,$比较.length  - 1),$比较);
    }

    public static boolean 以结束(boolean[] $数组,boolean[] $比较) {
        if ($比较.length == 0) return true;
        return 比较(截取($数组,$数组.length - $比较.length,$数组.length - 1),$比较);
    }
    
    public static <类型> Class<类型> 取泛型数组类(类型[] $数组) {
        return (Class<类型>)$数组.getClass().getComponentType();
    }

}
