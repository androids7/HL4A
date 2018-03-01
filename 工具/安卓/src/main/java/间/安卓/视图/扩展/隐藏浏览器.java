package 间.安卓.视图.扩展;

import android.content.Context;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import 间.安卓.视图.浏览器;
import 间.工具.字符;
import 间.安卓.工具.提示;

public class 隐藏浏览器 extends 浏览器 {

    public 隐藏浏览器(Context $上下文) {
        super($上下文, false);
        隐藏();
        setDownloadListener(new 隐藏下载监听());
        setWebViewClient(new 隐藏浏览器实例());
        setWebChromeClient(new 隐藏Chrome实例());
    }
    
    public class 隐藏浏览器实例 extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView $视图,String $链接) {
            if (字符.以开始($链接, "http")) {
                //$视图.loadUrl($链接);
                new 隐藏浏览器(getContext()).置链接($链接);
                return true;
            } else {
                return false;
            }
        }

    }

    public class 隐藏Chrome实例 extends WebChromeClient {

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

    public class 隐藏下载监听 implements DownloadListener {

        @Override
        public void onDownloadStart(String $地址,String $US,String $描述,String $类型,long $长度) {
            // 禁止下载
        }


    }


}
