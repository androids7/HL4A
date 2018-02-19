package 间.安卓.视图.事件;

import android.widget.AdapterView.*;
import android.view.*;
import android.widget.*;
import 间.接口.*;

public class 项目长按 implements OnItemLongClickListener {

    方法 长按;
    
    public 项目长按(方法 $方法) {
        长按 = $方法;
    }
    
    @Override
    public boolean onItemLongClick(AdapterView<?> $适配器视图,View $视图,int $键值,long $ID) {
        Object $返回值 = 调用.事件(长按,$适配器视图,$视图,$键值,$ID);
        return true;
    }

}
