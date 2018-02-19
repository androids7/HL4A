package 间.接口;

import java.lang.reflect.*;
import java.util.*;
import 间.收集.*;

public class 动态接口 implements InvocationHandler {

    public static Class v = void.class;

    public 哈希表<String,方法> 方法表;

    public 动态接口(Map<String,方法> $列表) {
        方法表 = new 哈希表<>($列表);
    }
    
    public 动态接口(方法 $方法) {
        方法表 = new 哈希表<>();
        方法表.设置("通用",$方法);
    }
    

    public Object 代理(Class<?> $类) {
        return Proxy.newProxyInstance($类.getClassLoader(), $类.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object $对象,Method $方法,Object[] $参数) {
        
        Class<?> $返回类型 = $方法.getReturnType();
        
        方法 $通用方法 = null;
        
        Object $方法对象 = 方法表.读取($方法.getName());
        
        if ($方法对象 == null) {
            $方法对象 = 方法表.读取("通用");
        }
        
        if ($方法对象 instanceof 方法) {
            
            $通用方法 = (方法)$方法对象;
            
        }
        
        Object $返回值 = 调用.事件($通用方法,$参数);
        
        if ($返回类型 == v) $返回值 = null;
        
        return $返回值;
       
    }

}
