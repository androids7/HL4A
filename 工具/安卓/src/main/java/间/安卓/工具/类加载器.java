package 间.安卓.工具;

import dalvik.system.DexClassLoader;
import 间.工具.字符;
import 间.工具.散列;

public class 类加载器 extends DexClassLoader {

    private static ClassLoader 基本加载器 = 类加载器.class.getClassLoader().getSystemClassLoader();
    private static String 缓存 = 文件.取数据目录("DEX");

    private DexClassLoader 最新加载器;

    public 类加载器() {
        super(null, 缓存, "", 基本加载器);
        最新加载器 = this;
    }

    public void 加载(String $地址) {
        if (!文件.是文件($地址)) return;
        $地址 = 文件.检查地址($地址);
        if (!字符.以开始($地址, "/data/")) {
            String $散列 = 散列.文件("MD5", $地址);
            String $文件 = 文件.取数据目录("注入", $散列);
            if (!文件.是文件($文件)) {
                文件.复制($地址, $文件);
            }
            $地址 = $文件;
        }
        最新加载器 = new DexClassLoader($地址, 缓存, null, 最新加载器);
    }

    @Override
    public Class<?> loadClass(String $类名,boolean $链接) throws ClassNotFoundException {
        return loadClass($类名);
    }

    @Override
    public Class<?> loadClass(String $类名) throws ClassNotFoundException {
        return 最新加载器.loadClass($类名);
    }

    public Class<?> 取类(String $类名) {
        if ($类名 == null)return null;
        try {
            return loadClass($类名);
        } catch (ClassNotFoundException $错误) {}
        return null;
    }

}
