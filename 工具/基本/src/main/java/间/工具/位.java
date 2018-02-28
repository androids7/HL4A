package 间.工具;

public class 位 {

    public static int 位(int $参数1,int $参数2) {
        return $参数1 & $参数2;
    }

    public static int 或(int $参数1,int $参数2) {
        return $参数1 | $参数2;
    }


    public static int 异或(int $参数1,int $参数2) {
        return $参数1 ^ $参数2;
    }

    public static int 非(int $参数) {
        return $参数 ^ 0xFFFFFFFF;
    }


    public static int 左移(int $参数1,int $参数2) {
        return $参数1 << $参数2;
    }


    public static int 右移(int $参数1,int $参数2) {
        return $参数1 >> $参数2;
    }


    public static int 无符号右移(int $参数1,int $参数2) {
        return $参数1 >>> $参数2;
    }
}
