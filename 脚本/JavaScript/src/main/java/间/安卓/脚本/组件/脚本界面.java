package 间.安卓.脚本.组件;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import 间.安卓.工具.文件;
import 间.安卓.插件.界面插件;
import 间.安卓.组件.基本界面;
import 间.安卓.脚本.JavaScript;
import 间.安卓.脚本.基本脚本;
import 间.工具.字符;
import 间.工具.错误;
import 间.接口.方法;
import 间.接口.调用;
import 间.收集.哈希表;

public class 脚本界面 extends 基本界面 {

    public 基本脚本 当前环境;
    public String 当前目录;
    public String 当前文件;
    public String 当前脚本;

    public 哈希表<String,方法> 所有事件 = new 哈希表<>();

    public void 注册事件(String $事件,方法 $方法) {
        所有事件.设置($事件, $方法);
    }

    public boolean 检查事件(String $名称) {
        return 所有事件.检查键值($名称);
    }

    public Object 调用事件(String $名称,Object... $参数) {
        return 调用.事件(所有事件.读取($名称), $参数);
    }

    @Override
    public void 界面创建事件(Bundle $恢复) {
        String $脚本地址 = getIntent().getStringExtra("脚本");
        if ($恢复 != null) {
            当前目录 = $恢复.getString("目录");
            文件.替换地址("./", 当前目录 + "/");
        }
        if (文件.是文件($脚本地址)) {
            当前脚本 = 文件.检查地址($脚本地址);
            当前目录 = 文件.取目录(当前脚本);
            当前文件 = 文件.取名称(当前脚本);
            文件.替换地址("./", 当前目录 + "/");
            当前环境 = new JavaScript();
            当前环境.压入变量("当前界面", this);
            当前环境.运行文件(当前脚本);
            new 脚本界面插件().注册(this);
        } else {
            错误.内容("找不到脚本 : " + $脚本地址);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle $输出) {
        $输出.putString("目录", 当前目录);
        super.onSaveInstanceState($输出);
    }

    @Override
    public void 跳转脚本(Integer $请求码,String $类,Object[] $数据) {
        if (字符.以开始($类, "../")) {
            $类 = 文件.取目录(当前目录) + "/" + $类.substring(3);
        } else if (字符.以开始($类, "./")) {
            $类 = 当前目录 + "/" + $类.substring(2);
        }
        super.跳转脚本($请求码, $类, $数据);
    }

    public class 脚本界面插件 extends 界面插件 {

        @Override
        public void 界面创建事件(Bundle $恢复) {
            调用事件("界面创建事件", $恢复);
        }

        @Override
        public void 界面启动事件() {
            调用事件("界面启动事件");
        }

        @Override
        public void 界面刷新事件() {
            调用事件("界面刷新事件");
        }

        @Override
        public void 界面遮挡事件() {
            调用事件("界面遮挡事件");
        }

        @Override
        public void 界面销毁事件() {
            调用事件("界面销毁事件");
        }

        @Override
        public void 界面回调事件(int $请求码,int $返回码,Intent $意图) {
            调用事件("界面回调事件", $请求码, $返回码, $意图);
        }

        @Override
        public void 离开界面事件() {
            调用事件("离开界面事件");
        }

        @Override
        public void 取得焦点事件() {
            调用事件("取得焦点事件");
        }

        @Override
        public void 失去焦点事件() {
            调用事件("失去焦点事件");
        }

        @Override
        public Boolean 按键按下事件(int $按键码,KeyEvent $事件) {
            return (Boolean)调用事件("按键按下事件", $按键码, $事件);
        }

        @Override
        public Boolean 返回按下事件() {
            return (Boolean)调用事件("返回按下事件");
        }

    }

}
