package 间.工具;

import cn.hutool.core.io.IoUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileLock;
import 间.接口.方法;
import 间.接口.流进度;

public class 流 {

    public static void 关闭(Closeable $流) {
        if ($流 == null) return;
        try {
            $流.close();
        } catch (IOException $错误) {}
    }

    public static void 关闭(AutoCloseable $流) {
        if ($流 == null) return;
        try {
            $流.close();
        } catch (Exception $错误) {}
    }

    public static FileLock 锁定(FileInputStream $流) {
        if ($流 == null)
            return null;
        try {
            return $流.getChannel().tryLock();
        } catch (IOException $错误) {
        }
        return null;
    }

    public static FileLock 锁定(FileOutputStream $流) {
        if ($流 == null)
            return null;
        try {
            return $流.getChannel().tryLock();
        } catch (IOException $错误) {
        }
        return null;
    }

    public static void 解锁(FileLock $锁) {
        if ($锁 == null)
            return;
        try {
            $锁.release();
        } catch (IOException $错误) {
        }
    }

    public static byte[] 读取(InputStream $流) {
        if ($流 == null)return null;
        return IoUtil.readBytes($流);
    }

    public static byte[] 读取(InputStream $流,int $长度) {
        if ($流 == null)return null;
        return IoUtil.readBytes($流, $长度);
    }

    public static String 读取文本(InputStream $流) {
        return 读取文本($流, "UTF-8");
    }

    public static String 读取文本(InputStream $流,String $格式) {
        if ($流 == null)return null;
        return IoUtil.read($流, $格式);
    }

    public static void 保存(OutputStream $流,byte[] $内容) {
        if ($流 == null)return;
        try {
            $流.write($内容);
        } catch (IOException $错误) {}
    }

    public static void 非阻塞保存(OutputStream $流,InputStream $内容) {
        非阻塞保存($流, $内容, null);
    }

    public static void 非阻塞保存(OutputStream $流,InputStream $内容,方法 $进度) {
        非阻塞保存($流, $内容, $进度, null, null);
    }

    public static void 非阻塞保存(OutputStream $流,InputStream $内容,方法 $进度,方法 $开始,方法 $结束) {
        非阻塞保存($流, $内容, IoUtil.DEFAULT_LARGE_BUFFER_SIZE, $进度, $开始, $结束);
    }

    public static void 非阻塞保存(OutputStream $流,InputStream $内容,int $缓存) {
        非阻塞保存($流, $内容, $缓存, null);
    }

    public static void 非阻塞保存(OutputStream $流,InputStream $内容,int $缓存,方法 $进度) {
        非阻塞保存($流, $内容, $缓存, $进度, null, null);
    }

    public static void 非阻塞保存(OutputStream $流,InputStream $内容,int $缓存,方法 $进度,方法 $开始,方法 $结束) {
        if ($流 == null || $内容 == null) return;
        IoUtil.copyByNIO($内容, $流, $缓存, 创建进度($进度, $开始, $结束));
    }

    public static void 保存(OutputStream $流,InputStream $内容) {
        if ($流 == null || $内容 == null) return;
        if ($流 instanceof FileOutputStream && $内容 instanceof FileInputStream) {
            IoUtil.copy((FileInputStream)$内容, (FileOutputStream)$流);
        } else {
            IoUtil.copy($内容, $流);
        }
    }

    public static void 保存(OutputStream $流,InputStream $内容,int $缓存) {
        if ($流 == null || $内容 == null) return;
        if ($流 instanceof FileOutputStream && $内容 instanceof FileInputStream) {
            IoUtil.copy((FileInputStream)$内容, (FileOutputStream)$流, $缓存);
        } else {
            IoUtil.copy($内容, $流, $缓存);
        }
    }

    protected static 流进度 创建进度(方法 $事件,方法 $开始,方法 $结束) {
        if ($事件 == null && $开始 == null && $结束 == null) {
            return null;
        }
        return new 流进度($事件, $开始, $结束);
    }

    public static class 输入 {

        public static InputStream 自身(String... $相对) {
            return 输入.class.getClassLoader().getResourceAsStream(字符.分解($相对, "/"));
        }

        public static ByteArrayInputStream 字节(byte[] $字节) {
            return new ByteArrayInputStream($字节);
        }

        public static FileInputStream 文件(String... $地址) {
            File $对象 = 文件.取文件对象($地址);
            if (!$对象.isFile())
                return null;
            try {
                return new FileInputStream($对象);
            } catch (FileNotFoundException $错误) {
            }
            return null;
        }

    }

    public static class 输出 {

        public static ByteArrayOutputStream 字节() {
            return new ByteArrayOutputStream();
        }

        public static FileOutputStream 文件(String $地址) {
            return 文件($地址, false);
        }

        public static FileOutputStream 文件(String $地址,boolean $追加) {
            文件.创建文件($地址);
            try {
                return new FileOutputStream(文件.检查地址($地址), $追加);
            } catch (FileNotFoundException $错误) {
            }
            return null;
        }

    }

}
