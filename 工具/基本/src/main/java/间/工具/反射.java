package 间.工具;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import 间.收集.哈希表;
import 间.收集.集合;
import android.app.Application;
import java.lang.reflect.InvocationTargetException;
import 间.接口.方法;
import java.io.ObjectInput;

public class 反射 {
    
    public static Class<?> 取类(String $类名) {
        return 取类($类名,反射.class.getClassLoader());
    }
    
    public static Class<?> 取系统类(String $类名) {
        return 取类($类名,ClassLoader.getSystemClassLoader());
    }

    public static Class<?> 取类(String $类名,ClassLoader $类加载器) {
        try {
            Class<?> $类 = Class.forName($类名, false, $类加载器);
            return $类;
        } catch (Exception $错误) {
        }
        return null;
    }

    public static Class<?> 取子类(Class<?> $类,String $类名) {
        Class<?>[] $所有 = null;
        while ($类 != null) {
            $所有 = $类.getDeclaredClasses();
            for (Class<?> $单个 : $所有) {
                if ($单个.getName().equals($类.getName() + "$" + $类名)) {
                    return $单个;
                }
            }
            $类 = $类.getSuperclass();
        }
        return null;
    }

    private static Field 取变量(Class<?> $类,String $变量名) {
        while ($类 != null) {
            try {
                Field $变量 = $类.getDeclaredField($变量名);
                $变量.setAccessible(true);
                return $变量;
            } catch (NoSuchFieldException $错误) {}
            $类 = $类.getSuperclass();
        }
        return null;
    }
    
    public static boolean 有变量(Class<?> $类,String $变量名) {
        while ($类 != null) {
            try {
                $类.getDeclaredField($变量名);
                return true;
            } catch (NoSuchFieldException $错误) {}
            $类 = $类.getSuperclass();
        }
        return false;
    }
    

    public static <类型> 类型 取变量(Object $对象,String $名称) {
        Class<?> $作用类;
        if ($对象 instanceof Class) {
            $作用类 = (Class<?>)$对象;
        } else if ($对象 instanceof String) {
            $作用类 = 取类((String)$对象);
        } else {
            $作用类 = $对象.getClass();
        }
        return 取变量($对象, $作用类, $名称);
    }

    public static void 置变量(Object $对象,String $名称,Object $内容) {
        Class<?> $作用类;
        if ($对象 instanceof Class) {
            $作用类 = (Class<?>)$对象;
        } else if ($对象 instanceof String) {
            $作用类 = 取类((String)$对象);
        } else {
            $作用类 = $对象.getClass();
        }
        置变量($对象, $作用类, $名称, $内容);
    }

    public static <类型> 类型 取变量(Object $对象,Class<?> $作用类,String $名称) {
        Field $变量 = 取变量($作用类, $名称);
        try {
            if ($变量 != null) 
                return (类型)$变量.get($对象);
        } catch (Exception $错误) {
            错误.抛出($错误);
        }
        return null;
    }

    public static void 置变量(Object $对象,Class<?> $作用类,String $名称,Object $内容) {
        Field $变量 = 取变量($作用类, $名称);
        try {
            if ($变量 != null)
                $变量.set($对象, $内容);
        } catch (IllegalAccessException $错误) {} catch (IllegalArgumentException $错误) {}
    }

    private static Constructor[] 取所有初始化方法(Class<?> $类) {
        if ($类 == null) return new Constructor[0];
        集合<Constructor> $所有 = new 集合<>();
        $所有.添加所有($类.getDeclaredConstructors());
        return $所有.到数组(Constructor.class);
    }

    public static <类型> 类型 实例化(Class<类型> $类,Object... $参数) {
        if ($类.isEnum()) {
            throw new RuntimeException("不能实例化Enum！(没有初始化方法)");
        }
        Constructor[] $所有 = 取所有初始化方法($类);
        Constructor $方法 = null;
        Object[] $参数组 = null;
        for (int $键值 = 0;$键值 < $所有.length;$键值 ++) {
            Constructor $单个 = $所有[$键值];
            Object[] $适配参数 = 适配参数($单个, $参数);
            if ($适配参数 != null) {
                $方法 = $单个;
                $参数组 = $适配参数;
            }
        }
        if ($方法 == null) {
            String $同名 = "\n同名的初始化方法:" + $所有.length;
            if ($所有.length == 0) {
                $同名 += "\n没有";
            } else {
                for (Constructor $单个 : $所有) {
                    $同名 += "\n" + $单个.toGenericString();
                }
            }
            throw new RuntimeException("没有那样的初始化方法:在类" + $类.getName() + "\n参数:" + 字符.分解(取参数类组($参数), ",") + "\n" + $同名);
        }
        try {
            $方法.setAccessible(true);
            return (类型) $方法.newInstance($参数组);
        } catch (Exception $错误) {
            错误.抛出($错误);
        }
        return null;
    }

    private static Method[] 取所有方法(Class<?> $实例,String $名称) {
        Method[] $所有 = null;
        if ($实例 == null) return new Method[0];
        else $所有 = $实例.getDeclaredMethods();
        集合<Method> $方法 = new 集合<Method>();
        for (Method $单个 : $所有) {
            if ($名称.equals($单个.getName())) {
                $单个.setAccessible(true);
                $方法.添加($单个);
            }
        }
        $方法.添加所有(取所有方法($实例.getSuperclass(), $名称));
        return (Method[])$方法.到数组(Method.class);
    }
    
    public static Object[] 变参(Object... $参数) {
        return $参数;
    }
    
    public static Object 调用方法(Object $对象,String $名称,Object... $参数) {
        return 调用($对象,$名称,$参数);
    }
    
    public static <类型> 类型 调用(Object $对象,String $名称) {
        return 调用($对象,$名称,new Object[0]);
    }
    
    public static <类型> 类型 调用(Object $对象,String $名称,Object[] $参数) {
        if ($对象 == null) 错误.内容("参数1 [对象] 为空");
        Method[] $所有 = 取所有方法($对象.getClass(), $名称);
        Method $方法 = null;
        Object[] $参数组 = null;
        for (int $键值 = 0;$键值 < $所有.length;$键值 ++) {
            Method $单个 = $所有[$键值];
            Object[] $适配参数 = 适配参数($单个, $参数);
            if ($适配参数 != null) {
                $方法 = $单个;
                $参数组 = $适配参数;
            }
        }
        if ($方法 == null) {
            String $同名 = "\n同名的方法:" + $所有.length;
            if ($所有.length == 0) {
                $同名 += "\n没有";
            } else {
                for (Method $单个 : $所有) {
                    $同名 += "\n" + $单个.toGenericString();
                }
            }
            throw new RuntimeException("没有那样的方法:在类" + $对象.getClass().getName() + "\n方法:" + $名称 + "\n参数:" + 字符.分解(取参数类组($参数), ",") + "\n" + $同名);
        }
        if ($对象 instanceof Class) {
            $对象 = null;
        }

        try {
            return (类型)$方法.invoke($对象, $参数组);
        } catch (Exception $错误) {
            错误.抛出($错误);
        }
        return null;
    }
    
    private static Object[] 适配参数(Method $单个,Object[] $参数) {
        Class[] $类组 = $单个.getParameterTypes();
        if ($类组.length == 0) {
            return $参数.length == 0 ? $参数 : null;
        }
        if ($类组.length > 1 && $参数.length == 0) {
            return null;
        }
        if ($类组.length != $参数.length && !是数组($类组[$类组.length - 1])) {
            return null;
        }
        集合 $返回集合 = new 集合();
        for (int $键值 = 0;$键值 < $类组.length;$键值 ++) {
            Class $类 = $类组[$键值];
            if ($键值 + 1 == $类组.length && 是数组($类)) {
                Object[] $剩余参数 = 数组.截取($参数, $键值, null);
                Class<?> $数组类型 = $类.getComponentType();
                for (Object $单个参数 : $剩余参数) {
                    if ($单个参数 != null && !是子类($数组类型,$单个参数.getClass())) {
                        return null;
                    }
                }
                $返回集合.添加(数组.转换($数组类型,$剩余参数));
            } else if ($参数[$键值] != null && !是子类($类,$参数[$键值].getClass())) {
                return null;
            } else {
                $返回集合.添加($参数[$键值]);
            }
        }
        return $返回集合.到数组();
    }

    private static Object[] 适配参数(Constructor $单个,Object[] $参数) {
        Class[] $类组 = $单个.getParameterTypes();
        if ($类组.length == 0) {
            return $参数.length == 0 ? $参数 : null;
        }
        if ($类组.length > 1 && $参数.length == 0) {
            return null;
        }
        if ($类组.length != $参数.length && !是数组($类组[$类组.length - 1])) {
            return null;
        }
        集合 $返回集合 = new 集合();
        for (int $键值 = 0;$键值 < $类组.length;$键值 ++) {
            Class $类 = $类组[$键值];
            if ($键值 + 1 == $类组.length && 是数组($类)) {
                int $参数长度 = $参数.length - $类组.length;
                Object[] $剩余参数 = 数组.截取($参数, $键值, null);
                Class<?> $数组类型 = $类.getComponentType();
                for (Object $单个参数 : $剩余参数) {
                    if ($单个参数 != null && !是子类($数组类型,$单个参数.getClass())) {
                        return null;
                    }
                }
                $返回集合.添加(数组.转换($数组类型,$剩余参数));
            } else if ($参数[$键值] != null && !是子类($类,$参数[$键值].getClass())) {
                return null;
            } else {
                $返回集合.添加($参数[$键值]);
            }
        }
        return $返回集合.到数组();
    }


    public static Class[] 取参数类组(Object... $参数) {
        Class[] $参数类组 = new Class[$参数.length];
        for (int i = 0, j = $参数.length; i < j; i++) {
            $参数类组[i] = $参数[i].getClass();
        }
        return $参数类组;
    }
    
    private static 哈希表<Class,Class> 对应表 = new 哈希表<>();
    
    static {
        对应表.设置(int.class,Integer.class);
        对应表.设置(long.class,Long.class);
        对应表.设置(boolean.class,Boolean.class);
        对应表.设置(float.class,Float.class);
        对应表.设置(char.class,Character.class);
        对应表.设置(double.class,Double.class);
        对应表.设置(short.class,Short.class);
        对应表.设置(byte.class,Byte.class);
    }
    
    public static boolean 是子类(Class<?> $类,Class<?> $子类) {
        if ($类.isAssignableFrom($子类)) {  
            return true;
        } else if(对应表.双查键值对($类,$子类)) {
            return true;
        }
        return false;
    }
    
    public static boolean 是数组(Class<?> $类) {
        return $类.isArray();
    }
   
}
