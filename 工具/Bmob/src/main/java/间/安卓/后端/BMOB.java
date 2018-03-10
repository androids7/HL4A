package 间.安卓.后端;

import cn.bmob.v3.Bmob;
import 间.安卓.工具.环境;

public class BMOB {
    
    public static void 初始化(String $应用id) {
        Bmob.initialize(环境.取应用(),$应用id);
    }
    
}
