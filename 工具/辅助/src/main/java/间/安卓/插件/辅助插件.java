package 间.安卓.插件;

import 间.安卓.工具.辅助;

public class 辅助插件 extends 应用插件 {

    @Override
    public void 应用出错(Thread $线程,Exception $错误) {
        辅助.停止();
    }
    
}
