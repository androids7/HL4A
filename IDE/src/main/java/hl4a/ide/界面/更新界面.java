package hl4a.ide.界面;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import hl4a.ide.布局.布局_界面_更新;
import 间.安卓.组件.界面;
import 间.安卓.工具.环境;
import 间.安卓.工具.应用;
import 间.接口.调用;
import 间.安卓.弹窗.进度弹窗;
import 间.安卓.组件.启动界面;
import 间.安卓.工具.线程;
import hl4a.ide.工具.更新;
import 间.安卓.工具.提示;
import 间.网络.连接;
import 间.网络.资源;
import 间.安卓.工具.文件;
import hl4a.ide.应用配置信息;

public class 更新界面 extends 界面 {

    private 布局_界面_更新 布局;
    private 进度弹窗 进度;

    @Override
    public void 界面创建事件(Bundle $恢复) {
        打开布局(new 布局_界面_更新(此));
        布局 = (布局_界面_更新)当前视图;
        if (!应用.已安装("com.coolapk.market")) {
            布局.酷安.隐藏();
        }
        进度 = new 进度弹窗(此);
        进度.无限(false);
        进度.置可关闭(false);
        布局.直接.置单击事件(调用.配置(this, "直接下载"));
    }

    public void 直接下载() {
        进度.显示();
        进度.更新("正在连接 ~");
        new 线程(this, "下载").启动();
    }

    private 资源 返回;
    private String 缓存;
    private String 地址;

    public void 下载() {
        if (!更新.联网()) {
            结束("连接失败 ~");
            return;
        }
        缓存 = 应用配置信息.下载缓存 + "/HL4A " + 更新.信息.最新 + ".tmp";
        地址 = 应用配置信息.下载缓存 + "/HL4A " + 更新.信息.最新 + ".apk";
        if (文件.是文件(地址)) {
            文件.打开(地址);
            进度.隐藏();
            return;
        }
        返回 = new 连接(更新.信息.地址).同步();
        if (!返回.成功()) {
            结束("连接失败 ~");
            return;
        }
        返回.保存(缓存, 调用.代理(this, "进度"), 调用.配置(this, "开始"), 调用.异步配置(this, "结束"));
    }

    public void 开始() {
        进度.更新("开始下载 ~");
    }
    
    public void 结束() {
        进度.隐藏();
        进度.更新("准备结束 ~");
        文件.剪切(缓存,地址);
        提示.普通("下载完成 ~");
        文件.打开(地址);
    }

    public void 进度(long $进度) {
        int $当前 = 返回.进度($进度);
        进度.更新($当前);
        进度.更新("下载中 " + $当前 + "% ~");
    }

    public void 结束(String $内容) {
        进度.隐藏();
        提示.警告($内容);
    }

}
