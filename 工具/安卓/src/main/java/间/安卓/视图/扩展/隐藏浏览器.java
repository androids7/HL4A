package 间.安卓.视图.扩展;

import android.content.Context;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import 间.安卓.视图.浏览器;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.webkit.JsPromptResult;

public class 隐藏浏览器 extends 浏览器 {

    /*

     这是什么？
     禁止操作并隐藏自己的浏览器

     */

    public 隐藏浏览器(Context $上下文) {
        super($上下文, false);
        隐藏();
        setDownloadListener(new 下载监听());
        setWebChromeClient(new Chrome实例());
    }

    public class Chrome实例 extends WebChromeClient {

        @Override
        public boolean onJsConfirm(WebView view,String url,String message,JsResult result) {
            result.cancel();
            return false;
        }

        @Override
        public boolean onJsPrompt(WebView view,String url,String message,String defaultValue,JsPromptResult result) {
            result.cancel();
            return false;
        }

        @Override
        public boolean onJsAlert(WebView view,String url,String message,JsResult result) {
            result.cancel();
            return false;
        }


    }

    public class 下载监听 implements DownloadListener {

        @Override
        public void onDownloadStart(String $地址,String $US,String $描述,String $类型,long $长度) {
            // 禁止下载
        }


    }


}
