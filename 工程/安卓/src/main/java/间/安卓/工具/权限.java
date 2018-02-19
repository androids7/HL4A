package 间.安卓.工具;

import android.app.*;
import android.content.pm.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.content.*;
import 间.工具.*;
import 间.安卓.组件.基本界面;

public class 权限 {

    public static void 默认请求(Activity $界面) {
        
        //String $哈希 = 应用工具.取安装包位置();
        //$哈希 = 散列工具.文件("MD5",$哈希);
        String $上次 = (String)设置.读取("上次请求");
        
        //if ($上次 == null || !$哈希.equals($上次)) {
            if ($上次 == null) {
            设置.保存("上次请求","233");
            请求所有($界面);
            } else {
                $界面.onRequestPermissionsResult(基本界面.请求码_权限请求,new String[0],new int[0]);
            }
        //}
        
    }
    
    public static boolean 检查所有(Activity $界面) {
        String[] $所有 = 应用.取所有权限();
        for (String $单个 : $所有) {
            if (检查权限($界面, $单个) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
       }
        return true;
    }

    public static int 检查权限(Activity $界面,String $权限) {
        if (设备.取SDK() >= Build.VERSION_CODES.M) {
            if (设备.取目标SDK() >= Build.VERSION_CODES.M) {
                return ContextCompat.checkSelfPermission($界面, $权限);
            } else {
                return PermissionChecker.checkSelfPermission($界面, $权限);
            }
        }
        return PackageManager.PERMISSION_GRANTED;
    }

    public static void 请求所有(Activity $界面) {
        if (设备.取SDK() >= 23) {
            String[] $所有 = 应用.取所有权限();
            请求权限($界面, $所有);
        } else {
            $界面.onRequestPermissionsResult(基本界面.请求码_权限请求,new String[0],new int[0]);
        }
    }

    public static void 请求权限(Activity $界面,String[] $权限) {
        ActivityCompat.requestPermissions($界面, $权限, 基本界面.请求码_权限请求);
    }

}
