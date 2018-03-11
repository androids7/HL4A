package hl4a.ide;

import 间.安卓.组件.基本应用;
import 间.安卓.后端.LeanCloud;

public class Application extends 基本应用 {

    @Override
    public void 应用创建事件() {
        LeanCloud.初始化("Qttdpyw603TbXotgm9yNaKWM-gzGzoHsz","bIGCIm97yUPCAnLWawqGcnaP");
    }
    
}
