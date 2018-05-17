package zhuwentao.com.zwtwitmass.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

/**
 * 应用Application
 * Created by zhuwentao on 2017-04-18.
 * TODO:分支测试
 * TODO:二次测试分支
 */
public class AppApplication extends Application {
    private static Context mContext;

    /**
     * 开发模式，发布前需要将值修改为 false
     */
    private static final Boolean DEVELOPER_MODE = false;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        if (DEVELOPER_MODE) {

            /*
             * 校验线程使用的合理性，不合理的使用，会打印日志并触发应用崩溃
             * 不合理使用的场景有：在主线程中进行网络操作、在主线程中进行文件写操作等
             */
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .permitDiskReads()
                    //.detectDiskReads()
                    //.detectDiskWrites()
                    //.detectNetwork()
                    ;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                threadPolicyBuilder.detectCustomSlowCalls();
            }

            threadPolicyBuilder.penaltyLog().penaltyDeath();

            StrictMode.setThreadPolicy(threadPolicyBuilder.build());

            /*
             * 校验资源的回收，资源存在泄漏，会打印日志并触发应用崩溃
             * 资源存在泄漏的场景有：游标未关闭、SQLiteDatabase对象未关闭、流未关闭、Activity内存泄漏等
             */
            StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder()
                    //.detectAll()
                    .detectLeakedSqlLiteObjects();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                vmPolicyBuilder.detectLeakedClosableObjects();
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                vmPolicyBuilder.detectLeakedRegistrationObjects();
            }

            vmPolicyBuilder.penaltyLog().penaltyDeath();

            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }

    }

    public static Context getContext(){
        return mContext;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
