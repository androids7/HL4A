package 间.安卓.编译;

import com.android.dex.Dex;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.command.dexer.Main;
import com.android.dx.dex.DexOptions;
import com.android.dx.dex.cf.CfOptions;
import com.android.dx.dex.cf.CfTranslator;
import com.android.dx.merge.DexMerger;
import java.io.IOException;
import 间.安卓.工具.文件;
import 间.工具.字节;

public class DEX {

    private DEX() {}

    public static void 多个(String $输出,String... $路径) throws IOException {
        Main.Arguments $参数 = new Main.Arguments();
        $参数.strictNameCheck = false;
        $参数.fileNames = 文件.检查地址($路径);
        $参数.outName = 文件.检查地址($输出);
        Main.run($参数);

    }

    public static void 单个(String $文件,String $类名,String $输出) throws IOException {
        byte[] $字节 = 字节.读取($文件);
        DexOptions dexOptions = new DexOptions();
        com.android.dx.dex.file.DexFile dexFile = new com.android.dx.dex.file.DexFile(dexOptions);
        DirectClassFile classFile = new DirectClassFile($字节, $类名.replace('.', '/') + ".class", true);
        classFile.setAttributeFactory(StdAttributeFactory.THE_ONE);
        classFile.getMagic();
        dexFile.add(CfTranslator.translate(classFile, null, new CfOptions(), dexOptions, dexFile));
        Dex dex = new Dex(dexFile.toDex(null, false));
        dex.writeTo(文件.取文件对象($输出));
    }

    public static void 合成(String... $所有) throws IOException {
        DexMerger.main(文件.检查地址($所有));
    }
}
