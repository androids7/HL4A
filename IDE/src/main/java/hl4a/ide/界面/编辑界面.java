package hl4a.ide.界面;

import android.os.*;
import android.view.*;
import hl4a.ide.布局.*;
import hl4a.ide.工具.*;
import java.io.*;
import 间.安卓.工具.*;
import 间.安卓.弹窗.*;
import 间.安卓.组件.*;
import 间.安卓.脚本.*;
import 间.安卓.视图.*;
import 间.安卓.视图.扩展.*;
import 间.安卓.视图.适配器.*;
import 间.安卓.资源.*;
import 间.工具.*;
import 间.接口.*;

import 间.安卓.工具.文件;
import 间.安卓.工具.设置;

public class 编辑界面 extends 界面 {

    JavaScript 环境 = new JavaScript();

    String 打开;
    工程 当前;
    布局_编辑界面 布局;
    布局_新建文件 新建布局;
    弹出菜单 菜单;
    线性按钮 侧滑右按钮;
    基本弹窗 新建弹窗;

    public boolean 检查代码(String $目录) {
        if ($目录 == null) {
            $目录 = 当前.取源码();
        }
        File[] $文件 = 文件.取文件列表($目录);
        try {
            for (File $单个 : $文件) {
                if ($单个.isDirectory()) {
                    if (!检查代码($单个.getPath()))return false;
                } else if ($单个.getName().toLowerCase().endsWith(".js")) {
                    环境.编译代码(字符.读取($单个.getPath()), $单个.getName());
                }
            }
        } catch (Exception $错误) {
            基本弹窗 $弹窗 = new 基本弹窗(此);
            $弹窗.置标题("又语法错误啦(ㅍ_ㅍ)");
            $弹窗.置内容($错误.getMessage());
            $弹窗.置右按钮("我知道了",调用.配置($弹窗,"隐藏"));
            $弹窗.显示();
            return false;
        }
        return true;
    }

    @Override
    public void 界面创建事件(Bundle $数据) {
        String $名称 = (String)传入参数[0];
        if (!工程.检查($名称)) {
            提示.警告("没有那样的工程 :" + $名称);
            结束界面();
            return;
        }
        当前 = 工程.读取($名称);
        布局 = new 布局_编辑界面(此);
        布局.标题.置标题("编辑工程");
        布局.标题.右按钮(图标.启动, 运行代码);
        布局.标题.右按钮(图标.撤销,撤销);
        布局.标题.右按钮(图标.重做,重做);
        布局.侧滑标题.置标题("打开文件");
        打开 = (String)设置.读取("上次打开");
        打开 = 打开 == null ? "入口.js" : 打开;
        界面刷新事件();
        布局.适配器 = new 文件适配器(布局.列表, 文件单击, 长按, 当前.取地址(工程.源码目录));
        布局.列表.置适配器(布局.适配器);
        侧滑右按钮 = 布局.侧滑标题.右按钮(打开侧滑菜单);
        菜单 = new 弹出菜单(侧滑右按钮);
        菜单.添加("新建文件/夹", 新建文件);
        打开布局(布局);
        新建布局 = new 布局_新建文件(此);
        新建弹窗 = new 基本弹窗(此);
        新建弹窗.置标题("新建文件/文件夹");
        新建弹窗.置内容(新建布局);
        新建弹窗.置左按钮("取消", 新建弹窗.隐藏);
        新建弹窗.置中按钮("文件", 文件新建);
        新建弹窗.置右按钮("目录", 目录新建);
    
        布局.代码.置代码选中事件(调用.代理(this,"代码选中"));
    }
    
    
    
    public void 代码选中(Boolean active,Integer selStart,Integer selEnd) {
        if (active) {
            布局.标题.隐藏();
            布局.符号.隐藏();
            布局.选中标题.显示();
        } else {
            布局.标题.显示();
            布局.符号.显示();
            布局.选中标题.隐藏();
        }
    }
    
    方法 长按 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            View $视图 = (View)$参数[4];
            final String $地址 = (String)$参数[0];
            final 弹出菜单 $操作菜单 = new 弹出菜单($视图);
            $操作菜单.添加("删除", new 方法() {
                    @Override
                    public Object 调用(Object[] $参数) {
                        文件.删除($地址);
                        提示.普通("删除成功 ~");
                        布局.适配器.刷新();
                        界面刷新事件();
                        $操作菜单.隐藏();
                        return null;
                    }
                });
            $操作菜单.显示();
            return null;
        }
    };
    
    方法 撤销 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            布局.代码.撤销();
            return null;
        }
    };
    
    方法 重做 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            布局.代码.重做();
            return null;
        }
    };

    方法 文件单击 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            界面遮挡事件();
            String $附加 = (String)$参数[2];
            String $名称 = (String)$参数[3];
            打开 = new File($附加 + "/" + $名称).getPath();
            界面刷新事件();
            设置.保存("上车打开",打开);
            提示.普通("打开文件 " + $名称);
            return null;
        }
    };

    方法 文件新建 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            String $内容 = 新建布局.内容.取文本();
            if ($内容.equals("")) {
                提示.普通("请不要留空 ~");
                return null;
            }
            String $目标 = 当前.取地址(工程.源码目录, 布局.适配器.附加, $内容);
            if (文件.存在($目标)) {
                提示.普通("文件/夹 已存在 ~");
                return null;
            }
            文件.创建文件($目标);
            if (文件.是文件($目标)) {
                提示.普通("文件创建成功 ~");
                布局.适配器.刷新();
                新建弹窗.隐藏();
            } else {
                提示.警告("创建错误 ~");
            }
            return null;
        }
    };

    方法 目录新建 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            String $内容 = 新建布局.内容.取文本();
            if ($内容.equals("")) {
                提示.普通("请不要留空 ~");
                return null;
            }
            String $目标 = 当前.取地址(工程.源码目录, 布局.适配器.附加, $内容);
            if (文件.存在($目标)) {
                提示.普通("文件/夹 已存在 ~");
                return null;
            }
            文件.创建目录($目标);
            if (文件.是目录($目标)) {
                提示.普通("目录创建成功 ~");
                布局.适配器.刷新();
                新建弹窗.隐藏();
            } else {
                提示.警告("创建错误 ~");
            }
            return null;
        }
    };
    
    
    方法 运行代码 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            界面遮挡事件();
            if (检查代码(null)) {
                文件.替换地址("@",当前.取地址("资源") + "/");
                跳转脚本(当前.取源码("入口.js"));
            } 
            return null;
        }
    };

    @Override
    public void 界面遮挡事件() {
        String $文件 = 当前.取源码(打开);
        if (!文件.是文件($文件)) {
            return;
        }
        布局.代码.保存(当前.取源码(打开));
    }

    @Override
    public void 界面刷新事件() {
        if (!文件.是文件(当前.取地址(工程.配置文件))) {
            提示.警告("工程已损坏！");
            置返回值(233);
            //如果Proj检测到返回233就直接结束
            //避免提示两次已损坏影响体验
            结束界面();
            return;
        }
        if (!文件.是文件(当前.取源码(打开))) {
            打开 = "入口.js";
            提示.普通("正在创建入口 ~");
            字符.保存(当前.取源码(打开), 字符.读取("#assets/client.js"));
        }
        布局.代码.读入(当前.取源码(打开));
        if (布局.适配器 != null) {
            布局.适配器.刷新();
        }
    }

    方法 新建文件 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            新建弹窗.显示();
            菜单.隐藏();
            return null;
        }
    };

    方法 打开侧滑菜单 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            菜单.显示();
            return null;
        }
    };

}
