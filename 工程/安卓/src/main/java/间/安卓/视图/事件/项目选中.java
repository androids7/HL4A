package 间.安卓.视图.事件;

import android.widget.AdapterView.*;
import android.view.*;
import android.widget.*;
import 间.接口.*;

public class 项目选中 implements OnItemSelectedListener {

    方法 选中;
    方法 取消;
    
    public 项目选中(方法 $选中,方法 $没选中) {
        选中 = $选中;
        取消 = $没选中;
    }
    
    
    @Override
    public void onItemSelected(AdapterView<?> $适配器视图,View $视图,int $键值,long $ID) {
        调用.事件(选中,$适配器视图,$视图,$键值,$ID);
    }

    @Override
    public void onNothingSelected(AdapterView<?> $适配器视图) {
        调用.事件(取消,$适配器视图);
    }

}
