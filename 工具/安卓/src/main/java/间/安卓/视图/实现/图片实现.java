package 间.安卓.视图.实现;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import 间.安卓.工具.文件;
import 间.安卓.工具.视图;
import android.support.v4.graphics.BitmapCompat;
import android.graphics.drawable.BitmapDrawable;

public class 图片实现 {
    
    public static void 置图片(ImageView $视图,Object $图片) {
        Bitmap $对象 = 视图.检查图片($图片);
        if ($对象 != null) {
            $视图.setImageBitmap($对象);
        } else {
            if ($图片 instanceof String && 文件.是网络文件((String)$图片)) {
                $视图.setImageURI(Uri.parse((String)$图片));
            } else if ($图片 instanceof Integer) {
                $视图.setImageResource((Integer)$图片);
            }
        }
    }
    
    public static Bitmap 取图片(ImageView $视图) {
        return ((BitmapDrawable)$视图.getBackground()).getBitmap();
    }
    
}
