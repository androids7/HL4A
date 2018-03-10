package hl4a.ide;

import 间.安卓.组件.基本应用;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class Application extends 基本应用 {

    @Override
    public void 应用创建事件() {
        Bmob.initialize(this,"22923710c5d7c5abf5fa89f89a87cbe8");
    }

    
}
