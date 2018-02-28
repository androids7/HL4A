package 间.安卓.视图.扩展;

import android.content.Context;
import android.view.ViewGroup;
import 间.安卓.工具.主题;
import 间.安卓.工具.颜色;
import 间.安卓.视图.代码框;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.水平滚动;
import 间.安卓.视图.线性布局;
import 间.接口.方法;

public class 符号栏 extends 水平滚动 {

    public static String[] 符号 = {
        "(",")","[","]","{","}",";",
        "\"","=",":",".",",","_","+",
        "-","*","/","\\","%","#","^",
        "$","?","<",">","~","'"};

    public 线性布局 底层;

    public 符号栏(Context $上下文) {
        super($上下文);
        置高度("自动");
        置背景(主题.取主题颜色().取基本色());
        底层 = new 线性布局(this);
        底层.置方向("水平");
        创建(符号);
    }

    public 符号栏(ViewGroup $父视图) {
        this($父视图.getContext());
        加入到($父视图);
    }

    public 代码框 框;

    public void 置代码框(代码框 $框) {
        框 = $框;
    }

    方法 单击 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            String $符号 = ((文本视图)((线性布局)$参数[0]).取子元素("文本")).取文本();
            switch ($符号) {
                case "格式化":;break;
                default:
                    框.插入($符号);
            }
            return null;
        }
    };

    public void 创建(String... $所有) {
        for (String $单个 : $所有) {
            线性布局 $符号 = new 线性布局(底层);
            $符号.置宽度("自动");
            $符号.置高度("自动");
            $符号.置重力("中间");
            $符号.置背景("透明");
            $符号.置填充("6dp", "8dp", "16dp", "16dp");
            $符号.置单击事件(单击);
            文本视图 $文本 = new 文本视图($符号);
            $文本.置标签("文本");
            $文本.置文本($单个);
            $文本.置文本颜色(颜色.白色);
        }
    }


}
