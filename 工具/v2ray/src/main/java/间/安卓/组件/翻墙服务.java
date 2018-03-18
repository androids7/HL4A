package 间.安卓.组件;

import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import libv2ray.Libv2ray;
import libv2ray.V2RayCallbacks;
import libv2ray.V2RayPoint;
import libv2ray.V2RayVPNServiceSupportsSet;
import 间.安卓.工具.应用;
import 间.安卓.工具.提示;
import 间.安卓.工具.文件;
import 间.安卓.工具.转换;
import 间.工具.字符;
import libv2ray.V2RayContext;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

public class 翻墙服务 extends VpnService {

    private ParcelFileDescriptor VPN;
    private V2RayPoint V2RAY;
    private 回调 回调 = new 回调();
    private static 翻墙服务 实例;
    private static String 地址;

    public 翻墙服务() {
        StrictMode.ThreadPolicy $模式 = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy($模式);
        V2RAY = Libv2ray.newV2RayPoint();
        V2RAY.setPackageName(应用.取信息().包名);
        实例 = this;
        启动();
    }

    public static void 置地址(String $地址) {
        地址 = 文件.检查地址($地址);
    }

    public static 翻墙服务 取实例() {
        return 实例;
    }

    @Override
    public int onStartCommand(Intent $意图,int $设置,int $ID) {
        return START_STICKY;
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
            try {
                String[] $内容 = $单个.split(",");
                switch ($内容[0].charAt(0)) {
                    case 'm':$启动.setMtu(Short.parseShort($内容[1]));break;
                    case 'a':$启动.addAddress($内容[1], 转换.数字($内容[2]));break;
                    case 'r':$启动.addRoute($内容[1], 转换.数字($内容[2]));break;
                    //case 'd':$启动.addDnsServer($内容[1]);break;
                    case 's':$启动.addSearchDomain($内容[1]);break;
                }
                提示.日志("参数:" + $单个);
            } catch (Exception $错误) {
                提示.日志("错误参数:" + $单个);
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
        提示.日志("新实例:" + VPN);
    }

    public void 启动() {
        try {
            if (!状态()) {
                V2RAY.setCallbacks(回调);
                V2RAY.setVpnSupportSet(回调);
                V2RAY.upgradeToContext();
                V2RAY.optinNextGenerationTunInterface();
                V2RAY.setConfigureFile("V2Ray_internal/ConfigureFileContent");
                V2RAY.setConfigureFileContent(转换配置(地址));
                V2RAY.runLoop();
                提示.日志("启动");
            }
        } catch (JSONException $错误) {}
    }

    private String 转换配置(String $地址) throws JSONException {

        JSONObject $对象 = new JSONObject(字符.读取($地址));
        JSONObject $覆盖 = new JSONObject(字符.读取("#assets/v2def.json"));
        $对象.putOpt("#lib2ray", $覆盖.getJSONObject("#lib2ray"));
        if ($对象.has("inbound")) {
            JSONObject $输入 = $对象.getJSONObject("inbound");
            if ($输入.has("port")) {
                $对象.putOpt("port", $输入.getInt("port"));
            } else {
                $对象.putOpt("port", $覆盖.getInt("pprt"));
            }
        }
        if (!$对象.has("inboundDetour")) {
            $对象.put("inboundDetour", new JSONArray());
        }

        return $对象.toString();
    }

    public boolean 状态() {
        return V2RAY.getIsRunning();
    }

    public void 停止() {
        if (状态()) {
            V2RAY.stopLoop();
        }
    }

    public void 准备() {
        Intent $意图 = VpnService.prepare(this);
        if ($意图 != null) {
            提示.日志("准备失败");
            return;
        }
        V2RAY.vpnSupportReady();
    }

    public class 回调 implements V2RayCallbacks,V2RayVPNServiceSupportsSet {

        @Override
        public long onEmitStatus(long p1,String p2) {
            return 0;
        }

        @Override
        public long getVPNFd() {
            return VPN.getFd();
        }

        @Override
        public long prepare() {
            准备();
            return 1;
        }

        @Override
        public long protect(long $套接字) {
            return 翻墙服务.this.protect(((Long)$套接字).intValue()) ? 0 : 1;
        }

        @Override
        public long setup(String $参数) {
            try {
                初始化($参数);
                return 0;
            } catch (Exception $错误) {
                提示.日志($错误);
                return -1;
            }
        }

        @Override
        public long shutdown() {
            return 0L;
        }


    }

}
