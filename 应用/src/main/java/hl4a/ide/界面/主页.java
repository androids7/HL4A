package hl4a.ide.界面;

import 间.安卓.组件.界面;
import android.os.Bundle;
import hl4a.ide.布局.布局_主页;
import 间.接口.调用;
import 间.安卓.工具.提示;
import 间.安卓.后端.用户;
import 间.安卓.工具.翻墙;

public class 主页 extends 界面 {

    private 布局_主页 布局;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_主页(此));
        布局 = 取视图();
        检查用户();
        布局.翻墙按钮.置单击事件(调用.配置(this,"启动"));
        布局.停止翻墙.置单击事件(调用.配置(this,"停止"));
    }
    
    public void 启动() {
        翻墙.启动(此,"#assets/hl4a.json");
        提示.普通("正在启动 ~");
    }
    
    public void 停止() {
        翻墙.停止();
        提示.普通("已停止 ~");
    }

    private void 检查用户() {
        if (用户.取当前用户() == null) {
            //布局.注册底层.显示();
        }
    }
    
}
