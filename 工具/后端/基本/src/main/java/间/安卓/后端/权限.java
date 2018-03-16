package 间.安卓.后端;

import com.avos.avoscloud.AVACL;

public class 权限 extends AVACL {

    public 权限() {
        所有人可读(true);
    }
    
    public 权限(用户 $用户) {
        this();
        用户可写($用户,true);
    }

    public void 所有人可读(boolean $是否) {
        setPublicReadAccess($是否);
    }

    public void 所有人可写(boolean $是否) {
        setPublicWriteAccess($是否);
    }

    public void 用户可读(用户 $用户,boolean $是否) {
        setReadAccess($用户, $是否);
    }

    public void 用户可读(String $用户,boolean $是否) {
        setReadAccess($用户, $是否);
    }

    public void 用户可写(String $用户,boolean $是否) {
        setWriteAccess($用户, $是否);
    }
    
    public void 用户可写(用户 $用户,boolean $是否) {
        setWriteAccess($用户, $是否);
    }

    public void 权限组可读(权限组 $权限组,boolean $是否) {
        setRoleReadAccess($权限组.权限组, $是否);
    }

    public void 权限组可读(String $权限组,boolean $是否) {
        setRoleReadAccess($权限组, $是否);
    }

    public void 权限组可写(权限组 $权限组,boolean $是否) {
        setRoleWriteAccess($权限组.权限组, $是否);
    }

    public void 权限组可写(String $权限组,boolean $是否) {
        setRoleWriteAccess($权限组, $是否);
    }


}
