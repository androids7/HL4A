package 间.网络;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import 间.工具.文件;
import 间.工具.流;
import 间.工具.线程;
import 间.工具.编码;
import 间.工具.错误;
import 间.接口.方法;
import 间.收集.哈希表;
import java.security.SecureRandom;
import 间.工具.反射;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class 连接 {

    public HttpURLConnection 连接;
    private 哈希表<String,String> 参数表 = new 哈希表<>();
    private 哈希表<String,String> Cookie表 = new 哈希表<>();
    private 哈希表<String,File> 文件表 = new 哈希表<>();
    private String 模式;
    private String 标识;
    
    private static SSLSocketFactory 工厂;

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
        HttpURLConnection.setDefaultRequestProperty("Charset","UTF-8");
        HttpURLConnection.setDefaultRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
        HttpURLConnection.setDefaultRequestProperty("Accept-Charset", "UTF-8");
        try {
            SSLContext $上下文 = SSLContext.getInstance("SSL");
            $上下文.init(null, 所有规则, new SecureRandom());
            工厂 = $上下文.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(工厂);
            HttpsURLConnection.setDefaultHostnameVerifier(信任所有);
        } catch (Exception $错误) {}
    }

    private 连接(String $地址,String $模式) throws IOException {
        连接 = (HttpURLConnection) new URL($地址).openConnection();
        标识 = UUID.randomUUID().toString();
        请求头("Content-Type", "multipart/form-data;boundary=" + 标识);
        模式 = $模式;
        连接.setDefaultUseCaches(false);
    }

    public static 连接 创建(String $地址,String $模式) {
        try {
            连接 $连接 = new 连接($地址, $模式);
            return $连接;
        } catch (IOException $错误) {}
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
        连接.setRequestProperty($名称, $内容);
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

            if (模式 == null ? true : 模式.toLowerCase().equals("get") || (参数表.isEmpty() && 文件表.isEmpty())) {
                连接.setRequestMethod("GET");
            } else {
                连接.setUseCaches(false);
                连接.setRequestMethod(模式);
                OutputStream $输出 = 连接.getOutputStream();
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
            }
            连接.connect();
            return new 资源(连接);
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

    private String 编码(String $内容) {

        return 编码.链接.编码($内容);

    }

}
