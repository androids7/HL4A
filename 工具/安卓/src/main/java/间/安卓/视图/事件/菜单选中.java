package 间.安卓.视图.事件;

import android.view.*;
import android.view.MenuItem.*;
import 间.接口.*;

public class 菜单选中 implements OnMenuItemClickListener {

    方法 事件;
    
    public 菜单选中(方法 $方法) {
        事件 = $方法;
    } 
    
    @Override
    public boolean onMenuItemClick(MenuItem $选项) {
        return 调用.事件(事件,$选项) == true;
    }

}
