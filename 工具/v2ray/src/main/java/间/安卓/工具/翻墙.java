package 间.安卓.工具;

import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import 间.安卓.插件.界面插件;
import 间.安卓.组件.基本界面;
import 间.安卓.组件.翻墙服务;
import 间.工具.反射;

public class 翻墙 {

    public static final int 请求码_VPN权限 = 19134;

    public static void 启动(基本界面 $界面,String $配置地址) {
        翻墙服务.置地址($配置地址);
        翻墙服务 $服务 = 翻墙服务.取实例();
        if ($服务 != null) {
            $服务.停止();
            $服务.启动();
        } else {
            Intent $意图 = VpnService.prepare($界面);
            if ($意图 == null) {
                服务.启动($界面, 反射.取类("hl4a.runtime.VpnService"));
            } else {
                $界面.startActivityForResult($意图, 请求码_VPN权限);
                $界面.注册插件("翻墙准备", new 准备回调($界面));
            }
        }
    }

    public static boolean 状态() {
        翻墙服务 $服务 = 翻墙服务.取实例();
        return $服务 == null ? false : $服务.状态();
    }

    public static void 停止() {
        翻墙服务 $服务 = 翻墙服务.取实例();
        if ($服务 != null) {
            $服务.停止();
        }
    }

    public static class 准备回调 extends 界面插件 {

        private Context 上下文;

        public 准备回调(Context $上下文) {
            上下文 = $上下文;
        }

        @Override
        public boolean 界面回调事件(int $请求码,int $返回码,Intent $意图) {
            if ($请求码 == 请求码_VPN权限) {
                if ($返回码 == 基本界面.返回码_成功) {
                    服务.启动(上下文, 反射.取类("hl4a.runtime.VpnService"));
                }
                return true;
            }
            return false;
        }

    }

}
