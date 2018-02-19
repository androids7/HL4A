package 间.安卓.视图.事件;

import android.view.*;
import android.view.View.*;
import 间.接口.*;

public class 单击 implements OnClickListener {

    private 方法 事件;
    
    public 单击(方法 $事件) {
        事件 = $事件;
    }
    
    @Override
    public void onClick(View $对象) {
        调用.事件(事件,$对象);
    }

    
}
