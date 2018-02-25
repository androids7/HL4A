package 间.收集;

import java.util.*;
import 间.工具.*;

public class 集合<类型> extends LinkedList<类型> {

    public 集合() {
        super();
    }
    
    public 集合(Set<类型> $集合) {
        super($集合);
    }

    public 集合(List<类型> $集合) {
        super($集合);
    }

    public 集合(类型... $集合) {
        super();
        添加所有($集合);
    }

    public String 分解() {
        return 分解("");
    }

    public String 分解(String $分隔) {
        return 分解(this, $分隔);
    }

    public static String 分解(List $集合) {
        return 分解($集合, "");
    }

    public static String 分解(List $集合,String $分隔) {
        return 字符.分解($集合.toArray(), $分隔);
    }

    public 集合<类型> 添加(int $键值,类型 $对象) {
        add($键值, $对象);
        return this;
    }

    public 集合<类型> 添加(类型 $对象) {
        add($对象);
        return this;
    }

    public 集合<类型> 添加所有(类型[] $对象) {
        for (类型 $单个 : $对象) {
            add($单个);
        }
        return this;
    }

    public 集合<类型> 添加所有(int $键值,Collection<类型> $集合) {
        addAll($键值, $集合);
        return this;
    }

    public 集合<类型> 添加所有(Collection<类型> $集合) {
        addAll($集合);
        return this;
    }

    public 集合<类型> 删除(int $键值) {
        remove($键值);
        return this;
    }

    public 集合<类型> 删除对象(Object $对象) {
        remove($对象);
        return this;
    }

    public 类型 读取(int $键值) {
        return get($键值);
    }

    public 集合<类型> 设置(int $键值,类型 $对象) {
        set($键值, $对象);
        return this;
    }

    public Boolean 检查(int $键值) {
        return 读取($键值) == null;
    }

    public Boolean 检查(Object $内容) {
        return contains($内容);
    }

    public int 数量() {
        return size();
    }

    public 集合<类型> 清空() {
        clear();
        return this;
    }
    
    public Object[] 到数组() {
        return toArray();
    }

    public 类型[] 到数组(Class<类型> $类) {
        return 到数组($类,数量());
    }
    
    public 类型[] 到数组(Class<类型> $类,int $数量) {
        return toArray(数组.创建($类,$数量));
    }

}
