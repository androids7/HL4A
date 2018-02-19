package 间.安卓.视图.事件;

import android.text.*;
import 间.接口.*;

public class 文本改变 implements TextWatcher {

    public 方法 改变前 = null;
    public 方法 已改变 = null;
    public 方法 改变后 = null;

    public 文本改变(方法 $事件) {
        已改变 = $事件;
    }

    public 文本改变(方法 $改变前,方法 $已改变,方法 $改变后) {
        改变前 = $改变前;
        已改变 = $已改变;
        改变后 = $改变后;
    }

    @Override
    public void beforeTextChanged(CharSequence p1,int p2,int p3,int p4) {
        调用.事件(改变前, p1, p2, p3, p4);
    }

    @Override
    public void onTextChanged(CharSequence p1,int p2,int p3,int p4) {
        调用.事件(已改变,p1, p2, p3, p4);
    }

    @Override
    public void afterTextChanged(Editable p1) {
        调用.事件(改变后,p1);
    }

}
