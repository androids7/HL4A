package 间.安卓.工具;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import 间.安卓.工具.图片;
import 间.安卓.工具.文件;
import 间.安卓.插件.应用插件;
import 间.安卓.插件.界面插件;
import 间.安卓.组件.基本应用;
import 间.安卓.组件.基本界面;
import 间.工具.流;
import 间.接口.方法;
import 间.接口.调用;
import android.app.Application;

public class 图片 {

    protected static void 初始化(Application $应用) {
        new 图片插件().注册($应用);
    }

    public static class 图片插件 extends 应用插件 {

        @Override
        public void 界面新建(Activity $界面) {
            new 界面选图().注册($界面);
        }
    }

    public static class 界面选图 extends 界面插件 {

        @Override
        public void 界面回调事件(int $请求码,int $返回码,Intent $意图) {
            if ($请求码 == 基本界面.请求码_图片选择) {
                if ($返回码 == 基本界面.返回码_成功) {
                    Uri $图片 = $意图.getData();
                    String $地址 = 文件.取URI路径($图片);
                    调用.事件(图片.回调, 文件.是文件($地址), $地址);
                } else {
                    调用.事件(图片.回调, false, null);
                }
                图片.回调 = null;
            }
        }

    }

    public static volatile 方法 回调;

    public static void 选择(Activity $界面,方法 $回调) {
        回调 = $回调;
        Intent $意图 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        $界面.startActivityForResult($意图, 基本界面.请求码_图片选择);
    }

    public static Bitmap 读取(String $地址) {
        if (!文件.是文件($地址)) return null;
        return BitmapFactory.decodeFile(文件.检查地址($地址));
    }

    public static Bitmap 读取(InputStream $输入流) {
        return BitmapFactory.decodeStream($输入流);
    }

    public static Bitmap 读取(byte[] $字节) {
        return BitmapFactory.decodeByteArray($字节, 0, $字节.length);
    }

    public static Bitmap 读取(BitmapDrawable $绘画) {
        return $绘画.getBitmap();
    }

    public static void 保存(Bitmap $图片,String $输出) {
        FileOutputStream $输出流 = 流.输出.文件($输出);
        $图片.compress(Bitmap.CompressFormat.PNG, 100, $输出流);
        流.关闭($输出流);
    }

    public static Bitmap 合成(Bitmap $背景,Bitmap $前景) {
        Bitmap bmp;
        int width = $背景.getWidth() < $前景.getWidth() ? $背景.getWidth() : $前景
            .getWidth();
        int height = $背景.getHeight() < $前景.getHeight() ? $背景.getHeight() : $前景
            .getHeight();

        if ($前景.getWidth() != width && $前景.getHeight() != height) {
            $前景 = 变焦($前景, width, height);
        }
        if ($背景.getWidth() != width && $背景.getHeight() != height) {
            $背景 = 变焦($背景, width, height);
        }

        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap($背景, 0, 0, null);
        canvas.drawBitmap($前景, 0, 0, paint);

        return bmp;
    }

    public static Bitmap 变焦(Bitmap bitmap,int w,int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                                            matrix, true);
        return newbmp;
    }


    public static Bitmap 圆角(Bitmap $图片,float $角度) {

        Bitmap output = Bitmap.createBitmap($图片.getWidth(),
                                            $图片.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, $图片.getWidth(), $图片.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, $角度, $角度, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap($图片, rect, rect, paint);

        return output;
    }


    public static Bitmap 倒影(Bitmap $图片) {
        final int reflectionGap = 4;
        int width = $图片.getWidth();
        int height = $图片.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = $图片.createBitmap($图片, 0, height / 2,
                                                  width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = $图片.createBitmap(width,
                                                       (height + height / 2), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap($图片, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, $图片.getHeight(), 0,
                                                   bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                                                   0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                        + reflectionGap, paint);

        return bitmapWithReflection;
    }



    public static Bitmap 压缩(Bitmap $图片) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        $图片.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            $图片.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    public static Bitmap 旋转(Bitmap $图片,int $角度) {
        if ($图片 == null)
            return null;
        int w = $图片.getWidth();
        int h = $图片.getHeight();
        // Setting post rotate to 90
        Matrix mtx = new Matrix();
        mtx.postRotate($角度);
        return Bitmap.createBitmap($图片, 0, 0, w, h, mtx, true);
    }


    public static Bitmap 水平翻转(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                   bitmap.getHeight(), matrix, false);
    }


    public static Bitmap 垂直翻转(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                   bitmap.getHeight(), matrix, false);
    }

    public static Bitmap 模糊(Bitmap $图片,int $半径) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Bitmap bitmap = $图片.copy($图片.getConfig(), true);
            final RenderScript rs = RenderScript.create(环境.取应用());
            final Allocation input = Allocation.createFromBitmap(rs, $图片, Allocation.MipmapControl.MIPMAP_NONE,                                                                                         Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius($半径);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);

            // clean up renderscript resources
            rs.destroy();
            input.destroy();
            output.destroy();
            script.destroy();

            return bitmap;
        }
        return null;
    }



}
