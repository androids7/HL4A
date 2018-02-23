package hl4a.ide.界面;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import hl4a.ide.工具.更新;
import hl4a.ide.布局.布局_主页_底层;
import hl4a.ide.布局.布局_适配器_发现;
import hl4a.ide.布局.布局_适配器_应用;
import hl4a.ide.适配器.发现适配器;
import hl4a.ide.适配器.应用适配器;
import 间.安卓.工具.处理;
import 间.安卓.工具.应用;
import 间.安卓.工具.提示;
import 间.安卓.工具.文件;
import 间.安卓.工具.线程;
import 间.安卓.弹窗.基本弹窗;
import 间.安卓.弹窗.询问弹窗;
import 间.安卓.组件.界面;
import 间.安卓.视图.弹出菜单;
import 间.接口.调用;
import 间.安卓.弹窗.加载中弹窗;
import 间.网络.连接;
import 间.网络.资源;

public class 主页 extends 界面 {

    private 布局_主页_底层 布局;
    private 应用适配器 适配器;
    private 基本弹窗 安装;
    private 发现适配器 发现;

    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_主页_底层(此));
        布局 = (布局_主页_底层)当前视图;
        适配器 = new 应用适配器(此);
        布局.主页.列表.置适配器(适配器);
        布局.主页.列表.置项目单击事件(调用.代理(this, "项目单击"));
        布局.主页.列表.置项目长按事件(调用.代理(this, "项目长按"));
        安装 = new 基本弹窗(此);
        安装.置标题("Runtime Install");
        安装.置内容("安装/更新应用运行环境 ~");
        安装.置可关闭(false);
        安装.置中按钮("退出", 调用.配置(this, "结束界面"));
        安装.置右按钮("安装", 调用.异步配置(this, "安装环境"));
        界面刷新事件();
        发现 = new 发现适配器(此);
        布局.发现.列表.置适配器(发现);
        布局.发现.列表.置项目单击事件(调用.代理(this, "发现单击"));
        布局.发现.置单击事件(调用.配置(this,"断网重试"));
        断网重试();

        连接.创建("https://baidu.com","GET").异步(调用.代理(this,"xx"));
        
    }
    
    public void xx(资源 $) {
        提示.普通($.文本());
    }
    
    public void 更新回调() {
        
        if (发现.是断网) {
            布局.发现.列表.隐藏();
            布局.发现.提示.显示();
            布局.发现.进度.隐藏();
            // 断网
            
        } else {
            布局.发现.列表.显示();
            布局.发现.提示.隐藏();
            布局.发现.进度.隐藏();
        }
        
    }
    
    public void 断网重试() {
        布局.发现.列表.隐藏();
        布局.发现.提示.隐藏();
        布局.发现.进度.显示();
        new 线程(发现, "更新").置回调(调用.配置(this,"更新回调")).启动();
    }

    public void 发现单击(AdapterView<?> $适配器视图,View $视图,int $键值,long $ID) {
        布局_适配器_发现 $布局 = (布局_适配器_发现)$视图;
        String $包名 = $布局.包名;
        String $地址 = $布局.地址;
        跳转界面(发现.class, $包名, $地址);
    }

    public void 项目单击(AdapterView<?> $适配器视图,View $视图,int $键值,long $ID) {
        hl4a.ide.工具.应用 $内容 = ((布局_适配器_应用)$视图).内容;
        String $地址 = $内容.取源码("入口.js");
        if (文件.是文件($地址)) {
            跳转脚本($地址);
        } else {
            提示.警告("没有入口文件");
        }
    }

    private 询问弹窗 删除;

    public void 项目长按(AdapterView<?> $适配器视图,View $视图,int $键值,long $ID) {
        hl4a.ide.工具.应用 $内容 = ((布局_适配器_应用)$视图).内容;
        弹出菜单 $菜单 = new 弹出菜单($视图);

        删除 = new 询问弹窗(此);
        删除.置问题("删除应用", 调用.配置(this, "删除应用", $内容));
        $菜单.添加("删除", 删除.显示);

        $菜单.显示();
    }

    public void 删除应用(hl4a.ide.工具.应用 $应用) {
        删除.隐藏();
        文件.删除($应用.地址);
        提示.普通("删除完成 ~");
        界面刷新事件();
    }


    @Override
    public void 界面刷新事件() {
        适配器.更新();
        if (适配器.getCount() == 0) {
            布局.主页.底层.隐藏();
            布局.主页.提示.显示();
        } else {
            布局.主页.底层.显示();
            布局.主页.提示.隐藏();
        }
    }

}

