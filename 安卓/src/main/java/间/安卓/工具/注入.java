package 间.安卓.工具;

import dalvik.system.*;
import 间.工具.*;
import java.io.*;

public class 注入 {
    
    public static DexClassLoader 注入(String $地址) {
        if (!文件.是文件($地址)) return null;
        $地址 = 文件.检查地址($地址);
        if (!字符.以开始($地址,"/data/data/")) {
            String $散列 = 散列.文件("MD5",$地址);
            String $文件 = 文件.取数据目录("注入",$散列);
            if (!文件.是文件($文件)) {
                文件.复制($地址,$文件);
            }
            $地址 = $文件;
        }
        return new DexClassLoader($地址,文件.取缓存目录("缓存"),文件.取数据目录("lib"),环境.取应用().getClassLoader());
    }
    
}
