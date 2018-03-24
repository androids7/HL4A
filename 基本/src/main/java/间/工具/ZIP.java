package 间.工具;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import 间.收集.*;

public class ZIP {

    static class CentralDirectory {
        long offset;
        long size;
    }

    /* redefine those constant here because of bug 13721174 preventing to compile using the
     * constants defined in ZipFile */
    private static final int ENDHDR = 22;
    private static final int ENDSIG = 0x6054b50;

    /**
     * Size of reading buffers.
     */
    private static final int BUFFER_SIZE = 0x4000;

    /**
     * Compute crc32 of the central directory of an apk. The central directory contains
     * the crc32 of each entries in the zip so the computed result is considered valid for the whole
     * zip file. Does not support zip64 nor multidisk but it should be OK for now since ZipFile does
     * no9t either.
     */
    public static long 取CRC(String $地址) {
        try {
            RandomAccessFile raf = new RandomAccessFile(文件.检查地址($地址), "r");
            CentralDirectory dir = findCentralDirectory(raf);
            return computeCrcOfCentralDir(raf, dir);
        } catch (FileNotFoundException $错误) {
            错误.内容("参数 [地址] 错误 没有那样的文件: %s",$地址);
        } catch (IOException $错误) {
            错误.抛出($错误);
        }
        return 0;
    }

    private static CentralDirectory findCentralDirectory(RandomAccessFile raf) throws IOException,
    ZipException {
        long scanOffset = raf.length() - ENDHDR;
        if (scanOffset < 0) {
            throw new ZipException("File too short to be a zip file: " + raf.length());
        }

        long stopOffset = scanOffset - 0x10000 /* ".ZIP file comment"'s max length */;
        if (stopOffset < 0) {
            stopOffset = 0;
        }

        int endSig = Integer.reverseBytes(ENDSIG);
        while (true) {
            raf.seek(scanOffset);
            if (raf.readInt() == endSig) {
                break;
            }

            scanOffset--;
            if (scanOffset < stopOffset) {
                throw new ZipException("End Of Central Directory signature not found");
            }
        }
        // Read the End Of Central Directory. ENDHDR includes the signature
        // bytes,
        // which we've already read.

        // Pull out the information we need.
        raf.skipBytes(2); // diskNumber
        raf.skipBytes(2); // diskWithCentralDir
        raf.skipBytes(2); // numEntries
        raf.skipBytes(2); // totalNumEntries
        CentralDirectory dir = new CentralDirectory();
        dir.size = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
        dir.offset = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
        return dir;
    }

    /* Package visible for testing */
    private static long computeCrcOfCentralDir(RandomAccessFile raf,CentralDirectory dir)
    throws IOException {
        CRC32 crc = new CRC32();
        long stillToRead = dir.size;
        raf.seek(dir.offset);
        int length = (int) Math.min(BUFFER_SIZE, stillToRead);
        byte[] buffer = new byte[BUFFER_SIZE];
        length = raf.read(buffer, 0, length);
        while (length != -1) {
            crc.update(buffer, 0, length);
            stillToRead -= length;
            if (stillToRead == 0) {
                break;
            }
            length = (int) Math.min(BUFFER_SIZE, stillToRead);
            length = raf.read(buffer, 0, length);
        }
        return crc.getValue();
    }

    public static byte[] 读取(String $文件,String $地址) {
        ZipFile $压缩 = null;
        try {
            $压缩 = new ZipFile(文件.取文件对象($文件));
            ZipEntry $进入 = $压缩.getEntry($地址);
            if ($进入.isDirectory()) {
                return null;
            } else {
                return 字节.读取($压缩.getInputStream($进入));
            }
        } catch (Exception $错误) {
            return null;
        } finally {
            流.关闭($压缩);
        }
    }
    
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
