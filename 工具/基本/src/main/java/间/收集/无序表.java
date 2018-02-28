package 间.收集;

import java.util.*;

public class 无序表<键值, 内容> extends HashMap<键值, 内容> {

    private static final long serialVersionUID = 3530384865377424460L;

    public 无序表() {
        super();
    }

    public 无序表(Map<键值,内容> $表) {
        super($表);
    }
    
    public int 长度() {
        return size();
    }

    public Boolean 检查键值(键值 $键值) {
        return containsKey($键值);
    }
    
    public Boolean 检查内容(内容 $键值) {
        return containsValue($键值);
    }
    
    public Boolean 正查键值对(键值 $键值,内容 $内容) {
        return containsKey($键值) && 读取($键值).equals($内容);
    }
    
    public Boolean 反查键值对(内容 $键值,键值 $内容) {
        return 正查键值对($内容,$键值);
    }
    
    public Boolean 双查键值对(键值 $键值,键值 $内容) {
        return 正查键值对($键值,(内容)$内容) || 反查键值对((内容)$键值,$内容);
    }

    public 无序表<键值,内容> 设置(键值 $键值, 内容 $内容) {
        put($键值, $内容);
        return this;
    }

    public 内容 读取(键值 $键值) {
        return get($键值);
    }
    
    public 内容 删除(键值 $键值) {
        return remove($键值);
    }

    public void 清空() {
        clear();
    }

}
