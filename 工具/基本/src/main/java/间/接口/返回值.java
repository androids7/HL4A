package 间.接口;

public class 返回值<内容> {
    
    public static <内容> 返回值<内容> 创建(内容 $内容,Exception $错误) {
        return new 返回值<内容>().置错误($错误);
    }
    
    public static <内容> 返回值<内容> 创建(内容 $内容,boolean $状态) {
        return new 返回值<内容>().置成功($状态);
    }
    
    public static <内容> 返回值<内容> 创建(内容 $内容) {
        return new 返回值<内容>().置内容($内容);
    }
    
    private 内容 内容;
    private Boolean 成功;
    private Exception 错误;
    
    public 返回值<内容> 置内容(内容 $内容) {
        内容 = $内容;
        return this;
    }
    
    public 返回值<内容> 置成功(boolean $状态) {
        成功 = $状态;
        return this;
    }
    
    public 返回值<内容> 置错误(Exception $错误) {
        错误 = $错误;
        return this;
    }
    
    public 返回值<内容> 置错误(String $错误) {
        错误 = new RuntimeException($错误);
        return this;
    }
    
    public 内容 取内容() {
        return 内容;
    }
    
    public <错误 extends Exception> 错误 取错误() {
        return (错误)错误;
    }
    
    public boolean 成功() {
        return 成功 == null ? 内容 != null && 错误 == null : 成功;
    }
    
}
