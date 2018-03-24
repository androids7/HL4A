package 间.接口;

import cn.hutool.core.io.StreamProgress;

public class 流进度 implements StreamProgress {

    private 方法 事件;
    private 方法 开始;
    private 方法 结束;
    
    public 流进度(方法 $事件,方法 $开始,方法 $结束) {
        事件 = $事件;
        开始 = $开始;
        结束 = $结束;
    }
    
    @Override
    public void start() {
        调用.事件(开始);
    }

    @Override
    public void progress(long $进度) {
        调用.事件(事件,$进度);
    }

    @Override
    public void finish() {
        调用.事件(结束);
    }
    
}
