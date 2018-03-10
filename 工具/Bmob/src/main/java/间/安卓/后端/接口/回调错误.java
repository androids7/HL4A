package 间.安卓.后端.接口;

import cn.bmob.v3.exception.BmobException;

public class 回调错误 extends BmobException {

    public 回调错误(Throwable $错误) {
        super($错误);
    }
    
    public 回调错误(int $错误码,String $信息) {
        super($错误码, $信息);
    }
    
    public int 取错误码() {
        return getErrorCode();
    }
    
    public String 取信息() {
        return getMessage();
    }

    @Override
    public String toString() {
        return "错误码: " + getErrorCode() + "\n信息: " + getMessage();
    }

}
