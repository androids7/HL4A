package 间.工具;

import java.util.concurrent.atomic.*;
import 间.接口.*;

public class 计数 {

    AtomicInteger 数量 = new AtomicInteger(0);

    public void 加一() {
        数量.addAndGet(1);
    }

    public int 读取() {
        return 数量.get();
    }

}
