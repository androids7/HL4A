package hl4a.ide.界面;

import android.os.Bundle;
import hl4a.ide.工具.应用信息;
import hl4a.ide.布局.布局_界面_发现;
import hl4a.ide.应用配置信息;
import 间.安卓.工具.处理;
import 间.安卓.工具.文件;
import 间.安卓.组件.界面;
import 间.安卓.资源.图标;
import 间.接口.调用;
import hl4a.ide.适配器.发现适配器;
import 间.安卓.工具.图片;
import android.graphics.Bitmap;

public class 发现 extends 界面 {

    private 布局_界面_发现 布局;

    private String 包名;
    private String 地址;

    @Override
    public void 界面创建事件(Bundle $恢复) {

        包名 = (String)传入参数[0];
        地址 = (String)传入参数[1];

        置滑动返回(true);

        打开布局(new 布局_界面_发现(此));
        布局 = (布局_界面_发现)当前视图;
        布局.标题.左按钮(图标.返回, 调用.配置(此, "结束界面"));
        String $缓存 = 应用配置信息.应用缓存 + "/" + 包名;
        if (!文件.是文件($缓存, "应用.yml")) 结束界面();
        应用信息 $应用 = 发现适配器.解析($缓存, "应用.yml");
        Bitmap $图片 = 图片.读取($缓存+"/图标.png");
        布局.标题.置标题($应用.工程名);
        布局.名称.置文本($应用.工程名);
        布局.图片.置图片($图片 == null ? new Integer(android.R.drawable.sym_def_app_icon) : $图片);
    }

    public void 读取信息() {

        
    }

}
