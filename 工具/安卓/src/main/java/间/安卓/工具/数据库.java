package 间.安卓.工具;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.IOException;

public class 数据库 {

    public SQLiteDatabase 对象;

    public 数据库(SQLiteDatabase $对象) {
        对象 = $对象;
    }

    public static 数据库 打开(String $名称) {
        SQLiteDatabase $对象 = 环境.取应用().openOrCreateDatabase($名称, 0, null);
        if ($对象 == null)return null;
        return new 数据库($对象);
    }

    public void 执行(String $命令) {
        对象.execSQL($命令);
    }

    public String[] 查询(String $命令) {
        Cursor $光标  = 对象.rawQuery($命令, null);
        int $数量 = $光标.getColumnCount();
        String[] $返回 = new String[$数量];
        for (int $键值 = 0;$键值 < $数量;) {
            $返回[$键值] = $光标.getString($键值);
        }
        return $返回;
    }
    
    public int[] 查询数字(String $命令) {
        Cursor $光标  = 对象.rawQuery($命令, null);
        int $数量 = $光标.getColumnCount();
        int[] $返回 = new int[$数量];
        for (int $键值 = 0;$键值 < $数量;) {
            $返回[$键值] = $光标.getInt($键值);
        }
        return $返回;
    }

    public void 关闭() {
        对象.close();
    }


    public void 建表(String $名称,String $内容) {
        执行("CREATE TABLE IF NOT EXISTS " + $名称 + " (" + $内容 + ")");
    }


    public void 删表(String $名称) {
        执行("DROP TABLE " + $名称);
    }

    public void 插入记录(String $表名,String $参数) {
        执行("INSERT INTO " + $表名 + " VALUES (" + $参数 + ")");
    }

    public void 删除记录(String $表名,String $条件) {
        执行("DELETE FROM " + $表名 + " WHERE " + $条件);
    }

    public void 更新记录(String $表名,String $设置,String $条件) {
        执行("UPDATE " + $表名 + " SET " + $设置 + " WHERE " + $条件);
    }

    public String[] 查询记录(String $表名,String $条件) {
        return 查询("SELECT * FROM " + $表名 + " WHERE " + $条件);
    }

    public String[] 查询记录(String $表名,String $条件,int $长度) {
        return 查询("SELECT * FROM " + $表名 + " WHERE " + $条件 + " LIMIT " + $长度);
    }

    public String[] 查询记录(String $表名,String $条件,int $开始,int $结束) {
        return 查询("SELECT * FROM " + $表名 + " WHERE " + $条件 + " LIMIT " + $开始 + "," + $结束);
    }

    public Integer 取记录数(String $表名) {
        return 查询数字("select count(*) from " + $表名)[0];
    }

}


