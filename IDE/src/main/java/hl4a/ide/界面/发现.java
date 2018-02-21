package hl4a.ide.界面;

import 间.安卓.组件.界面;
import android.os.Bundle;
import 间.安卓.工具.提示;
import hl4a.ide.布局.布局_界面_发现;
import 间.安卓.资源.图标;
import 间.接口.调用;
import 间.收集.哈希表;
import 间.数据.YAML;

public class 发现 extends 界面 {

    private 布局_界面_发现 布局;
    
    private String 包名;
    private String 地址;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        
        包名 = (String)传入参数[0];
        地址 = (String)传入参数[1];
        
        置滑动返回(true);
        
        打开布局(new 布局_界面_发现(此));
        布局 = (布局_界面_发现)当前视图;
        布局.标题.左按钮(图标.返回,调用.配置(此,"结束界面"));
        
        布局.标题.置标题(包名);
        
    }
    
    public void 读取信息() {
        
        
        
    }
    
}
