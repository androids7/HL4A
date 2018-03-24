package 间.工具;

import java.io.*;
import 间.收集.*;

public class 序列化 {

    public static void 保存(String $地址, Object $对象) {
        try {
            OutputStream $文件流 = 流.输出.文件($地址);
            ObjectOutputStream $输出流 = new ObjectOutputStream($文件流);
            $输出流.writeObject($对象);
        } catch (IOException $错误) {
        }
    }

    public static Object 读取(String $地址) {
        try {
            InputStream $文件流 = 流.输入.文件($地址);
            ObjectInputStream $输入流 = new ObjectInputStream($文件流);
            return $输入流.readObject();
        } catch (Exception $错误) {
        }
        return null;
    }

}
