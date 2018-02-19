package 间.收集;

import static com.github.program_in_chinese.junit4_in_chinese.断言.相等;

import org.junit.runner.RunWith;

import com.github.program_in_chinese.junit4_in_chinese.测试;
import com.github.program_in_chinese.junit4_in_chinese.运行器;

@RunWith(运行器.class)
public class 哈希表测试 {

    @测试
    public void 删除键值() {
        哈希表<Integer, String> 测试表 = new 哈希表<>();
        测试表.设置(1, "1");
        相等(1, 测试表.长度());
        测试表.删除(1);
        相等(0, 测试表.长度());
    }

}
