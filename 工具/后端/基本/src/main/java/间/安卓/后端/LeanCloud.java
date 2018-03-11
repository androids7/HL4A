package 间.安卓.后端;

import com.avos.avoscloud.AVOSCloud;
import 间.安卓.工具.环境;
import com.avos.avoscloud.AVCloud;

public class LeanCloud {
    
    public static void 初始化(String $ID,String $KEY) {
        AVOSCloud.initialize(环境.取应用(),$ID,$KEY);
    }
    
}
