package 间.安卓.后端;

import com.avos.avoscloud.AVRole;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.后端错误;
import 间.安卓.工具.线程;
import 间.接口.方法;
import 间.接口.调用;
import 间.接口.返回值;
import 间.收集.无序表;
import 间.收集.集合;

public class 权限组 {

    private static 无序表<String,权限组> 所有权限组 = new 无序表<>();
    private static volatile Boolean 已准备 = false;
    private static void 准备() {
        synchronized (已准备) {
            if (已准备) return;
            查询<AVRole> $所有 = new 查询<>("_Role");
            返回值 $返回 = $所有.查询();
            if ($返回.成功()) {
                集合<AVRole> $集合 = $所有.查询().取内容();
                for (AVRole $单个 : $集合) {
                    所有权限组.设置($单个.getName(), new 权限组($单个));
                }
                已准备 = true;
            }
        }
    }

    public static 权限组 读取(String $单个) {
        if (!已准备) 准备();
        return 所有权限组.读取($单个);
    }

    public static void 读取(final String $单个,final 方法 $回调) {
        new 线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    准备();
                    调用.事件($回调, 读取($单个));
                    return null;
                }
            }).启动();
    }

    public AVRole 权限组;

    public 权限组(AVRole $权限组) {
        权限组 = $权限组;
    }

    public 权限组 加入(用户 $用户) {
        权限组.getUsers().add($用户);
        return this;
    }

    public 权限组 删除(用户 $用户) {
        权限组.getUsers().remove($用户);
        return this;
    }

    private 无序表<String,Boolean> 存在缓存 = new 无序表<>();

    public boolean 存在(String $用户) {
        try {
            if (!存在缓存.检查键值($用户))
                存在缓存.设置($用户, !权限组.getUsers().getQuery().whereEqualTo("username", $用户).find().isEmpty());
            return 存在缓存.读取($用户);
        } catch (后端错误 $错误) {}
        return false;
    }

    public boolean 存在(用户 $用户) {
        return 存在($用户.取用户名());
    }

    public 返回值<Void> 同步保存() {
        try {
            权限组.save();
        } catch (后端错误 $错误) {
            return 返回值.创建(null, $错误);
        }
        return null;
    }

    public void 保存() {
        权限组.saveInBackground();
    }

    public void 保存(final 方法 $回调) {
        权限组.saveInBackground(new SaveCallback() {
                @Override
                public void done(后端错误 $错误) {
                    调用.事件($回调, 返回值.创建(null, $错误));
                }
            });
    }

}
