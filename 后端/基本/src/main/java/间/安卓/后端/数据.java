package 间.安卓.后端;

import com.avos.avoscloud.后端错误;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import 间.安卓.工具.文件;
import 间.安卓.工具.线程;
import 间.工具.字节;
import 间.工具.断言;
import 间.接口.方法;
import 间.接口.返回值;
import 间.接口.调用;
import com.avos.avoscloud.SaveCallback;

public class 数据 extends AVObject {
    
    public 数据(String $表名) {
        super($表名);
    }
    
    public void 设置(String $键值,Object $内容) {
        put($键值,$内容);
    }
    
    public <类型> 类型 读取(String $键值) {
        return (类型)get($键值);
    }
    
    public void 置权限(权限 $权限) {
        setACL($权限);
    }
    
    public void 置文件(String $键值,String $地址) {
        断言.为真(文件.是文件($地址),"参数2 [地址](%s) 不是文件",$地址);
        put($键值,new AVFile(文件.取名称($地址),字节.读取($地址)));
    }
    
    public 用户 取用户(String $键值) {
        return getAVUser($键值,用户.class);
    }
    
    public 返回值<byte[]> 取文件(String $键值) {
        AVFile $文件 = getAVFile($键值);
        try {
            return 返回值.创建($文件.getData());
        } catch (后端错误 $错误) {
            return 返回值.创建(null,$错误);
        }
    }
    
    public void 取文件(final String $键值,final 方法 $回调) {
        new 线程(new 方法(){
                @Override
                public Object 调用(Object[] $参数) {
                    调用.事件($回调,取文件($键值));
                    return null;
                }
            }).启动();
    }
    
    public 返回值<Void> 同步保存() {
        try {
            save();
            return 返回值.创建(null);
        } catch (后端错误 $错误) {
            return 返回值.创建(null,$错误);
        }
    }
    
    public void 保存(final 方法 $回调) {
        saveInBackground(new SaveCallback() {
                @Override
                public void done(后端错误 $错误) {
                    调用.事件($回调,返回值.创建(null,$错误));
                }
            });
    }
   
    public void 保存() {
        saveInBackground();
    }
    
}
