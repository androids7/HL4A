package 间.安卓.视图.事件;

import com.myopicmobile.textwarrior.android.*;
import 间.接口.*;

public class 代码选中 implements OnSelectionChangedListener  {

    方法 事件;
    
    public 代码选中(方法 $方法) {
        事件 = $方法;
    }
    
    @Override
    public void onSelectionChanged(boolean active,int selStart,int selEnd) {
       调用.事件(事件,active,selStart,selEnd);
    }
    
}
