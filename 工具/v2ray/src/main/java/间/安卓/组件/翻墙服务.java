package 间.安卓.组件;

import android.net.VpnService;
import android.content.Context;
import android.os.StrictMode;
import 间.工具.字符;
import 间.安卓.工具.转换;
import android.os.ParcelFileDescriptor;
import android.content.Intent;
import go.libv2ray.Libv2ray;
import go.libv2ray.Libv2ray.V2RayPoint;
import 间.工具.错误;
import 间.安卓.工具.提示;
import 间.安卓.工具.文件;

public class 翻墙服务 extends VpnService {

    private ParcelFileDescriptor VPN;
    private Libv2ray.V2RayPoint V2RAY;
    private 回调 回调 = new 回调();
    private static 翻墙服务 实例;
    private static String 地址;
    
    public 翻墙服务() {
        StrictMode.ThreadPolicy $模式 = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy($模式);
        V2RAY = Libv2ray.NewV2RayPoint();
        V2RAY.setPackageName(getPackageName());
        实例 = this;
        启动(地址);
    }
    
    public static void 置地址(String $地址) {
        地址 = 文件.检查地址($地址);
    }
    
    public static 翻墙服务 取实例() {
        return 实例;
    }

    @Override
    public void onRevoke() {
        停止();
        实例 = null;
    }

    public void 初始化(String $信息) {
        Builder $启动 = new Builder();
        // 我也不知道下面的是啥 别人都这么写
        String[] $所有 = $信息.split(" ");
        for (String $单个 : $所有) {
            String[] $内容 = $单个.split(",");
            switch (字符.取开始后($内容[0], 1)) {
                case "m":$启动.setMtu(Short.parseShort($内容[1]));break;
                case "a":$启动.addAddress($内容[1], 转换.数字($内容[2]));break;
                case "r":$启动.addRoute($内容[1], 转换.数字($内容[2]));break;
                case "s":$启动.addSearchDomain($内容[1]);break;
            }
        }
        $启动.setSession("HL4A-V2Ray服务");
        $启动.addDnsServer("8.8.8.8");
        $启动.addDnsServer("8.8.4.4");
        if (VPN != null) {
            try {
                VPN.close();
            } catch (Exception $错误) {}
        }
        VPN = $启动.establish();
    }

    public void 启动(String $配置) {
        if (!状态()) {
            V2RAY.setCallbacks(回调);
            V2RAY.setVpnSupportSet(回调);
            V2RAY.setConfigureFile($配置);
            V2RAY.RunLoop();
        }
    }
    
    public boolean 状态() {
        return V2RAY.getIsRunning();
    }

    public void 停止() {
        if (状态()) {
            V2RAY.StopLoop();
        }
    }

    public void 准备() {
        Intent $意图 = VpnService.prepare(this);
        if ($意图 != null) return;
        V2RAY.VpnSupportReady();
    }

    public class 回调 implements Libv2ray.V2RayCallbacks,Libv2ray.V2RayVPNServiceSupportsSet {

        @Override
        public long OnEmitStatus(long p1,String p2) {
            return 0;
        }

        @Override
        public long GetVPNFd() {
            return VPN.getFd();
        }

        @Override
        public long Prepare() {
            准备();
            return 1;
        }

        @Override
        public long Protect(long $套接字) {
            return 翻墙服务.this.protect(((Long)$套接字).intValue()) ? 0 : 1;
        }

        @Override
        public long Setup(String $参数) {
            try {
                初始化($参数);
                return 0;
            } catch (Exception $错误) {
                提示.日志($错误);
                return -1;
            }
        }

        @Override
        public long Shutdown() {
            return 0L;
        }


    }

}
