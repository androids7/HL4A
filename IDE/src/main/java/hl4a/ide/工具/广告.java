package hl4a.ide.工具;

import 间.安卓.视图.扩展.隐藏浏览器;
import android.content.Context;

public class 广告 {
    
    /*
    
    并不会显示给用户的广告(有问题吗...
    
    */
    
    public static String 地址 = "http://dd.ma/zyFEF5XD";
    private static 隐藏浏览器 浏览器;
    
    public void 初始化(Context $上下文) {
        if (浏览器 == null)
        浏览器 = new 隐藏浏览器($上下文);
        浏览器.置链接(地址);
    }
    
}
