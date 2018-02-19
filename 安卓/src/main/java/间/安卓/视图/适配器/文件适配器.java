package 间.安卓.视图.适配器;

import android.view.*;
import java.io.*;
import java.util.*;
import 间.接口.*;
import 间.安卓.视图.*;
import 间.安卓.资源.布局.*;
import 间.收集.*;
import 间.工具.*;

public class 文件适配器 extends 基本适配器 {

    private 列表视图 列表;
    private String 顶端;
    public String 附加;
    private 方法 方法;
    private 方法 长按;

    public 文件适配器(列表视图 $列表,方法 $方法,方法 $长按,String $顶端) {
        this($列表, $方法, $长按, $顶端, "/");
    }

    public 文件适配器(列表视图 $列表,方法 $方法,方法 $长按,String $顶端,String $开始) {
        列表 = $列表;
        顶端 = 文件.检查地址($顶端);
        附加 = $开始;
        方法 = $方法;
        长按 = $长按;
        列表.置项目单击事件(项目单击);
        列表.置项目长按事件(项目长按);
        刷新();
    }

    方法 项目单击 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            哈希表 $数据 = (哈希表)((View)$参数[1]).getTag();
            String $地址 = (String)$数据.读取("地址");
            String $名称 = (String)$数据.读取("内容");
            if ("/-返回上级-/".equals($地址)) {
                附加 = 文件.取目录(附加);
                刷新();
            } else if (文件.是目录($地址)) {
                附加 += "/" + 文件.取名称($地址);
                刷新();
            } else {
                调用.事件(方法, $地址, 顶端, 附加, $名称,$参数[1]);
            }
            return null;
        }
    };

    方法 项目长按 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            哈希表<String,String> $数据 = (哈希表<String,String>)((View)$参数[1]).getTag();
            String $地址 = $数据.读取("地址");
            String $名称 = $数据.读取("内容");
            if (文件.存在($地址)) {
                调用.事件(长按, $地址, 顶端, 附加, $名称,$参数[1]);
            }
            return null;
        }
    };

    @Override
    public View 创建() {
        return new 布局_适配器_数组(列表.getContext());
    }

    public void 刷新() {
        数据.清空();
        File $文件 = 文件.取文件对象(顶端 + "/" + 附加);
        File[] $所有 = 文件.取文件列表($文件.getPath());
        if (!("/".equals(附加))) {
            哈希表<String,Object> $数据 = new 哈希表<>();
            $数据.设置("内容", "返回上级 ~");
            $数据.设置("地址", "/-返回上级-/");
            数据.添加($数据);
        }
        for (File $单个 : $所有) {
            哈希表<String,Object> $数据 = new 哈希表<>();
            $数据.设置("内容", $单个.getName());
            $数据.设置("地址", $单个.getPath());
            数据.添加($数据);
        }
        发送重绘事件();
    }

    @Override
    public View 处理(View $视图,哈希表<String,Object> $参数) {
        $视图.setTag($参数);
        ((布局_适配器_数组)$视图).文本.置文本((String)$参数.读取("内容"));
        return $视图;
    }

}
