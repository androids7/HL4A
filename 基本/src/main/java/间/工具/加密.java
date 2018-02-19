package 间.工具;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class 加密 {

    public static byte[] 加密(String $方法,byte[] $内容,String $密码) {
        try {           
            KeyGenerator $创建 = KeyGenerator.getInstance($方法);
            $创建.init(128, new SecureRandom($密码.getBytes()));
            SecretKey $秘钥 = $创建.generateKey();
            byte[] $格式 = $秘钥.getEncoded();
            SecretKeySpec $秘钥格式 = new SecretKeySpec($格式, $方法);
            Cipher $密码器 = Cipher.getInstance("AES");// 创建密码器
            $密码器.init(Cipher.ENCRYPT_MODE, $秘钥格式);// 初始化
            byte[] $返回 = $密码器.doFinal($内容);
            return $返回; // 加密
        } catch (Exception $错误) {}
        return null;
    }
    
    public static byte[] 解密(String $方法,byte[] $内容,String $密码) {
        try {           
            KeyGenerator $创建 = KeyGenerator.getInstance($方法);
            $创建.init(128, new SecureRandom($密码.getBytes()));
            SecretKey $秘钥 = $创建.generateKey();
            byte[] $格式 = $秘钥.getEncoded();
            SecretKeySpec $秘钥格式 = new SecretKeySpec($格式, $方法);
            Cipher $密码器 = Cipher.getInstance("AES");// 创建密码器
            $密码器.init(Cipher.DECRYPT_MODE, $秘钥格式);// 初始化
            byte[] $返回 = $密码器.doFinal($内容);
            return $返回; // 加密
        } catch (Exception $错误) {}
        return null;
    }
    
    public static class AES {
        
        public static byte[] 加密(byte[] $内容,String $密码) {
            return 加密.加密("AES",$内容,$密码);
        }
        
        public static byte[] 解密(byte[] $内容,String $密码) {
            return 加密.解密("AES",$内容,$密码);
        }

    }
    
    public static class DES {

        public static byte[] 加密(byte[] $内容,String $密码) {
            return 加密.加密("DES",$内容,$密码);
        }

        public static byte[] 解密(byte[] $内容,String $密码) {
            return 加密.解密("DES",$内容,$密码);
        }

    }

}
