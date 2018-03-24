package 间.网络;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import 间.工具.文件;
import 间.工具.流;
import 间.工具.线程;
import 间.工具.编码;
import 间.工具.错误;
import 间.接口.方法;
import 间.收集.哈希表;
import 间.收集.集合;
import 间.网络.连接;

public class 连接 {

    private static OkHttpClient 网络实例;
    private Headers.Builder 请求头 = new Headers.Builder();
    private Request.Builder 请求 = new Request.Builder();

    private 哈希表<String,String> Cookie表 = new 哈希表<>();
    private 哈希表<String,String> 参数表 = new 哈希表<>();
    private 哈希表<String,File> 文件表 = new 哈希表<>();

    private MediaType 类型;

    private boolean 不可用 = false;

    static {
        OkHttpClient.Builder $构建 = new OkHttpClient.Builder();
        try {
            $构建.sslSocketFactory(new 连接工厂());
            $构建.hostnameVerifier(new 信任域名());
        } catch (Exception $错误) {}
        $构建.connectTimeout(2333, TimeUnit.MILLISECONDS);
        网络实例 = $构建.build();
    }

    private static 集合<String> 所有模式 = new 集合<String>("OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH");
    private String 模式;
    private String 标识;

    public 连接() {
        this((String)null);
    }

    public 连接(String $地址) {
        this($地址, "GET");
    }
    
    public 连接(String $地址,String $模式) {
        模式($模式);
        地址($地址);
        标识 = UUID.randomUUID().toString();
        类型 = MediaType.parse("multipart/form-datap;boundary=" + 标识);
    }

    public 连接 地址(String $地址) {
        if ($地址 == null || (!$地址.startsWith("http://") && !$地址.startsWith("https://"))) {
            不可用 = true;
        } else {
            请求.url($地址);
        }
        return this;
    }

    public 连接 模式(String $模式) {
        $模式 = $模式.toUpperCase();
        if (!所有模式.检查($模式)) {
            不可用 = true;
        } else {
            模式 = $模式;
        }
        return this;
    }
    
    public 连接 断点(long $开始) {
        请求头("RANGE", BigInteger.valueOf($开始).toString() + "-");
        return this;
    }

    public 连接 断点(String $文件) {
        if (文件.是文件($文件)) {
            long $大小 = 文件.取大小($文件);
            if ($大小 != 0) {
                断点($大小);
            }
        }
        return this;
    }

    public 连接 请求头(String $名称,String $内容) {
        请求头.set($名称, $内容);
        return this;
    }

    public 连接 参数(String $名称,String $内容) {
        参数表.设置($名称, $内容);
        return this;
    }

    public 连接 Cookie(String $名称,String $内容) {
        Cookie表.设置($名称, $内容);
        return this;
    }

    public 连接 文件(String $名称,String $文件) {
        if (!文件.是文件($文件)) 错误.内容("文件不存在:" + $文件);
        文件表.设置($名称, 文件.取文件对象($文件));
        return this;
    }

    public void 异步(方法 $回调) {

        new 线程(this, "同步").置回调($回调).启动();

    }

    public 资源 同步() {

        if (不可用) {
            return new 资源(null);
        }

        try {

            if (!Cookie表.isEmpty()) {
                请求头("Cookie", 转换Cookie(Cookie表));
            }

            请求.headers(请求头.build());

            请求.cacheControl(new CacheControl.Builder().noCache().build());

            ByteArrayOutputStream $输出 = 流.输出.字节();
            byte[] $分隔 = ("--" + 标识).getBytes();
            byte[] $换行 = "\r\n".getBytes();
            if (!参数表.isEmpty()) {
                for (Map.Entry<String,String> $单个: 参数表.entrySet()) {
                    $输出.write($换行);
                    $输出.write($分隔);
                    $输出.write($换行);
                    $输出.write("Content-Disposition: form-data;".getBytes());
                    $输出.write(("name=\"" + 编码($单个.getKey()) + "\"").getBytes());
                    $输出.write($换行);
                    $输出.write(编码($单个.getValue()).getBytes());
                }
            }
            if (!文件表.isEmpty()) {
                for (Map.Entry<String,File> $单个: 文件表.entrySet()) {
                    File $文件 = $单个.getValue();
                    $输出.write($换行);
                    $输出.write($分隔);
                    $输出.write($换行);
                    $输出.write("Content-Disposition: form-data;".getBytes());
                    $输出.write(("name=\"" + 编码($单个.getKey()) + "\";").getBytes());
                    $输出.write(("filename=\"" + 编码($文件.getName()) + "\";").getBytes());
                    $输出.write($换行);
                    流.保存($输出, 流.输入.文件($文件.getPath()));
                }
            }
            $输出.write($换行);
            $输出.write($分隔);
            $输出.write("--".getBytes());
            $输出.flush();
            请求.method(模式, ("GET".equals(模式) || "HEAD".equals(模式)) ? null : RequestBody.create(类型, $输出.toByteArray()));
            Request $请求 = 请求.build();
            return new 资源(网络实例.newCall($请求).execute());
        } catch (Exception $错误) {}

        return new 资源(null);

    }

    private String 转换Cookie(哈希表<String,String> $内容) {
        switch ($内容.长度()) {
            case 0:return "";
            case 1:
                Map.Entry<String,String> $一个 = $内容.entrySet().iterator().next();
                return $一个.getKey() + "=" + $内容;
            default:
                StringBuffer $返回 = new StringBuffer("");
                Iterator<Map.Entry<String,String>> $所有 = $内容.entrySet().iterator();
                Map.Entry<String,String> $第一个= $所有.next();
                $返回.append("&" + 编码($第一个.getKey()) + "=" + 编码($第一个.getValue()));
                while ($所有.hasNext()) {
                    Map.Entry<String,String> $单个= $所有.next();
                    $返回.append("&" + 编码($单个.getKey()) + "=" + 编码($单个.getValue()));
                }
                return $返回.toString();
        }
    }

    private static String 编码(String $内容) {
        return 编码.链接.编码($内容);
    }

    public static String 取源(String $地址) {
        return new 连接($地址).同步().文本();
    }

    private static class 信任证书 implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain,String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {

            return new X509Certificate[]{};
        }
    };


    private static class 信任域名 implements  HostnameVerifier {
        @Override
        public boolean verify(String hostname,SSLSession session) {
            return true;// 直接返回true
        }
    };

    private static class 连接工厂 extends SSLSocketFactory {

        public static String SSL = "SSL";
        public static String SSLv2 = "SSLv2";
        public static String SSLv23 = "SSLv2.3";
        public static String SSLv3 = "SSLv3";

        public static String TLS = "TLS";
        public static String TLSv1 = "TLSv1";
        public static String TLSv11 = "TLSv1.1";
        public static String TLSv12 = "TLSv1.2";

        private static String[] 开启策略 = {SSLv23,SSLv3,TLSv1,TLSv11,TLSv12};
        // Android低版本不重置的话某些SSL访问就会失败

        private SSLSocketFactory 工厂;

        public 连接工厂() {
            super();
            try {
                SSLContext $上下文 = SSLContext.getInstance("SSL");
                $上下文.init(null, new X509TrustManager[]{new 信任证书()}, new SecureRandom());
                工厂 = $上下文.getSocketFactory();
            } catch (Exception $错误) {}
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return 工厂.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return 工厂.getSupportedCipherSuites();
        }

        @Override
        public SSLSocket createSocket(Socket s,String host,int port,boolean autoClose) throws IOException {
            SSLSocket $连接 = (SSLSocket) 工厂.createSocket(s, host, port, autoClose);
            重置策略($连接);
            return $连接;
        }

        @Override
        public Socket createSocket(String host,int port) throws IOException, UnknownHostException {
            SSLSocket $连接 = (SSLSocket) 工厂.createSocket(host, port);
            重置策略($连接);
            return $连接;
        }

        @Override
        public Socket createSocket(String host,int port,InetAddress localHost,int localPort) throws IOException,
        UnknownHostException {
            SSLSocket $连接 = (SSLSocket) 工厂.createSocket(host, port, localHost, localPort);
            重置策略($连接);
            return $连接;
        }

        @Override
        public Socket createSocket(InetAddress host,int port) throws IOException {
            SSLSocket $连接 = (SSLSocket) 工厂.createSocket(host, port);
            重置策略($连接);
            return $连接;
        }

        @Override
        public Socket createSocket(InetAddress address,int port,InetAddress localAddress,int localPort)
        throws IOException {
            SSLSocket $连接 = (SSLSocket) 工厂.createSocket(address, port, localAddress, localPort);
            重置策略($连接);
            return $连接;
        }

        private void 重置策略(SSLSocket $连接) {
            $连接.setEnabledProtocols(开启策略);
        }

    }

}
