package 间.安卓.后端;

import com.avos.avoscloud.后端错误;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestEmailVerifyCallback;
import com.avos.avoscloud.SignUpCallback;
import 间.接口.方法;
import 间.接口.调用;
import 间.接口.返回值;
import android.content.Context;
import 间.安卓.工具.环境;
import android.graphics.Bitmap;

public class 用户 extends AVUser {
    
    public 用户() {
        super();
    }
    
    public void 置用户名(String $用户名) {
        setUsername($用户名);
    }

    public String 取用户名() {
        return getUsername();
    }

    public void 置密码(String $密码) {
        setPassword($密码);
    }

    public void 置邮箱(String $邮箱) {
        setEmail($邮箱);
    }

    public String 取邮箱() {
        return getEmail();
    }
    
    public void 置头像(Bitmap $头像) {
        设置("icon",$头像);
    }
    
    public Bitmap 取头像() {
        return 读取("icon");
    }

    public 返回值<Void> 同步注册() {
        try {
            signUp();
        } catch (后端错误 $错误) {
            return 返回值.创建(null,$错误);
        }
        return 返回值.成功;
    }

    public void 注册(final 方法 $回调) {
        signUpInBackground(new SignUpCallback() {
                @Override
                public void done(后端错误 $错误) {
                    if ($错误 == null) {
                        置权限(new 权限(用户.this));
                        保存();
                    }
                    调用.事件($回调, 返回值.创建($错误));
                }
            });
    }

    public void 验证邮箱(String $邮箱) {
        requestEmailVerify($邮箱);
    }

    public void 验证邮箱(String $邮箱,final 方法 $回调) {
        requestEmailVerifyInBackground($邮箱, new RequestEmailVerifyCallback() {
                @Override
                public void done(后端错误 $错误) {
                    调用.事件($回调, 返回值.创建($错误));
                }
            });
    }

    public static 返回值<用户> 同步登录(String $账号,String $密码) {
        try {
            return 返回值.创建(logIn($账号, $密码, 用户.class));
        } catch (后端错误 $错误) {
            return 返回值.创建(null,$错误);
        }
    }

    public static void 登录(String $账号,String $密码,final 方法 $回调) {
        logInInBackground($账号, $密码, new LogInCallback<用户>() {
                @Override
                public void done(用户 $用户,后端错误 $错误) {
                    调用.事件($回调, 返回值.创建($用户,$错误));
                }
            }, 用户.class);
    }


    public static 用户 取当前用户() {
        return getCurrentUser(用户.class);
    }


}
