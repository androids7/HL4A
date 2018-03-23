package 间.安卓.后端;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.后端错误;
import java.util.Arrays;
import java.util.List;
import 间.接口.方法;
import 间.接口.调用;
import 间.接口.返回值;
import 间.收集.无序集合;
import 间.收集.集合;

public class 查询<类型 extends AVObject> extends AVQuery<类型> {

    public 查询(String $表名) {
        super($表名);
    }

    public static <类型 extends AVObject> 查询<类型> 创建(String $表名) {
        return new 查询<类型>($表名);
    }

    public 查询<类型> 等于(String $键值,Object $内容) {
        whereEqualTo($键值, $内容);
        return this;
    }

    public 查询<类型> 不等(String $键值,Object $内容) {
        whereNotEqualTo($键值, $内容);
        return this;
    }

    public 查询<类型> 大于(String $键值,Object $内容) {
        whereGreaterThan($键值, $内容);
        return this;
    }

    public 查询<类型> 大于等于(String $键值,Object $内容) {
        whereGreaterThanOrEqualTo($键值, $内容);
        return this;
    }

    public 查询<类型> 小于(String $键值,Object $内容) {
        whereLessThan($键值, $内容);
        return this;
    }

    public 查询<类型> 小于等于(String $键值,Object $内容) {
        whereLessThanOrEqualTo($键值, $内容);
        return this;
    }

    public 查询<类型> 以开始(String $键值,String $内容) {
        whereStartsWith($键值, $内容);
        return this;
    }

    public 查询<类型> 以结束(String $键值,String $内容) {
        whereEndsWith($键值, $内容);
        return this;
    }

    public 查询<类型> 包含(String $键值,String $内容) {
        whereContains($键值, $内容);
        return this;
    }

    public 查询<类型> 匹配(String $键值,String $内容) {
        whereMatches($键值, $内容);
        return this;
    }

    public 查询<类型> 空值(String $键值) {
        whereDoesNotExist($键值);
        return this;
    }

    public 查询<类型> 非空(String $键值) {
        whereExists($键值);
        return this;
    }


    public 查询<类型> 或(查询<类型>... $查询) {
        or((List<AVQuery<类型>>)Arrays.asList($查询));
        return this;
    }

    public 查询<类型> 与(查询<类型>... $查询) {
        and((List<AVQuery<类型>>)Arrays.asList($查询));
        return this;
    }

    public 查询<类型> 跳过(int $数量) {
        skip($数量);
        return this;
    }

    public 查询<类型> 截止(int $数量) {
        limit($数量);
        return this;
    }

    public 查询<类型> 升序(String $列) {
        orderByAscending($列);
        return this;
    }

    public 查询<类型> 降序(String $列) {
        orderByDescending($列);
        return this;
    }

    public 查询<类型> 附加升序(String $列) {
        addAscendingOrder($列);
        return this;
    }

    public 查询<类型> 附加降序(String $列) {
        addDescendingOrder($列);
        return this;
    }

    public 查询<类型> 限定(String... $字段) {
        selectKeys(Arrays.asList($字段));
        return this;
    }

    public 返回值<集合<类型>,后端错误> 查询() {
        try {
            return 返回值.创建(new 集合<类型>(find()), null);
        } catch (后端错误 $错误) {
            return 返回值.创建(null, $错误);
        }
    }

    public void 查询(final 方法 $回调) {
        findInBackground(new FindCallback<类型>() {
                @Override
                public void done(List<类型> $集合,后端错误 $错误) {
                    调用.事件($回调, 返回值.创建(new 集合<类型>($集合), $错误));
                }
            });
    }

    public 返回值<Integer,后端错误> 统计() {
        try {
            return 返回值.创建(count(), null);
        } catch (后端错误 $错误) {
            return 返回值.创建(null, $错误);
        }
    }

    public void 统计(final 方法 $回调) {
        countInBackground(new CountCallback() {
                @Override
                public void done(int $数量,后端错误 $错误) {
                    调用.事件($回调, 返回值.创建($数量, $错误));
                }
            });
    }

}
