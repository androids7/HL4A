package 间.安卓.后端.接口;

import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.datatype.BmobQueryResult;
import 间.接口.方法;
import 间.接口.调用;
import 间.收集.集合;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import java.util.List;
import cn.bmob.v3.BmobObject;

class 数据查询回调 extends FindListener<BmobObject> {

    private 方法 回调;

    public 数据查询回调(方法 $回调) {
        回调 = $回调;
    }

    @Override
    public void onSuccess(List<BmobObject> $列表) {
        调用.事件(回调, null, new 集合<BmobObject>($列表));
    }

    @Override
    public void onError(int $错误码,String $内容) {
        调用.事件(回调, new 回调错误($错误码, $内容), null);
    }

}
