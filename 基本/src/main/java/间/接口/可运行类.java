package 间.接口;

public class 可运行类 implements Runnable, 方法 {

    public 方法 方法;
    public Object[] 参数;

    public 可运行类(方法 $方法, Object... $参数) {
        方法 = $方法;
        参数 = $参数;
    }

    @Override
    public void run() {
        执行();
    }

    public Object 执行() {
        return 调用(参数);
    }

    @Override
    public Object 调用(Object... $参数) {
        if (方法 != null)
            return 方法.调用($参数);
        return null;
    }

}
