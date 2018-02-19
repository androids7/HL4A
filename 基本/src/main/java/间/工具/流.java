package 间.工具;

import java.io.*;
import java.nio.channels.*;

public class 流 {

    public static void 关闭(InputStream $流) {
        if ($流 == null)
            return;
        try {
            $流.close();
        } catch (IOException $错误) {
        }
    }

    public static void 关闭(OutputStream $流) {
        if ($流 == null)
            return;
        try {
            $流.close();
        } catch (IOException $错误) {
        }
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
        if ($流 == null)
            return null;
        try {
            ByteArrayOutputStream $输出 = 流.输出.字节();
            int $长度 = 0;
            byte[] $缓冲 = new byte[$流.available()];
            while (($长度 = $流.read($缓冲)) > 0) {
                $输出.write($缓冲, 0, $长度);
            }
            return $输出.toByteArray();
        } catch (IOException $错误) {
        }
        return null;
    }

    public static void 保存(OutputStream $流, byte[] $内容) {
        if ($流 == null)
            return;
        if ($内容 == null)
            return;
        try {
            $流.write($内容);
            $流.flush();
        } catch (IOException $错误) {
        }
    }
    
    public static void 保存(OutputStream $流, InputStream $内容) {
        保存($流,读取($内容));
    }

    public static class 输入 {

        public static InputStream 自身(String... $相对) {
            return 输入.class.getClassLoader().getResourceAsStream(字符.分解($相对,"/"));
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

        public static FileOutputStream 文件(String $地址, boolean $追加) {
            文件.创建文件($地址);
            try {
                return new FileOutputStream(文件.检查地址($地址), $追加);
            } catch (FileNotFoundException $错误) {
            }
            return null;
        }

    }

}
