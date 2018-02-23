package 间.安卓.组件;

import android.os.*;
import 间.接口.*;
import 间.安卓.工具.*;
import 间.安卓.弹窗.*;
import 间.安卓.视图.*;
import 间.安卓.视图.扩展.*;
import 间.安卓.资源.*;

public class 错误界面 extends 基本界面 {

    String 错误内容;

    @Override
    public void onCreate(Bundle $数据) {
        super.onCreate($数据);
        置滑动返回(true);
        线性布局 $底层 = new 线性布局(this);
        $底层.打开(this);
        标题栏 $标题 = new 标题栏(this);
        $标题.置标题("崩溃了(ノДＴ)");
        $标题.加入到($底层);

        $标题.左按钮(图标.返回, new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    错误界面.this.结束界面();
                    return null;
                }
            });

        $标题.右按钮(图标.复制, new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    设备.置剪切板(错误内容);
                    提示.普通("已复制 ~");
                    return null;
                }
            });
        /*
         $标题.右按钮(图标.发送, new 通用方法() {
         @Override
         public Object 调用(Object[] $参数) {
         设备工具.置剪切板(错误内容);
         提示工具.普通("已复制 请发送 ~");
         return null;
         }
         });
         */


        错误内容 = getIntent().getStringExtra("错误");

        if (错误内容 == null) 错误内容 = "未知错误";

        线性布局 $布局 = new 线性布局(this);
        $布局.置宽度("最大");
        $布局.置高度("最大");
        $布局.置填充("16dp");
        $布局.加入到($底层);

        滚动文本 $文本 = new 滚动文本(this);
        $文本.置文本(错误内容);
        $文本.加入到($布局);
        
    }

}
