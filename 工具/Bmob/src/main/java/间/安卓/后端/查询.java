package 间.安卓.后端;

import cn.bmob.v3.BmobQuery;
import 间.收集.集合;
import 间.接口.方法;
import 间.安卓.工具.环境;
import 间.安卓.后端.接口.数据查询回调;

public class 查询 extends BmobQuery {
    
    public 查询() {
        super();
    }
    
    public 查询(String $表名) {
        super($表名);
    }
    
    public 查询(String $表名,String $SQL) {
        this($表名);
        setSQL($SQL);
    }
    
    public 查询 等于(String $键值,Object $内容) {
        addWhereEqualTo($键值,$内容);
        return this;
    }
    
    public 查询 不等(String $键值,Object $内容) {
        addWhereNotEqualTo($键值,$内容);
        return this;
    }
    
    public 查询 包含(String $键值,String $内容) {
        addWhereContains($键值,$内容);
        return this;
    }
    
    public 查询 以结束(String $键值,String $内容) {
        addWhereEndsWith($键值,$内容);
        return this;
    }
    
    public 查询 以开始(String $键值,String $内容) {
        addWhereEndsWith($键值,$内容);
        return this;
    }
    
    
    public 查询 匹配(String $键值,String $内容) {
        addWhereMatches($键值,$内容);
        return this;
    }
    
    public 查询 截止(int $数量) {
        setLimit($数量);
        return this;
    }
    
    public 查询 开始(int $数量) {
        setSkip($数量);
        return this;
    }
    
    public 查询 和(查询... $所有) {
        and(new 集合<查询>($所有));
        return this;
    }
    
    public 查询 或(查询... $所有) {
        or(new 集合<查询>($所有));
        return this;
    }
    
    public void 查询(方法 $回调) {
        findObjects(环境.取应用(),new 数据查询回调($回调));
    }
    
    
}
