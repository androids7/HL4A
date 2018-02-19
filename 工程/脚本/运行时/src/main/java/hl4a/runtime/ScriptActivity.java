package hl4a.runtime;

import android.os.Bundle;
import 间.安卓.工具.提示;
import 间.安卓.脚本.组件.脚本界面;
import 间.工具.字符;

public class ScriptActivity extends 脚本界面 {

    private Bundle 恢复;
    
    @Override
    public void 界面创建事件(Bundle $恢复) {
        恢复 = $恢复;
        请求权限();
    }

    @Override
    public void 权限回调事件() {
        super.界面创建事件(恢复);
    }
    
    
}
