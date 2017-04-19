package zhuwentao.com.zwtwitmass.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回一些测试用的数据
 * Created by zhuwentao on 2016-08-25.
 */
public class DataUtil {

    /**
     * 返回List集合测试数据
     * @param number
     * @return
     */
    public static List<String> getListStringData(int number) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String str = "list测试数据" + i;
            datas.add(str);
        }
        return datas;
    }

    /**
     * 返回String数组测试数据
     * @param number
     * @return
     */
    public static String[] getArrayStringData(int number) {
        String[] datas = new String[number];
        for (int i = 0; i < number; i++) {
            datas[i] = "Array测试数据" + i;
        }
        return datas;
    }

    /**
     * 获取手机中所有App
     *
     * @return
     */
    public static List<ResolveInfo> getAppData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        return packageManager.queryIntentActivities(mainIntent, 0);
    }


}
