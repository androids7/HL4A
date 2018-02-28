package 间.工具;

import java.io.*;
import java.math.*;
import java.security.*;
import cn.hutool.core.io.IoUtil;

public class 散列 {

    public static String 摘要(String $算法,byte[] $字节) {

        断言.不为空($算法,"参数1 [算法] 为空");
        断言.不为空($字节,"参数2 [字节] 为空");

        InputStream $输入流 = 流.输入.字节($字节);

        String $结果 = new BigInteger(摘要($算法, $输入流)).toString(16);

        流.关闭($输入流);

        return $结果;

    }

    private static byte[] 摘要(String $算法,InputStream $输入流) {
        try {
            byte $大小[] = new byte[流.中];
            MessageDigest $摘要 = MessageDigest.getInstance($算法);
            int $长度;
            while (($长度 = $输入流.read($大小)) != -1) {
                $摘要.update($大小, 0, $长度);
            }
            return $摘要.digest();
        } catch (IOException $错误) {
            错误.抛出($错误);
        } catch (NoSuchAlgorithmException $错误) {
            错误.内容("参数1[算法] 错误: 没有那样的散列算法 (%s)", $算法);
        }
        return null;
    }

    public static String 文件(String $算法,String $文件) {
        断言.不为空($算法, "参数1 [算法] 为空");
        断言.为真(文件.是文件($文件), "参数2 [文件] (%s) 不是文件", $文件);
        InputStream $输入流 = 流.输入.文件($文件);
        String $结果 = new BigInteger(摘要($算法, $输入流)).toString(16);
        流.关闭($输入流);
        return $结果;
    }

}
