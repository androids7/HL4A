package 间.网络;

import java.io.File;
import 间.工具.文件;
import 间.工具.错误;
import 间.收集.哈希表;
import java.util.Map;

public class 连接工厂 {
    
    private String 地址;
    private String 模式;
    private 哈希表<String,String> 请求头表 = new 哈希表<>();
    private 哈希表<String,String> Cookie表 = new 哈希表<>();
    private 哈希表<String,String> 参数表 = new 哈希表<>();
    private 哈希表<String,String> 文件表 = new 哈希表<>();
    
    public void 地址(String $地址) {
        地址 = $地址;
    }
    
    public void 模式(String $模式) {
        模式 = $模式;
    }
    
    public void 请求头(String $名称,String $内容) {
        请求头表.设置($名称, $内容);
    }

    public void 参数(String $名称,String $内容) {
        参数表.设置($名称, $内容);
    }

    public void Cookie(String $名称,String $内容) {
        Cookie表.设置($名称, $内容);
    }

    public void 文件(String $名称,String $文件) {
        if (!文件.是文件($文件)) 错误.内容("文件不存在:" + $文件);
        文件表.设置($名称, $文件);
    }
    
    public 连接 新建() {
        连接 $新建 = new 连接();
        if (地址 == null) {
            $新建.地址(地址);
        }
        if (模式 != null) {
            $新建.模式(模式);
        }
        for (Map.Entry<String,String> $单个 : 请求头表.entrySet()) {
            $新建.请求头($单个.getKey(),$单个.getValue());
        }
        for (Map.Entry<String,String> $单个 : Cookie表.entrySet()) {
            $新建.Cookie($单个.getKey(),$单个.getValue());
        }
        for (Map.Entry<String,String> $单个 : 参数表.entrySet()) {
            $新建.参数($单个.getKey(),$单个.getValue());
        }
        for (Map.Entry<String,String> $单个 : 文件表.entrySet()) {
            $新建.文件($单个.getKey(),$单个.getValue()
            );
        }
        return $新建;
    }
    
    public 连接 新建(String $地址) {
        if ($地址 == null) return 新建();
        if (地址 == null) {
            $地址 = 地址 + $地址;
        }
        return 新建().地址($地址);
    }
    
    public 连接 新建(String $地址,String $模式) {
        return 新建($地址).模式($模式);
    }
    
}
