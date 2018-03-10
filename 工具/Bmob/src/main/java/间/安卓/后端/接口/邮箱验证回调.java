package 间.安卓.后端.接口;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.EmailVerifyListener;
import 间.工具.回调;
import 间.接口.方法;
import 间.接口.调用;

public class 邮箱验证回调 implements EmailVerifyListener {

    private 方法 回调;

    public 邮箱验证回调(方法 $回调) {
        回调 = $回调;
    }

    @Override
    public void onSuccess() {
        调用.事件(回调, null);
    }

    @Override
    public void onFailure(int $错误码,String $信息) {
        调用.事件(回调, new 回调错误($错误码, $信息));
    }

}

