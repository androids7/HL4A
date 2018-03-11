package 间.安卓.后端;

public class BMOB {
    
    public static String 地址 = "https://api.bmob.cn/1";
    protected static String RESTFUL秘钥;
    protected static String 应用秘钥;
    
    public static void 初始化(String $AID,String $RID) {
        应用秘钥 = $AID;
        RESTFUL秘钥 = $RID;
    }
    
}
