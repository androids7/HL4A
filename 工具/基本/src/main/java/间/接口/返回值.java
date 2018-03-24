package 间.接口;

public class 返回值<内容,错误 extends Exception> {
    
    private 内容 内容;
    private boolean 成功;
    private 错误 错误;
    
    public 返回值(内容 $内容,boolean $状态) {
        内容 = $内容;
        成功 = $状态;
    }
    
    public 返回值(错误 $错误) {
        this(null,$错误);
    }
    
    public 返回值(内容 $内容,错误 $错误) {
        内容 = $内容;
        错误 = $错误;
        成功 = 错误 == null;
    }
    
    public static <内容,错误 extends Exception> 返回值<内容,错误> 创建(内容 $内容,错误 $错误) {
        return new 返回值<内容,错误>($内容,$错误);
    }
    
    public static <内容,错误 extends Exception> 返回值<内容,错误> 创建(内容 $内容,boolean $状态) {
        return new 返回值<内容,错误>($内容,$状态);
    }
    
    public static <内容,错误 extends Exception> 返回值<内容,错误> 创建(错误 $错误) {
        return new 返回值<内容,错误>($错误);
    }
    
    public 内容 取内容() {
        return 内容;
    }
    
    public 错误 取错误() {
        return 错误;
    }
    
    public boolean 成功() {
        return 成功;
    }
    
}
