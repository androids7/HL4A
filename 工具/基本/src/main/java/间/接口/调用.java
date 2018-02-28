package 间.接口;

import 间.工具.*;

public class 调用 {

    public static 方法 合并(final 方法 $方法,final 方法 $回调) {
        return new 方法() {
            @Override
            public Object 调用(Object[] $参数) {
                普通($方法);
                return 普通($回调);
            }
        };
    }

    public static 方法 代理(final Object $对象,final String $方法名) {
        return new 方法() {
            @Override
            public Object 调用(Object[] $参数) {
                return 反射.调用方法($对象, $方法名, $参数);
            }
        };
    }
    
    public static 方法 配置(final Object $对象,final String $方法名,final Object... $配置参数) {
        return new 方法() {
            @Override
            public Object 调用(Object[] $参数) {
                return 反射.调用方法($对象, $方法名, $配置参数);
            }
        };
    }
    
    public static 方法 异步代理(final Object $对象,final String $方法名) {
        return new 线程(代理($对象,$方法名)).启动;
    }
    
    public static 方法 异步配置(final Object $对象,final String $方法名,Object... $参数) {
        return new 线程(配置($对象,$方法名,$参数)).启动;
    }

    public static 方法 空方法 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            return null;
        }
    };
    
    public static Object 普通(方法 $方法,Object... $参数) {
        return $方法.调用($参数);
    }

    public static Object 安全(方法 $方法,Object... $参数) {
        try {
            return 普通($方法, $参数);
        } catch (Exception $错误) {
        }
        return null;

    }

    public static Object 事件(方法 $方法,Object... $参数) {
        if ($方法 != null)
            return 普通($方法, $参数);
        return null;
    }

}
