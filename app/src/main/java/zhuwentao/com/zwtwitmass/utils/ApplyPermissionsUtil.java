package zhuwentao.com.zwtwitmass.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

/**
 * 权限检查和申请
 * Created by zhuwentao on 2017-04-28.
 */
public class ApplyPermissionsUtil {

    /**
     * 检查是否有该权限
     * @param context 上下文
     * @param permission 要申请的权限
     * @return true=有权限 false=无权限
     */
    public static boolean checkHavePermissions(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getTargetSdkVersion(context) >= 23) {
                LogUtil.e("执行ContextCompat检查");
                return ContextCompat.checkSelfPermission(context, permission)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                LogUtil.e("执行PermissionChecker检查");
                return PermissionChecker.checkSelfPermission(context, permission)
                        == PackageManager.PERMISSION_GRANTED;
            }
        }
        LogUtil.e("SDK_INT小于23");
        return true;
    }

    /**
     * 权限校验
     * @param context 上下文
     * @param permission 权限
     * @param requestCode 申请标识
     */
    public static void checkRequestPermissions(Context context, String permission, int requestCode) {
        if (permission == null) {
            return;
        }
        boolean result = checkHavePermissions(context, permission);
        if (!result) {
            LogUtil.e("无权限" + permission);
            // 弹出对话框申请权限
            // 最后一个参数标识该申请权限，可以在Activity界面通过onRequestPermissionsResult回调方法来监听权限申请的结果
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
        } else {
            LogUtil.e("有权限" + permission);
        }
    }

    /**
     * 获取应用targetSdkVersion
     * @param context 上下文
     * @return  返回应用targetSdkVersion
     */
    private static int getTargetSdkVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
