package 间.安卓.视图.适配器;

import android.content.*;
import android.view.*;
import 间.安卓.资源.布局.*;

public class 复选适配器 extends 数组适配器 {
    
    public 复选适配器(Context $上下文) {
        super($上下文);
    }

    @Override
    public View 创建() {
        return new 布局_适配器_复选(上下文);
    }
    
}
