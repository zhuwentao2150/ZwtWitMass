package zhuwentao.com.zwtwitmass.utils;

import android.util.Log;

import java.io.File;

/**
 * 文件操作类
 * Created by zhuwentao on 2017-07-07.
 */
public class FileUtil {

    private static String TAG = "FileUtil";


    /**
     * 删除文件
     * @param path
     * @return
     */
    public static boolean delFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return true;
            } else {
                Log.e("FileUtil", "file is directory!");
            }
        } else {
            Log.e("FileUtil", "file not find!");
        }
        return false;
    }

}
