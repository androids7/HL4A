package 间.安卓.编译;

import kellinwood.security.zipsigner.optional.CertCreator;
import kellinwood.security.zipsigner.optional.DistinguishedNameValues;
import 间.安卓.工具.文件;

public class 签名创建 {

    private DistinguishedNameValues 设置;
    
    private String 密码;
    private String 别名;
    private String 别名密码;
    
    public 签名创建(String $别名,String $密码) {
        this($别名,$密码,$密码);
    }
    
    public 签名创建(String $别名,String $密码,String $别名密码) {
        设置 = new DistinguishedNameValues();
        密码 = $密码;
        别名 = $别名;
        别名密码 = $别名密码;
        置国家代码("CN");
        置名称("");
        置组织("");
        置公司("");
        置省份("");
        置街道("");
    }
    
    public void 置名称(String $名称) {
        设置.setCommonName($名称);
    }
    
    public void 置组织(String $组织) {
        设置.setOrganization($组织);
    }
    
    public void 置公司(String $公司) {
        设置.setOrganizationalUnit($公司);
    }
    
    public void 置国家代码(String $国家) {
        设置.setCountry($国家);
    }
    
    public void 置城市(String $城市) {
        设置.setLocality($城市);
    }
    
    public void 置省份(String $省份) {
        设置.setState($省份);
    }
    
    public void 置街道(String $街道) {
        设置.setStreet($街道);
    }
    
    public void 创建(String $输出) {
        $输出 = 文件.检查地址($输出);
        文件.创建目录(文件.取目录($输出));
        if (文件.是文件($输出)) 文件.删除($输出);
        CertCreator.createKeystoreAndKey($输出, 密码.toCharArray(), "RSA", 2048, 别名, 别名密码.toCharArray(), "SHA1withRSA",30, 设置);
    }
   
}
