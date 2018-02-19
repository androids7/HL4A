package 间.安卓.弹窗;

import android.app.*;
import android.content.*;
import android.view.*;
import android.view.WindowManager.*;
import 间.接口.*;
import 间.安卓.视图.事件.*;
import 间.安卓.视图.扩展.*;
import 间.安卓.资源.布局.*;
import 间.工具.*;

public class 基本弹窗 extends AlertDialog {
    
    public Context 上下文;
    public 布局_基本弹窗 布局;
    
    
    
    public 基本弹窗(Context $上下文) {
        super($上下文);
        布局 = new 布局_基本弹窗($上下文);
        setView(布局);
        置可关闭(true);
    }
    
    public void 置标题(String $标题) {
        布局.标题.置标题($标题);
        布局.标题.显示();
    }
    
    public void 置内容(String $内容) {
        布局.底层.removeAllViews();
        布局.底层.置填充(0,"16dp","16dp","16dp");
        new 滚动文本(布局.底层).置文本($内容);
    }
    
    public void 置内容(View $视图) {
        布局.底层.removeAllViews();
        布局.底层.加入子元素($视图);
    }
    
    public void 置左按钮(String $文本,方法 $单击) {
        布局.控制.显示();
        布局.左按钮.文本.置文本($文本);
        布局.左按钮.置单击事件(new 弹窗按钮单击(this,$单击));
    }
    
    public void 置中按钮(String $文本,方法 $单击) {
        布局.控制.显示();
        布局.中按钮.文本.置文本($文本);
        布局.中按钮.置单击事件(new 弹窗按钮单击(this,$单击));
    }
    
    public void 置右按钮(String $文本,方法 $单击) {
        布局.控制.显示();
        布局.右按钮.文本.置文本($文本);
        布局.右按钮.置单击事件(new 弹窗按钮单击(this,$单击));
    }
    
    public void 置隐藏事件(方法 $事件) {
        setOnDismissListener(new 弹窗隐藏($事件));
    }
    
    public 方法 显示 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            显示();
            return null;
        }
    };
    
    public 方法 隐藏 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            隐藏();
            return null;
        }
    };
    
    
    public void 显示() {
        show();
        Window $窗口 = getWindow();
        LayoutParams $设置 = $窗口.getAttributes();
        $设置.flags = 2;
        $设置.width = -1;
        $设置.height = -2;
        $窗口.setAttributes($设置);
    }

    public void 置可关闭(boolean $是否) {
        setCancelable($是否);
        setCanceledOnTouchOutside($是否);
    }

    public void 隐藏() {
        dismiss();
    }
    
    public static class 弹窗按钮单击 implements 方法 {
        
        基本弹窗 弹窗;
        方法 单击;
        
        public 弹窗按钮单击(基本弹窗 $弹窗,方法 $单击) {
            弹窗 = $弹窗;
            单击 = $单击;
        }
        
        @Override
        public Object 调用(Object[] $参数) {
            调用.事件(单击,数组.添加($参数,0,弹窗));
            return null;
        }
        
        
    }

}
