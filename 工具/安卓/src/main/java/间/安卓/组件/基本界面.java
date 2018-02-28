package 间.安卓.组件;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import hl4a.runtime.ProxyActivity;
import java.io.Serializable;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import 间.安卓.工具.布局;
import 间.安卓.工具.应用;
import 间.安卓.工具.提示;
import 间.安卓.工具.权限;
import 间.安卓.工具.环境;
import 间.安卓.工具.线程;
import 间.安卓.工具.设备;
import 间.安卓.插件.应用插件;
import 间.安卓.插件.界面插件;
import 间.工具.反射;
import 间.工具.时间;
import 间.工具.错误;
import 间.接口.方法;
import 间.收集.哈希表;
import 间.收集.集合;

public class 基本界面 extends Activity implements SwipeBackActivityBase {

    public static final int 请求码_图片选择 = 19132;
    public static final int 请求码_权限请求 = 13133;

    public static final int 返回码_成功 = Activity.RESULT_OK;
    public static final int 返回码_失败 = Activity.RESULT_CANCELED;

    public Object[] 传入参数;

    public 集合<界面插件> 所有插件 = new 集合<>();

    public void 注册插件(界面插件 $插件) {
        if ($插件 == null) return;
        synchronized (所有插件) {
            所有插件.添加($插件);
            $插件.界面 = this;
        }
    }

    @Override
    public void onCreate(Bundle $恢复) {
        super.onCreate($恢复);
        应用.初始化界面(this);
        for (应用插件 $单个 : ((基本应用)环境.取应用()).所有插件) {
            $单个.界面新建(this);
        }
        Intent $意图 = getIntent();
        if ($意图.hasExtra("参数")) {
            传入参数 = (Object[])$意图.getSerializableExtra("参数");
        }
        for (界面插件 $单个 : 所有插件) {
            $单个.界面创建事件($恢复);
        }
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        置滑动返回(false);
        try {
            界面创建事件($恢复);
        } catch (Exception $错误) {
            应用.跳转错误(线程.取当前线程(), $错误);
            结束界面();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    private SwipeBackActivityHelper mHelper;


    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    public void 置滑动返回(boolean $是否) {
        setSwipeBackEnable($是否);
    }

    public String 读字符串(String $内容) {
        return getIntent().getStringExtra($内容);
    }

    @Override
    public void onStart() {
        super.onStart();
        for (界面插件 $单个 : 所有插件) {
            $单个.界面启动事件();
        }
        界面启动事件();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            for (界面插件 $单个 : 所有插件) {
                $单个.取得焦点事件();
            }
            取得焦点事件();
        } else {
            for (界面插件 $单个 : 所有插件) {
                $单个.失去焦点事件();
            }
            失去焦点事件();
        }
    }

    @Override
    public void onNewIntent(Intent $意图) {
        super.onNewIntent($意图);
        for (界面插件 $单个 : 所有插件) {
            $单个.收到意图事件($意图);
        }
        收到意图事件($意图);
    }

    @Override
    protected void onSaveInstanceState(Bundle $输出) {
        super.onSaveInstanceState($输出);
        for (界面插件 $单个 : 所有插件) {
            $单个.保存状态事件($输出);
        }
        保存状态事件($输出);
    }

    @Override
    public void onResume() {
        super.onResume();
        for (界面插件 $单个 : 所有插件) {
            $单个.界面刷新事件();
        }
        界面刷新事件();
    }

    @Override
    public void onPause() {
        super.onPause();
        for (界面插件 $单个 : 所有插件) {
            $单个.界面遮挡事件();
        }
        界面遮挡事件();
    }

    private long 返回时间 = 时间.时间戳() - 23333;

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event) {
        for (界面插件 $单个 : 所有插件) {
            Boolean $返回 = $单个.按键按下事件(keyCode, event);
            if ($返回 != null) {
                return $返回;
            }
        }
        Boolean $返回 = 按键按下事件(keyCode, event);
        if ($返回 != null) {
            return $返回;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (界面插件 $单个 : 所有插件) {
                $返回 = $单个.返回按下事件();
                if ($返回 != null) {
                    return $返回;
                }
            }
            $返回 = 返回按下事件();
            if ($返回 != null) {
                return $返回;
            }
            long 上次 = 返回时间;
            if ((返回时间 = 时间.时间戳()) - 上次 < 2333) {
                finish();
                return true;
            } else {
                提示.普通("再按一次返回键退出 ~");
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onActivityResult(int $请求码,int $返回码,Intent $意图) {
        super.onActivityResult($请求码, $请求码, $意图);
        for (界面插件 $单个 : 所有插件) {
            $单个.界面回调事件($请求码, $返回码, $意图);
        }
        界面回调事件($请求码, $返回码, $意图);
    }

    @Override
    public void onStop() {
        for (界面插件 $单个 : 所有插件) {
            $单个.离开界面事件();
        }
        离开界面事件();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (界面插件 $单个 : 所有插件) {
            $单个.权限回调事件();
        }
        权限回调事件();
    }

    @Override
    public void onDestroy() {
        for (界面插件 $单个 : 所有插件) {
            $单个.界面销毁事件();
        }
        for (应用插件 $单个 : ((基本应用)环境.取应用()).所有插件) {
            $单个.界面结束(this);
        }
        界面销毁事件();
        super.onDestroy();
    }

    public void 置返回值(int $请求码) {
        setResult($请求码);
    }

    public void 置返回值(int $结果码,Intent $意图) {
        setResult($结果码, $意图);
    }

    public void 结束界面() {
        finish();
    }

    public void 结束界面(final int $延时) {
        new 线程(new 方法() {
                @Override
                public Object 调用(Object[] $参数) {
                    线程.暂停($延时);
                    finish();
                    return null;
                }
            }).启动();
    }

    public View 当前视图;

    public void 打开布局(View $视图) {
        if ($视图 == null) return;
        for (界面插件 $单个 : 所有插件) {
            $单个.打开布局事件($视图);
        }
        当前视图 = $视图;
        间.安卓.工具.布局.打开(this, $视图);
    }

    public void 打开布局(String $地址) {
        打开布局(布局.读取(this, $地址));
    }

    public void 打开布局(哈希表 $内容) {
        打开布局(布局.解析(this, $内容));
    }

    public void 解析布局(String $内容) {
        打开布局(布局.解析(this, $内容));
    }

    public View 取视图(Object $标签) {
        return 当前视图.findViewWithTag($标签);
    }

    public void 跳转界面(Class<?> $类) {
        跳转界面(null, $类, null);
    }

    public void 跳转界面(Class<?> $类,Object... $数据) {
        跳转界面(null, $类 , $数据);
    }

    public void 跳转界面(Integer $请求码,Class<?> $类,Object... $数据) {

        if (反射.是子类(界面.class, $类)) {
            Intent $意图 = new Intent(this, ProxyActivity.class);
            $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            $意图.putExtra("类", (Serializable)$类);
            if ($数据 != null) 
                $意图.putExtra("参数", (Serializable)$数据);
            if ($请求码 == null)
                startActivity($意图);
            else
                startActivityForResult($意图, $请求码);
            return;
        }
        Intent $意图 = new Intent(this, $类);
        $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if ($数据 != null)
            $意图.putExtra("参数", (Serializable)$数据);
        if ($请求码 == null)
            startActivity($意图);
        else
            startActivityForResult($意图, $请求码);

    }

    public void 跳转脚本(String $类) {
        跳转脚本(null, $类, null);
    }

    public void 跳转脚本(String $类,Object[] $数据) {
        跳转脚本(null, $类 , $数据);
    }

    public void 跳转脚本(Integer $请求码,String $类,Object[] $数据) {
        new 线程(this, "直接跳转脚本").启动($请求码, $类, $数据);
    }

    public void 直接跳转脚本(Integer $请求码,String $类,Object[] $数据) {
        $请求码 = $请求码 == null ? -1 : $请求码;
        Class<?> $界面 = 反射.取类("hl4a.runtime.ScriptActivity");
        if ($界面 != null) {
            Intent $意图 = new Intent(this, $界面);
            $意图.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            $意图.putExtra("脚本", $类);
            if ($数据 != null)
                $意图.putExtra("参数", (Serializable)$数据);
            startActivityForResult($意图, $请求码);
        } else {
            错误.内容("没有脚本运行时 ~");
        }
    }

    public void 请求权限() {
        if (设备.取SDK() < 23) {
            权限回调事件();
            return;
        }
        for (界面插件 $单个 : 所有插件) {
            $单个.请求权限事件();
        }
        权限.默认请求(this);
    }


    public void 界面创建事件(Bundle $恢复) {}
    public void 界面启动事件() {}
    public void 界面刷新事件() {}
    public void 界面遮挡事件() {}
    public void 界面回调事件(int $请求码,int $返回码,Intent $意图) {}
    public void 离开界面事件() {}
    public void 界面销毁事件() {}
    public void 取得焦点事件() {}
    public void 失去焦点事件() {}
    public Boolean 按键按下事件(int $按键码,KeyEvent $事件) {return null;}
    public Boolean 返回按下事件() {return null;}
    public void 收到意图事件(Intent $意图) {}
    public void 保存状态事件(Bundle $输出) {}

    public void 权限回调事件() {}

}
