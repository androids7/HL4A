package hl4a.ide.适配器;

import android.content.Context;
import android.view.View;
import hl4a.ide.工具.应用;
import hl4a.ide.布局.布局_适配器_应用;
import hl4a.ide.应用配置信息;
import java.io.File;
import 间.安卓.工具.处理;
import 间.安卓.工具.文件;
import 间.安卓.视图.适配器.基本适配器;
import 间.收集.哈希表;
import 间.收集.集合;
import 间.安卓.工具.图片;

public class 应用适配器 extends 基本适配器 {

    public Context 上下文;

    public 应用适配器(Context $上下文) {
        上下文 = $上下文;
    }

    public void 更新() {
        数据 = new 集合<>();
        File[] $列表 = 文件.取文件列表(应用配置信息.应用保存);
        for (File $单个 : $列表) {
            if (!$单个.isDirectory() || !文件.是文件($单个.getPath() + "/应用.yml")) continue;
            哈希表<String,Object> $参数 = new 哈希表<>();
            应用 $应用 = 应用.打开($单个.getName());
            if ($应用 == null) continue;
            $参数.设置("应用", $应用);
            数据.添加($参数);
        }
        处理.主线程(this,"发送重绘事件");
    }

    @Override
    public View 创建() {
        return new 布局_适配器_应用(上下文);
    }

    @Override
    public View 处理(View $视图,哈希表<String, Object> $参数) {
        应用 $应用 = (应用)$参数.读取("应用");
        布局_适配器_应用 $布局 = (布局_适配器_应用)$视图;
        $布局.内容 = $应用;
        $布局.名称.置文本($应用.信息.工程名);
        String $图标 = $应用.取地址("图标.png");
        $布局.图标.置图片(文件.是文件($图标) ? $图标 : android.R.drawable.sym_def_app_icon);
        return $视图;
    }



}
