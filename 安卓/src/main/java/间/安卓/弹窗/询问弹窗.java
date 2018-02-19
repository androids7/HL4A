package 间.安卓.弹窗;

import android.content.Context;
import 间.安卓.视图.事件.单击;
import 间.接口.方法;
import 间.接口.调用;

public class 询问弹窗 extends 基本弹窗 {
    
    private 方法 单击;
    
    public 询问弹窗(Context $上下文) {
        super($上下文);
    }
    
    public void 按钮单击(Object[] $参数) {
        隐藏();
        调用.事件(单击,$参数);
    }
    
    public void 置问题(String $动作,方法 $确定) {
        置问题($动作,$确定,null);
    }

    public void 置问题(String $动作,方法 $确定,方法 $取消) {
        置标题($动作);
        置内容("您真的要" + $动作 + "吗？");
        单击 = $确定;
        置右按钮("确定",调用.代理(this,"按钮单击"));
        置中按钮("取消",$取消 == null ? 隐藏 : $取消);
    }

}
