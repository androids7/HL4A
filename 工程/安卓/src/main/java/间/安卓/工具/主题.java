package 间.安卓.工具;

public class 主题 {

    private static 颜色 主题颜色;
    private static int 圆角大小;
    private static int 大文本大小;
    private static int 文本大小;
    private static int 小文本大小;
    private static int 默认填充;
    private static int 中等填充;
    private static int 默认阴影;
    private static int 默认圆角;
    
    public static void 置默认圆角(Object $阴影) {
        默认圆角 = 视图.检查大小($阴影);
    }

    public static int 取默认圆角() {
        return 默认圆角;
    }
    
    public static void 置默认阴影(Object $阴影) {
        默认阴影 = 视图.检查大小($阴影);
    }

    public static int 取默认阴影() {
        return 默认阴影;
    }
    
    public static void 置中等填充(Object $填充) {
        中等填充 = 视图.检查大小($填充);
    }

    public static int 取中等填充() {
        return 中等填充;
    }

    public static void 置小文本大小(Object $大小) {
        小文本大小 = 视图.检查大小($大小);
    }

    public static int 取小文本大小() {
        return 小文本大小;
    }

    public static void 置大文本大小(Object $大小) {
        主题.大文本大小 = 视图.检查大小($大小);
    }

    public static int 取大文本大小() {
        return 大文本大小;
    }

    public static void 置默认填充(Object $填充) {
        默认填充 = 视图.检查大小($填充);
    }

    public static int 取默认填充() {
        return 默认填充;
    }

    public static void 置文本大小(Object $大小) {
        文本大小 = 视图.检查大小($大小);
    }

    public static int 取文本大小() {
        return 文本大小;
    }

    public static void 置圆角大小(Object $大小) {
        圆角大小 = 视图.检查大小($大小);
    }

    public static int 取圆角大小() {
        return 圆角大小;
    }

    public static void 置主题颜色(颜色 $颜色) {
        主题颜色 = $颜色;
    }

    public static 颜色 取主题颜色() {
        return 主题颜色;
    }

}
