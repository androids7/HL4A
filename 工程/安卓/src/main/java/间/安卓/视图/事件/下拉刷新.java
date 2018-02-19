package 间.安卓.视图.事件;

import android.support.v4.widget.*;
import 间.接口.*;

public class 下拉刷新 implements SwipeRefreshLayout.OnRefreshListener {

    方法 方法;
    
    public 下拉刷新(方法 $事件) {
        方法 = $事件;
    }
    
    @Override
    public void onRefresh() {
        调用.事件(方法);
    }
    
}
