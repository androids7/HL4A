package 间.安卓.组件;

import android.os.Bundle;
import 间.安卓.资源.布局.布局_初始化;
import 间.安卓.工具.线程;
import 间.安卓.工具.设置;
import 间.安卓.工具.文件;
import 间.安卓.工具.应用;
import 间.工具.ZIP;
import 间.安卓.工具.处理;
import 间.安卓.工具.提示;

public class 启动界面 extends 基本界面 {

    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_初始化(this));
        new 线程(this,"释放文件").启动();
    }
    
    public void 释放文件() {
        if (文件.自身变更("$_系统变更")) {
            文件.删除("#");
            ZIP.解压(应用.取安装包位置(),"#");
        }
        处理.主线程(this,"初始化完成事件");
    }
    
    public void 初始化完成事件() {}
    
}
