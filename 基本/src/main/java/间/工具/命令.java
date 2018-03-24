package 间.工具;

import java.io.*;

public class 命令 {

    private boolean 成功 = true;
    private Process 进程 = null;

    public 命令() {
        this("");
    }
    
    public 命令(String $命令) {
        try {
            $命令 = $命令 == null ? "" : $命令;
            进程 = Runtime.getRuntime().exec($命令);
        } catch (Exception $错误) {
            成功 = false;
        }
    }
    
    public 命令(String $命令,File $当前) {
        this($命令,$当前,null);
    }
    
    public 命令(String $命令, File $当前, String... $参数) {
        try {
            if ($命令 == null)
                $命令 = "";
            进程 = Runtime.getRuntime().exec($命令, $参数, $当前);
        } catch (Exception $错误) {
            成功 = false;
        }
    }

    public void 等待() {
        try {
            进程.waitFor();
        } catch (Exception $错误) {
            成功 = false;
        }
    }

    public boolean 状态() {
        return 成功;
    }
    
    public String 执行(String $命令) {
        
        OutputStream $输出 = 进程.getOutputStream();
      
        字符.保存($输出,$命令);
        
        等待();
        
        return 读取();
        
    }

    public String 读取() {

        InputStream $输入流 = 进程.getInputStream();

        String $文本 = 字符.读取($输入流);

        流.关闭($输入流);

        return $文本;

    }

}
