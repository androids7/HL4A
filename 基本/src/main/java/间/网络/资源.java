package 间.网络;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import okhttp3.Response;
import okhttp3.internal.huc.OkHttpURLConnection;
import okhttp3.internal.huc.OkHttpsURLConnection;
import 间.工具.反射;
import 间.工具.字符;
import 间.工具.流;
import 间.工具.错误;
import 间.接口.方法;
import 间.网络.连接;

public class 资源 {

    private Response 资源;
    private byte[] 内容;

    public 资源(Response $资源) {
        资源 = $资源;
    }

    public boolean 成功() {
        return 资源.isSuccessful();
    }

    public boolean 重定向() {
        return 资源.isRedirect();
    }

    public int 状态码() {
        return 资源.code();
    }

    public byte[] 字节() {
        try {
            if (内容 == null)
                内容 = 资源.body().bytes();
            return 内容;
        } catch (IOException $错误) {}
        return null;
    }

    public String 文本() {
        return 字符.转换(字节());
    }

    public void 保存(String $输出) {
        保存($输出, null);
    }

    public void 保存(String $输出,方法 $进度) {
        保存($输出, $进度, null, null);
    }

    public void 保存(String $输出,方法 $进度,方法 $开始,方法 $结束) {
        OutputStream $流 = 流.输出.文件($输出);
        if ($流 == null) {
            错误.内容("无法保存: " + $输出);
        }
        流.非阻塞保存($流, 内容 == null ? 资源.body().byteStream() : 流.输入.字节(内容), $进度, $开始, $结束);
    }

}
