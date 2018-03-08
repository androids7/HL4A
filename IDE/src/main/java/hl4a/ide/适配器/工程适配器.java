package hl4a.ide.适配器;

import android.view.View;
import hl4a.ide.界面.工程界面;
import hl4a.ide.布局.布局_新建工程;
import hl4a.ide.布局.布局_适配器_工程;
import hl4a.ide.工具.工程;
import java.io.File;
import 间.安卓.工具.提示;
import 间.安卓.工具.文件;
import 间.安卓.弹窗.基本弹窗;
import 间.安卓.组件.基本界面;
import 间.安卓.视图.列表视图;
import 间.安卓.视图.文本视图;
import 间.安卓.视图.适配器.数组适配器;
import 间.接口.方法;
import 间.收集.哈希表;
import 间.接口.调用;

public class 工程适配器 extends 数组适配器 {

    列表视图 列表;
    基本界面 界面;

    布局_新建工程 新建工程布局;

    public 工程适配器(列表视图 $视图) {
        super($视图.getContext());
        界面 = (基本界面)$视图.getContext();
        列表 = $视图;
        更新工程();
        $视图.置适配器(this);
        $视图.置项目单击事件(列表单击);
        新建工程布局 = new 布局_新建工程(界面);
        新建工程 = new 基本弹窗(界面);
        新建工程.置标题("新建工程");
        新建工程.置内容(新建工程布局);
        新建工程.置中按钮("取消", 调用.配置(新建工程,"隐藏"));
        新建工程.置右按钮("创建", 创建);
    }

    基本弹窗 新建工程;

    方法 创建 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            String $包名 = 新建工程布局.包名.取文本();
            String $工程名 = 新建工程布局.工程名.取文本();
            if ("".equals($包名)) {
                提示.普通("包名不能为空 ~");
            } else if (!工程.检查包名($包名)) {
            } else if ("".equals($工程名)) {
                提示.普通("工程名不能为空 ~");
            } else if (工程.检查($包名)) {
                提示.普通("包名已存在 ~");
            } else {
                新建工程布局.工程名.置文本("");
                新建工程布局.包名.置文本("");
                工程.创建($工程名, $包名);
                更新工程();
                新建工程.隐藏();
            }
            return null;
        }
    };

    方法 列表单击 = new 方法() {
        @Override
        public Object 调用(Object[] $参数) {
            int $键值 = $参数[2];
            if ($键值 == 0) {
                新建工程.显示();
            } else {
                View $项目 = (View)$参数[1];
                哈希表 $表 = (哈希表)$项目.getTag();
                String $地址 = (String)$表.读取("地址");
                界面.跳转界面(工程界面.class, $地址);
            }
            return null;
        }
    };

    public void 更新工程() {
        数据.清空();
        File[] $所有 = 文件.取文件列表(工程.工程目录);
        添加项目("新建工程", null,null);
        for (File $单个 : $所有) {
            if ($单个.isDirectory()) {
                工程 $工程 = 工程.读取($单个.getName());
                if ($工程 != null && $工程.信息 != null) {
                    if (!$单个.getName().equals($工程.信息.包名)) {
                        $工程.信息.包名 = $单个.getName();
                        $工程.保存();
                    }
                    添加项目($工程.信息.工程名, $单个.getName(),$工程.取地址("图标.png"));
                }
            }
        }
        发送重绘事件();
    }


    void 添加项目(String $内容,String $地址,String $图片) {
        哈希表<String,Object> $添加 = new 哈希表<>();
        if ($图片 != null && !文件.是文件($图片)) {
            $图片 = "#assets/android.png";
        }
        $添加.设置("图片", $图片);
        $添加.设置("内容", $内容);
        $添加.设置("地址", $地址);
        数据.添加($添加);
    }

    @Override
    public View 创建() {
        return new 布局_适配器_工程(上下文);
    }
    
    @Override
    public View 处理(View $视图,哈希表<String,String> $参数) {
        $视图.setTag($参数);
        布局_适配器_工程 $工程 = (布局_适配器_工程)$视图;
        String $图片 = $参数.读取("图片");
        if ($图片 == null) {
            $工程.背景.隐藏();
        } else {
            $工程.图片.置图片($图片);
        }
        $工程.文本.置文本($参数.读取("内容"));
        return $视图;
    }

}
