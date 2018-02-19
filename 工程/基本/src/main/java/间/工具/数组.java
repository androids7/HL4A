package 间.工具;

import 间.收集.*;
import java.lang.reflect.*;

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
        集合<类型> $返回 = 集合.到集合($数组);
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
    
    public static <类型> Class<类型> 取泛型数组类(类型[] $数组) {
        return (Class<类型>)$数组.getClass().getComponentType();
    }

}
