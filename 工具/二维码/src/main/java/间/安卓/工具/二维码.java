package 间.安卓.工具;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;
import java.util.Vector;
import 间.工具.反射;
import 间.收集.无序集合;
import 间.接口.方法;
import 间.接口.调用;
import 间.接口.返回值;
import com.google.zxing.NotFoundException;

public class 二维码 {

    public static void 解析(final String $地址,final 方法 $回调) {
        new 线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    Bitmap $图片 = 图片.读取($地址);
                    if ($图片 == null) 调用.事件($回调,返回值.失败);
                    调用.事件($回调,同步解析($图片));
                    return null;
                }
            }).启动();
    }
    
    public static 返回值<String> 同步解析(Bitmap $图片) {
        
        MultiFormatReader $解析器 = new MultiFormatReader();
        // 解码的参数
        Hashtable<DecodeHintType, Object> $设置 = new Hashtable<DecodeHintType, Object>(2);
        // 可以解析的编码类型
        Vector<BarcodeFormat> $所有格式 = new Vector<BarcodeFormat>();
        if ($所有格式 == null || $所有格式.isEmpty()) {
            $所有格式 = new Vector<BarcodeFormat>();
            $所有格式.addAll(new 无序集合<BarcodeFormat>(反射.取所有枚举(BarcodeFormat.class)));
        }
        $设置.put(DecodeHintType.POSSIBLE_FORMATS, $所有格式);
        // 设置继续的字符编码格式为UTF8
        // 设置.put(DecodeHintType.CHARACTER_SET, "UTF8");
        // 设置解析配置参数
        $解析器.setHints($设置);

 
        try {
            return 返回值.创建($解析器.decodeWithState(new BinaryBitmap(new HybridBinarizer(new 图片源码($图片)))).getText());
        } catch (Exception $错误) {
            return 返回值.创建(null,$错误);
        }

    }
    
    public static Bitmap 创建(String $文本) {
        return 创建($文本,1024);
    }
    
    public static Bitmap 创建(String $文本,Object $宽度高度) {
        return 创建($文本,$宽度高度,颜色.黑色);
    }
    
    public static Bitmap 创建(String $文本,Object $宽度高度,Object $二维码颜色) {
        return 创建($文本,$宽度高度,$二维码颜色,颜色.白色);
    }
    
    public static Bitmap 创建(String $文本,Object $宽度高度,Object $二维码颜色,Object $背景颜色) {
        return 创建($文本,$宽度高度,$二维码颜色,$背景颜色,null);
    }
    
    public static Bitmap 创建(String $文本,Object $宽度高度,Object $二维码颜色,Object $背景颜色,Bitmap $图片) {
        try {
            int $宽高 = 视图.检查大小($宽度高度);
            int $颜色 = 视图.检查颜色($二维码颜色);
            int $背景 = 视图.检查颜色($背景颜色);
            Bitmap $图标 = 转换图标($图片, $宽高, $宽高);
            int $偏移X = $宽高 / 2;
            int $偏移Y = $宽高 / 2;
            int $图标宽 = 0;
            int $图标高 = 0;
            if ($图标 != null) {
                $图标宽 = $图标.getWidth();
                $图标高 = $图标.getHeight();
                $偏移X = ($宽高 - $图标宽) / 2;
                $偏移Y = ($宽高 - $图标高) / 2;
            }
            Hashtable<EncodeHintType, Object> $设置 = new Hashtable<EncodeHintType, Object>();
            $设置.put(EncodeHintType.CHARACTER_SET, "utf-8");
            $设置.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            $设置.put(EncodeHintType.MARGIN, 0);
            BitMatrix $矩阵 = new QRCodeWriter().encode($文本, BarcodeFormat.QR_CODE, $宽高, $宽高, $设置);
            int[] $像素 = new int[$宽高 * $宽高];
            for (int y = 0; y < $宽高; y++) {
                for (int x = 0; x < $宽高; x++) {
                    if (x >= $偏移X && x < $偏移X + $图标宽 && y >= $偏移Y && y < $偏移Y + $图标高) {
                        int $单个 = $图标.getPixel(x - $偏移X, y - $偏移Y);
                        if ($单个 == 0) {
                            if ($矩阵.get(x, y)) {
                                $单个 = $颜色;
                            } else {
                                $单个 = $背景;
                            }
                        }
                        $像素[y * $宽高 + x] = $单个;
                    } else {
                        if ($矩阵.get(x, y)) {
                            $像素[y * $宽高 + x] = $颜色;
                        } else {
                            $像素[y * $宽高 + x] = $背景;
                        }
                    }
                }
            }
            Bitmap $返回 = Bitmap.createBitmap($宽高, $宽高, Bitmap.Config.ARGB_8888);
            $返回.setPixels($像素, 0, $宽高, 0, 0, $宽高, $宽高);
            return $返回;
        } catch (WriterException $错误) {}
        return null;
    }

    private static Bitmap 转换图标(Bitmap $图标,int w,int h) {
        if ($图标 == null)return null;
        Matrix matrix = new Matrix();
        float scaleFactor = Math.min(w * 1.0f / 5 / $图标.getWidth(), h * 1.0f / 5 / $图标.getHeight());
        matrix.postScale(scaleFactor, scaleFactor);
        Bitmap result = Bitmap.createBitmap($图标, 0, 0, $图标.getWidth(),   $图标.getHeight(), matrix, true);
        return result;
    }

    public static class 图片源码 extends LuminanceSource {

        private byte 像素[];

        public 图片源码(Bitmap $图片) {
            super($图片.getWidth(), $图片.getHeight());
            // 首先，要取得该图片的像素数组内容
            int[] data = new int[$图片.getWidth() * $图片.getHeight()];
            this.像素 = new byte[$图片.getWidth() * $图片.getHeight()];
            $图片.getPixels(data, 0, getWidth(), 0, 0, getWidth(), getHeight());

            // 将int数组转换为byte数组，也就是取像素值中蓝色值部分作为辨析内容
            for (int i = 0; i < data.length; i++) {
                this.像素[i] = (byte) data[i];
            }
        }

        @Override
        public byte[] getMatrix() {
            // 返回我们生成好的像素数据
            return 像素;
        }

        @Override
        public byte[] getRow(int y, byte[] row) {
            // 这里要得到指定行的像素数据
            System.arraycopy(像素, y * getWidth(), row, 0, getWidth());
            return row;
        }
    }
    
}
