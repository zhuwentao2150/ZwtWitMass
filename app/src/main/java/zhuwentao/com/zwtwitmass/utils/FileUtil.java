package zhuwentao.com.zwtwitmass.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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

    public static void saveStrToFile(String rootPath, String fileName, String content) throws Exception {
        File file = new File(rootPath, fileName);
        if (!file.exists()) {
            File dir = new File(file.getParent());
            dir.mkdirs();
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bufw = new BufferedWriter(fileWriter);
        bufw.write(content);
        bufw.newLine();
        bufw.flush();

        fileWriter.close();
        bufw.close();
    }

    public static String readFileToStr(String rootPath, String fileName, int index) throws Exception {
        File file = new File(rootPath, fileName);
        if (!file.exists()) {
            LogUtil.e("file is not exists");
            return null;
        }

        FileReader fileReader = new FileReader(file.getAbsoluteFile());
        BufferedReader bufr = new BufferedReader(fileReader);

        String line = null;
        int poi = 0;
        while((line = bufr.readLine()) != null){
            if (poi == index) {
                break;
            }
            poi++;
        }
        fileReader.close();
        bufr.close();
        return line;
    }

}
