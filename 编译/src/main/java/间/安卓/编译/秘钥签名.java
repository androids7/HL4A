package 间.安卓.编译;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import kellinwood.security.zipsigner.ZipSigner;
import kellinwood.security.zipsigner.optional.KeyStoreFileManager;
import java.security.KeyStoreException;
import 间.安卓.工具.文件;
import 间.工具.时间;

public class 秘钥签名 {

    private String 别名;
    private X509Certificate 公共秘钥;
    private PrivateKey 私有秘钥;
    private ZipSigner 签名;

    public 秘钥签名(String $文件,String $密码,String $别名,String $别名密码) {
        if (!文件.是文件($文件)) {
            throw new RuntimeException("秘钥不存在 ~");
        }
        try {
            KeyStore $仓库 = KeyStoreFileManager.loadKeyStore(文件.检查地址($文件), $密码.toCharArray());
            //别名 = $仓库.aliases().nextElement();
            别名 = $别名;
            公共秘钥 = (X509Certificate) $仓库.getCertificate($别名);
            try {
                私有秘钥 = (PrivateKey) $仓库.getKey($别名, $别名密码.toCharArray());
            } catch (Exception $错误) {
                throw new RuntimeException("错误的别名密码");
            }
            if (公共秘钥 == null) {
                throw new RuntimeException("错误的别名 ~");
            }
            签名 = new ZipSigner();
            签名.setKeys("MikaGuranN", 公共秘钥, 私有秘钥, "SHA1withRSA", null);
        } catch (Exception $错误) {
            throw new RuntimeException("秘钥密码错误 ~");
        }

    }

    public void 签名(String $文件) {
        签名($文件, $文件);
    }

    public void 签名(String $文件,String $输出) {
        $文件 = 文件.检查地址($文件);
        $输出 = 文件.检查地址($输出);
        if ($文件.toLowerCase().equals($输出.toLowerCase())) {
            String $缓存 = 文件.取缓存目录("签名-" + 时间.时间戳());
            文件.复制($文件,$缓存);
            签名.signZip($缓存, $输出);
            文件.删除($缓存);
            return;
        }
        签名.signZip($文件, $输出);
    }

}
