package 间.安卓.资源.布局;

import android.content.Context;
import 间.安卓.工具.颜色;
import 间.安卓.视图.侧滑布局;
import 间.安卓.视图.扩展.标题栏;
import 间.安卓.视图.线性布局;
import 间.接口.调用;

public class 布局_侧滑界面 extends 侧滑布局 {
    
    public 线性布局 侧滑;
    public 线性布局 底层;
    
    public 标题栏 侧滑标题;
    public 标题栏 标题;
    
    public 布局_侧滑界面(Context $上下文) {
        super($上下文);
        线性布局 $主页 = new 线性布局(this);
        标题 = new 标题栏($主页);
        底层 = new 线性布局($主页);
        线性布局 $侧滑 = new 线性布局(this);
        $侧滑.置布局重力("开始");
        侧滑标题 = new 标题栏($侧滑);
        侧滑 = new 线性布局($侧滑);
        侧滑.置背景颜色(颜色.白色);
        侧滑.置单击事件(调用.空方法);
    }
    
}
