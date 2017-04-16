package zhuwentao.com.zwtwitmass.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast工具
 * Created by zhuwentao on 2016-08-18.
 */
public class ToastUtil {

    private static Toast toast;

    private ToastUtil() {
        // 防止实例化
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param content 要显示的内容
     */
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param resId   文字资源id
     */
    public static void showToast(Context context, int resId) {
        showToast(context, (String) context.getResources().getText(resId));
        toast.show();
    }

    /**
     * 显示Toas在中间
     * @param context
     * @param content
     */
    public static void showToastToCenter(Context context, String content) {
        showToast(context, content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
