package 间.收集;

import static com.github.program_in_chinese.junit4_in_chinese.断言.相等;

import org.junit.runner.RunWith;

import com.github.program_in_chinese.junit4_in_chinese.测试;
import com.github.program_in_chinese.junit4_in_chinese.运行器;

import 间.收集.集合;

@RunWith(运行器.class)
public class 集合测试 {

    @测试
    public void 添加() {
        集合<Integer> 测试集 = new 集合<>();
        测试集.添加(1);
        相等(1, 测试集.数量());
    }

    @测试
    public void 一维数组到集合() {
        String[] 数组 = {"1", "2"};
        集合 测试集合 = 集合.到集合(数组);
        相等(2, 测试集合.数量());
        相等("1", 测试集合.读取(0));
        相等("2", 测试集合.读取(1));
    }

    @测试
    public void 二维数组到集合() {
        String[][] 数组 = {{"1", "2"}, {"3"}};
        集合 测试集合 = 集合.到集合(数组);
        相等(3, 测试集合.数量());
        相等("1", 测试集合.读取(0));
        相等("2", 测试集合.读取(1));
        相等("3", 测试集合.读取(2));
    }
}