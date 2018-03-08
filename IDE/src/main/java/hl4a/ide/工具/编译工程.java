package hl4a.ide.工具;

/*

import hl4a.ide.工具.工程;
import java.io.File;
import java.io.IOException;
import 间.安卓.工具.处理;
import 间.安卓.工具.应用;
import 间.安卓.工具.提示;
import 间.安卓.工具.文件;
import 间.安卓.工具.环境;
import 间.安卓.弹窗.进度弹窗;
import 间.安卓.弹窗.基本弹窗;
import 间.安卓.组件.基本界面;
import 间.安卓.编译.CLASS;
import 间.安卓.编译.DEX;
import 间.安卓.编译.JSC;
import 间.安卓.编译.打包签名;
import 间.安卓.编译.秘钥签名;
import 间.工具.ZIP;
import 间.工具.字符;
import 间.工具.线程;
import 间.接口.方法;
import 间.安卓.脚本.JavaScript;

public class 编译工程 {

    private 基本界面 界面;
    private 工程 工程;
    private 进度弹窗 弹窗;
    private 秘钥签名 签名;

    public 编译工程(基本界面 $界面,工程 $工程,秘钥签名 $签名) {
        界面 = $界面;
        工程 = $工程;
        签名 = $签名;
        弹窗 = new 进度弹窗($界面);
        弹窗.置可关闭(false);
        弹窗.更新("初始化");
    }

    public void 启动() {
        弹窗.显示();
        new 线程($初始化).启动();
    }

    方法 $初始化 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            主程序();
            return null;
        }
    };

    public void 主程序() {
        弹窗.更新("检查资源名称");
        File[] $文件 = 文件.遍历文件(工程.取地址("资源"));
        for (File $单个 : $文件) {
            if (!$单个.getName().replaceAll("[a-zA-Z0-9. ]", "").equals("")) {
                提示.警告("资源名称不合法 可能导致无法安装 : " + $单个.getName());
            }
        }
        String $编译 = 工程.取地址("编译");
        if (文件.是文件($编译)) {
            文件.删除($编译);
        }
        String $打包 = 工程.取地址("编译", "打包");
        文件.创建目录($打包);
        弹窗.更新("释放打包文件");
        ZIP.解压(应用.取应用信息().地址, $打包);
        File[] $替换 = 文件.取文件列表($打包 + "/assets/client");
        for (File $单个 : $替换) {
            文件.剪切($单个.getPath(), $打包 + "/" + $单个.getName());
        }
        文件.删除($打包 + "/assets");
        弹窗.更新("打包资源");
        String $资源 = 工程.取地址("资源");
        if (文件.是目录($资源)) {
            文件.复制($资源, $打包 + "/assets");
        }
        弹窗.更新("编译脚本");
        String $缓存 = 工程.取地址("编译", "脚本");
        文件.复制(工程.取地址("源码"), $缓存);
        File[] $所有 = 文件.遍历文件($缓存);
        String $输出 = 工程.取地址("编译", "类");
        for (File $单个 : $所有) {
            if ($单个.getName().endsWith(".js")) {
                JSC $JS = new JSC($单个.getPath());
                $JS.置包名(工程.信息.包名);
                $JS.置输出($输出);
                $JS.置编译源码(true);
                try {
                    String $返回 = $JS.编译();
                    字符.保存($单个.getPath(), $返回);
                } catch (Exception e) {
                    continue;
                }

            }
        }
        ZIP.压缩($缓存, 工程.取地址("编译", "打包", "classes1.dex"));
        弹窗.更新("编译应用类文件");
        CLASS $应用 = new CLASS(工程.信息.包名 + ".Application", "间.安卓.实例.实例应用", "Application.java");
        $应用.初始化();
        $应用.编译(工程.取地址("编译", "类", 字符.替换(工程.信息.包名, ".", "/"), "Application.class"));
        弹窗.更新("编译启动界面");
        CLASS $启动 = new CLASS(工程.信息.包名 + ".LaunchPad", "间.安卓.实例.实例启动", "LaunchPad.java");
        $启动.初始化();
        $启动.编译(工程.取地址("编译", "类", 字符.替换(工程.信息.包名, ".", "/"), "LaunchPad.class"));
        弹窗.更新("编译到Dex");
        try {
            int $d = 2;

            while (文件.是文件(工程.取地址("编译", "打包", "classes" + $d + ".dex"))) {
                文件.删除(工程.取地址("编译", "打包", "classes" + $d + ".dex"));
                $d ++;
            }

            String $DEX = $打包 + "/classes2.dex";
            DEX.多个($DEX, 工程.取地址("编译", "类"));
            //文件.删除($DEX);
            弹窗.更新("合并Dex");
            DEX.合成($打包 + "/class.dex",$DEX,$打包+"/classes.dex");
            文件.剪切($打包+"/class.dex",$打包+"/classes.dex");
            文件.删除($DEX);
        } catch (IOException $错误) {
            提示.普通($错误);
            return;
        }
        弹窗.更新("检查图标资源");
        String $图标 = 工程.取地址("图标.png");
        if (文件.是文件($图标)) {
            文件.复制($图标, 工程.取地址("编译", "打包", "res", "drawable", "icon.png"));
        } else {
            文件.复制("#assets/android.png", 工程.取地址("编译", "打包", "res", "drawable", "icon.png"));
        }
        弹窗.更新("编译清单文件");
        打包签名 $签名 = new 打包签名($打包);
        final String $APK = $签名.初始化(工程.信息.包名);
        if (文件.存在($APK)) {
            文件.删除($APK);
        }
        $签名.置工程名(工程.信息.工程名);
        $签名.置版本号(new Integer(工程.信息.版本号).toString());
        $签名.置版本名(工程.信息.版本名);
        $签名.编译清单();
        弹窗.更新("编译资源");
        $签名.编译资源();
        弹窗.更新("打包安装包");
        $签名.打包();
        弹窗.更新("签名安装包");
        $签名.签名(签名);
        弹窗.更新("打开安装包");
        文件.删除($编译);
        处理.主线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    弹窗.隐藏();
                    提示.普通("打包成功 ~\n存放在: " + $APK);
                    文件.打开($APK);
                    return null;
                }
            });
    }

}

*/
