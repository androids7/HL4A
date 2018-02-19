package 间.安卓.工具;

import android.content.*;
import android.net.*;
import 间.接口.*;

public class 链接 {

    public static void 打开(String $地址) {
        Intent $意图= new Intent(Intent.ACTION_VIEW, Uri.parse($地址));
        $意图.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        环境.取应用().startActivity($意图);
    }

    public static class QQ {

        public static void 聊天(String $号码) {
            打开("mqqwpa://im/chat?chat_type=wpa&uin=" + $号码);
        }

        public static void 加群(String $号码) {
            打开("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=" + $号码 + "&card_type=group&source=qrcode");
        }

        public static void 名片(String $号码) {
            打开("mqqapi://card/show_pslcard?src_type=internal&source=sharecard&version=1&uin=" + $号码);
        }
        
        public static void 乐园部() {
            加群("538982726");
        }

        public static void 作者() {
            名片("1069952275");
        }

        public static void 反馈() {
            聊天("1069952275");
        }

    }

}
