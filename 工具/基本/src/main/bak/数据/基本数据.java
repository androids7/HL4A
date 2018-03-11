package 间.数据;

import 间.收集.哈希表;
import 间.工具.文件;
import 间.工具.字符;

public abstract class 基本数据 {
    
    public abstract 哈希表 解析(String $内容);
    public abstract String 转换(哈希表 $数据);
    
    public 哈希表 读取(String $地址) {
        if (文件.是文件($地址)) {
            return 解析(字符.读取($地址));
        }
        return null;
    }
    
    public void 保存(String $地址,哈希表 $数据) {
        字符.保存($地址,转换($数据));
    }
    
}
