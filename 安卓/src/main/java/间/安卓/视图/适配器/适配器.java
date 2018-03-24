package 间.安卓.视图.适配器;

import android.view.View;
import 间.接口.方法;
import 间.收集.哈希表;
import 间.接口.调用;
import 间.工具.错误;

public class 适配器 extends 基本适配器 {

    private View 视图;
    private 方法 创建;
    private 方法 处理;

    public 适配器(View $视图,方法 $处理) {
        视图 = $视图;
        处理 = $处理;
    }

    public 适配器(方法 $创建,方法 $处理) {
        创建 = $创建;
        处理 = $处理;
    }

    @Override
    public View 创建() {
        if (视图 != null) return 视图;
        视图 = (View)调用.事件(创建);
        if (视图 == null) {
            错误.内容("方法 [创建] 没有返回视图");
        }
        return 视图;
    }

    @Override
    public View 处理(View $视图,哈希表<String, Object> $参数) {
        return (View)调用.事件(处理, $视图, $参数);
    }

}
