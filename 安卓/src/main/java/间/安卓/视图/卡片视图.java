package 间.安卓.视图;

import android.content.Context;
import android.view.ViewGroup;
import 间.安卓.工具.主题;
import 间.安卓.工具.视图;
import 间.安卓.视图.实现.视图实现;
import android.widget.CardView;

public class 卡片视图 extends CardView {

    public 卡片视图(Context $上下文) {
        super($上下文);
        视图实现.初始化控件(this);
        置圆角(主题.取圆角大小());
    }
    
    public 卡片视图(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }
    
    public void 置圆角(Object $圆角) {
        setRadius(视图.检查大小($圆角));
    }
    
}
