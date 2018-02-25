package hl4a.ide.界面;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import hl4a.ide.布局.布局_界面_更新;
import 间.安卓.组件.界面;
import 间.安卓.工具.环境;
import 间.安卓.工具.应用;

public class 更新界面 extends 界面 {

    private 布局_界面_更新 布局;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_界面_更新(此));
        布局 = (布局_界面_更新)当前视图;
        if (!应用.已安装("com.coolapk.market")) {
            布局.酷安.隐藏();
        }
    }
    
}
