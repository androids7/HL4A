package 间.工具;

public class 断言 {

    public static void 不为空(Object $对象,String $描述,Object... $模板) {
        if ($对象 == null) {
            错误.内容(String.format($描述,$模板));
        }
    }
    
    public static void 为空(Object $对象,String $描述,Object... $模板) {
        if ($对象 == null) {
            错误.内容(String.format($描述,$模板));
        }
    }

    public static void 为真(boolean $对象,String $描述,Object... $模板) {
        if ($对象 != true) {
            错误.内容(String.format($描述,$模板));
        }
    }

    public static void 不为真(boolean $对象,String $描述,Object... $模板) {
        if ($对象 == true) {
            错误.内容(String.format($描述,$模板));
        }
    }
    
    public static void 为假(boolean $对象,String $描述,Object... $模板) {
        if ($对象 != false) {
            错误.内容(String.format($描述,$模板));
        }
    }
    
    public static void 不为假(boolean $对象,String $描述,Object... $模板) {
        if ($对象 == false) {
            错误.内容(String.format($描述,$模板));
        }
    }

}
