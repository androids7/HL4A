package 间.网络;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import 间.工具.字符;
import 间.工具.流;
import 间.工具.错误;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.StreamProgress;
import 间.接口.方法;
import 间.接口.流进度;

public class 资源 {

    private int 状态码;
    private int 长度;
    private InputStream 输入;
    private boolean 是断点;
    private byte[] 缓存;

    public 资源(int $状态码,int $长度,InputStream $输入) {
        状态码 = $状态码;
        长度 = $长度;
        输入 = $输入;
        是断点 = 状态码 == 206;
    }

    public boolean 已成功() {
        return 状态码 >= 200 && 状态码 < 300;
    }

    public boolean 无连接() {
        return 状态码 == -1;
    }

    public int 取状态码() {
        return 状态码;
    }

    public byte[] 字节() {
        if (缓存 == null)
            缓存 = 流.读取(输入);
        return 缓存;
    }

    public String 文本() {
        return 字符.转换(字节());
    }
    
    public void 保存(String $输出) {
        保存($输出,null);
    }
    
    public void 保存(String $输出,方法 $进度) {
        保存($输出,$进度,null,null);
    }

    public void 保存(String $输出,方法 $进度,方法 $开始,方法 $结束) {
        OutputStream $流 = 流.输出.文件($输出);
        if ($流 == null) {
            错误.内容("无法保存: "+$输出);
        }
        流.非阻塞保存($流,输入,$进度,$开始,$结束);
    }

}
