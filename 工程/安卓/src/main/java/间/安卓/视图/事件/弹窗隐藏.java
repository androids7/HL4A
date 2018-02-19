package 间.安卓.视图.事件;

import android.content.DialogInterface;
import 间.接口.方法;
import 间.接口.调用;

public class 弹窗隐藏 implements DialogInterface.OnDismissListener {

    private 方法 事件;
    
    public 弹窗隐藏(方法 $隐藏) {
        事件 = $隐藏;
    }
    
    @Override
    public void onDismiss(DialogInterface $视图) {
        调用.事件(事件,$视图);
    }
    
}
