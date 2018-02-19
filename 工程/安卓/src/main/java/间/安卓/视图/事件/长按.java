package 间.安卓.视图.事件;

import android.view.*;
import android.view.View.*;
import 间.接口.*;

public class 长按 implements OnLongClickListener {

    private 方法 事件;
    
    public 长按(方法 $事件) {
        事件 = $事件;
    }
    
    @Override
    public boolean onLongClick(View $对象) {
        // TODO: Implement this method
        return (调用.事件(事件,$对象) == true);
    }

    
}
