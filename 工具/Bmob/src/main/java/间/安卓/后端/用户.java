package 间.安卓.后端;

import cn.bmob.v3.BmobUser;
import 间.安卓.后端.接口.保存回调;
import 间.安卓.后端.接口.邮箱验证回调;
import 间.安卓.工具.环境;
import 间.接口.方法;
import 间.安卓.后端.接口.更新回调;

public class 用户 extends BmobUser {
    
    public Integer ae = 0;
    public String sg = "还没有签名 ~";
    
    public String 取用户名() {
        return getUsername();
    }
    
    public void 置用户名(String $用户名) {
        setUsername($用户名);
    }
    
    public void 置密码(String $密码) {
        setPassword($密码);
    }
    
    public String 取邮箱() {
        return getEmail();
    }
    
    public void 置邮箱(String $邮箱) {
        setEmail($邮箱);
    }
    
    public boolean 邮箱已验证() {
        return getEmailVerified();
    }
    
    public void 置邮箱已验证(boolean $是否) {
        setEmailVerified($是否);
    }
    
    public void 验证邮箱(String $邮箱,方法 $回调) {
        requestEmailVerify(环境.取应用(),$邮箱,new 邮箱验证回调($回调));
    }
    
    public int 取权限等级() {
        return ae;
    }
    
    public void 置权限等级(int $等级) {
        ae = $等级;
    }
    
    public String 取签名() {
        return sg;
    }

    public void 置签名(String $签名) {
        sg = $签名;
    }
    
    public String 取注册时间() {
        return getCreatedAt();
    }
    
    public void 更新(方法 $回调) {
        update(环境.取应用(),new 更新回调($回调));
    }
    
    public void 注册(方法 $回调) {
        signUp(环境.取应用(),new 保存回调($回调));
    }
    
    public void 登录(方法 $回调) {
        login(环境.取应用(),new 保存回调($回调));
    }
    
    public void 登出() {
        logOut(环境.取应用());
    }
    
}
