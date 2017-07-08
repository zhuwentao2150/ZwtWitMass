package zhuwentao.com.zwtwitmass.utils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件操作类
 * Created by zhuwentao on 2017-07-07.
 */
public class FileUtil {

    private static String TAG = "FileUtil";


    /**
     * 删除文件
     *
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

    public static boolean saveStrToFile(String rootPath, String fileName, String content) throws Exception {
        File file = new File(rootPath, fileName);
        if (file.exists()) {
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();

        } else {
            File dir = new File(file.getParent());
            dir.mkdirs();
            file.createNewFile();
            Log.e("FileUtil", "file is exists");
        }


        return false;
    }

}
