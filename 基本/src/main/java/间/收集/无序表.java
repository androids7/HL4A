package 间.收集;

import java.util.*;

/**
 * 对{@link HashMap}的封装
 */
public class 无序表<键值, 内容> extends HashMap<键值, 内容> {

    private static final long serialVersionUID = 3530384865377424460L;

    /**
     * 创建初始长度为16的空<tt>哈希表</tt>, 默认载荷系数0.75
     */
    public 无序表() {
        super();
    }

    /**
     * 创建新的<tt>哈希表</tt>, 包含指定<tt>Map</tt>中的相同映射.  <tt>哈希表</tt>默认载荷系数0.75,
     * 初始长度足够包含指定<tt>Map</tt>中的映射
     *
     * @param   $表 该表中的映射将被置于返回的新表中
     * @throws  NullPointerException, 如果指定表为null
     */
    public 无序表(Map<键值,内容> $表) {
        super($表);
    }
    
    /**
     * 返回表中键值到内容的映射个数
     *
     * @return 表中键值到内容的映射个数
     */
    public int 长度() {
        return size();
    }

    /**
     * 如果此表包含指定键值的映射, 返回<tt>true</tt>
     *
     * @param   $键值   在此表中查找的键值
     * @return <tt>true</tt>, 如果此表包含指定键值的映射
     */
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
