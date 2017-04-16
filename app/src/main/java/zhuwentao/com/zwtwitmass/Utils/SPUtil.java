package zhuwentao.com.zwtwitmass.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存应用常用参数
 * Created by zhuwentao on 2017-04-16.
 */
public class SPUtil {

    // 用户名
    public static final String ACCOUNT_NAME = "accountName";

    /**
     * 保存用户名
     * @param context
     * @param value
     */
    public static void saveAccountName(Context context, String value) {
        SharedPreferences sharedPre = context.getSharedPreferences(ACCOUNT_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString(ACCOUNT_NAME, value);
        editor.commit();
    }

    /**
     * 获取用户名称
     * @param context
     * @return
     */
    public static String getAccountName(Context context) {
        // 获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences(ACCOUNT_NAME, Context.MODE_PRIVATE);
        return sharedPre.getString(ACCOUNT_NAME, null);
    }

    /**
     * 删除用户名称
     * @param context
     * @return
     */
    public static void removeAccountName(Context context) {
        // 获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences(ACCOUNT_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.remove(ACCOUNT_NAME);
        editor.commit();
    }
}
