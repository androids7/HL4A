package 间.网络;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import 间.工具.文件;
import 间.工具.流;
import 间.工具.线程;
import 间.工具.编码;
import 间.工具.错误;
import 间.接口.方法;
import 间.收集.哈希表;
import 间.收集.集合;
import 间.网络.连接;
import java.util.concurrent.TimeUnit;
import okhttp3.RequestBody;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import okhttp3.MediaType;

public class 连接 {

    private static OkHttpClient 网络实例;
    private Headers.Builder 请求头 = new Headers.Builder();
    private Request.Builder 请求 = new Request.Builder();
    private static SSLSocketFactory 工厂;
    
    private 哈希表<String,String> Cookie表 = new 哈希表<>();
    private 哈希表<String,String> 参数表 = new 哈希表<>();
    private 哈希表<String,File> 文件表 = new 哈希表<>();
    
    private MediaType 类型;
    
    // 信任所有规则 避免无法连接

    private static final TrustManager[] 所有规则 = new TrustManager[] {
        new X509TrustManager() {
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
        }
    };

    private static HostnameVerifier 信任所有 = new HostnameVerifier() {
        @Override
        public boolean verify(String $地址,SSLSession $连接) {
            return true;
        }
    };

    static {
        OkHttpClient.Builder $构建 = new OkHttpClient.Builder();
        try {
            SSLContext $上下文 = SSLContext.getInstance("SSL");
            $上下文.init(null, 所有规则, new SecureRandom());
            工厂 = $上下文.getSocketFactory();
            $构建.sslSocketFactory(工厂);
            $构建.hostnameVerifier(信任所有);
        } catch (Exception $错误) {}
        $构建.connectTimeout(5000,TimeUnit.MILLISECONDS);
        网络实例 = $构建.build();
    }

    private static 集合<String> 所有模式 = new 集合<String>("OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH");
    private String 模式;
    private String 标识;
    
    private 连接(String $地址,String $模式) {
        $模式 = $模式.toUpperCase();
        if (!所有模式.检查($模式)) {
            模式 = "GET";
        } else {
            模式 = $模式;
        }
        请求.url($地址);
        标识 = UUID.randomUUID().toString();
        类型 = MediaType.parse("multipart/form-datap;boundary=" + 标识);
    }

    public static 连接 创建(String $地址,String $模式) {
        try {
            连接 $连接 = new 连接($地址, $模式);
            return $连接;
        } catch (Exception $错误) {}
        return null;
    }

    public 连接 置断点(long $开始) {
        请求头("RANGE", BigInteger.valueOf($开始).toString() + "-");
        return this;
    }

    public 连接 置断点(String $文件) {
        long $大小 = 文件.取大小($文件);
        if ($大小 != 0) {
            置断点($大小);
        }
        return this;
    }

    public 连接 请求头(String $名称,String $内容) {
        请求头.set($名称,$内容);
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

        try {
            
            if (!Cookie表.isEmpty()) {
                请求头("Cookie", 转换Cookie(Cookie表));
            }

            请求.headers(请求头.build());

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
                请求.method(模式,("GET".equals(模式) || "HEAD".equals(模式)) ? null : RequestBody.create(类型,$输出.toByteArray()));
                Request $请求 = 请求.build();
                return new 资源(网络实例.newCall($请求).execute());
        } catch (IOException $错误) {
            错误.抛出($错误);
        }

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

}
