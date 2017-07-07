package zhuwentao.com.zwtwitmass.utils;

import java.io.File;

/**
 * 文件操作类
 * Created by zhuwentao on 2017-07-07.
 */
public class FileUtil {

    public static boolean isFile(String path){

        File file = new File(path);
        if (file.exists()) {
            return true;
        }

        return false;
    }

}
