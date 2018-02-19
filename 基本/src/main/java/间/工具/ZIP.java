package 间.工具;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import 间.收集.*;

public class ZIP {

    public static boolean 解压(String $文件,String $地址,String $输出) {
        try {
            ZipFile $压缩 = new ZipFile(文件.取文件对象($文件));
            ZipEntry $进入 = $压缩.getEntry($地址);
            if ($进入.isDirectory()) {
                return false;/*
            } else if ($进入.getSize() == 0) {
                文件.创建文件($输出);*/
            } else {
                字节.保存($输出, 字节.读取($压缩.getInputStream($进入)));
            }
            $压缩.close();
            return true;
        } catch (IOException $错误) {
        }
        return false;
    }

    public static boolean 解压(String $文件,String $输出) {
        File $对象 = 文件.取文件对象($文件);
        if ($对象.isFile()) {
            try {
                ZipFile $压缩文件 = new ZipFile($对象);
                if (!文件.是目录($输出)) {
                    文件.创建目录($输出);
                }
                Enumeration<? extends ZipEntry> $所有 = $压缩文件.entries();
                while ($所有.hasMoreElements()) {
                    ZipEntry $单个 = $所有.nextElement();
                    if ($单个.isDirectory()) {
                        文件.创建目录($输出 + "/" + $单个.getName());
                    } else {
                        流.保存(流.输出.文件($输出 + "/" + $单个.getName()), $压缩文件.getInputStream($单个));
                    }
                }
                $压缩文件.close();
                return true;
            } catch (IOException $错误) {}
        }
        return false;
    }

    public static boolean 压缩(String $地址,String $输出) {
        try {
            if (文件.存在($输出)) 文件.删除($输出);
            ZipOutputStream $输出流 = new ZipOutputStream(流.输出.文件($输出));
            File[] $所有 = 文件.取文件列表($地址);
            for (File $单个 : $所有) {
                if ($单个.isDirectory()) {
                    String $目录 =  $单个.getName() + "/";
                    $输出流.putNextEntry(new ZipEntry($目录));
                    遍历压缩($输出流, $单个.getPath(), $目录);
                } else {
                    InputStream $输入 = 流.输入.文件($单个.getPath());
                    $输出流.putNextEntry(new ZipEntry($单个.getName()));
                    int $长度 = 0;
                    byte[] $缓冲 = new byte[$输入.available()];
                    while (($长度 = $输入.read($缓冲)) > 0) {
                        $输出流.write($缓冲, 0, $长度);
                    }
                    流.关闭($输入);
                }
            }
            流.关闭($输出流);
            return true;
        } catch (IOException $错误) {}
        return false;
    }

    private static void 遍历压缩(ZipOutputStream $输出流,String $地址,String $附加) throws IOException {
        File[] $所有 = 文件.取文件列表($地址);
        for (File $单个 : $所有) {
            if ($单个.isDirectory()) {
                String $目录 = $附加 + $单个.getName() + "/";
                $输出流.putNextEntry(new ZipEntry($目录));
                遍历压缩($输出流, $单个.getPath(), $目录);
            } else {
                InputStream $输入 = 流.输入.文件($单个.getPath());
                $输出流.putNextEntry(new ZipEntry($附加 + $单个.getName()));
                int $长度 = 0;
                byte[] $缓冲 = new byte[$输入.available()];
                while (($长度 = $输入.read($缓冲)) > 0) {
                    $输出流.write($缓冲, 0, $长度);
                }
                流.关闭($输入);
            }
        }
    }

}
