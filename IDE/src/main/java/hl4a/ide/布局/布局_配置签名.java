package hl4a.ide.布局;

import android.content.Context;
import 间.安卓.视图.线性布局;
import 间.安卓.视图.编辑框;
import 间.安卓.工具.主题;

public class 布局_配置签名 extends 线性布局 {
    
    public 编辑框 地址;
    public 编辑框 密码;
    public 编辑框 别名;
    public 编辑框 别名密码;
    
    public 布局_配置签名(Context $上下文) {
        super($上下文);
        置左右填充(主题.取默认填充());
        地址 = new 编辑框(this);
        地址.置默认文本("相对于项目目录的地址");
        地址.置输入类型("文本");
        别名 = new 编辑框(this);
        别名.置默认文本("秘钥仓库里你使用的别名");
        别名.置输入类型("文本");
        密码 = new 编辑框(this);
        密码.置默认文本("秘钥仓库文件的密码");
        密码.置输入类型("文本-密码");
        别名密码 = new 编辑框(this);
        别名密码.置默认文本("秘钥仓库里该别名的密码");
        别名密码.置输入类型("文本-密码");
    }
    
}
