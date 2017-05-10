package zhuwentao.com.zwtwitmass.network;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import zhuwentao.com.zwtwitmass.network.callback.HttpDownLoadCallBack;
import zhuwentao.com.zwtwitmass.network.callback.ProgressListener;
import zhuwentao.com.zwtwitmass.utils.LogUtil;
import zhuwentao.com.zwtwitmass.utils.ToastUtil;

/**
 * 下载服务
 * 目前是用广播来收发信息
 * 广播和业务代码耦合严重，待改进
 *
 * @author zwt Create by 2017-5-2
 */
public class DownLoadService extends Service {

    public static String DOWNLOAD_START = "Start_Download";
    public static String DOWNLOAD_STOP = "Stop_Download";
    public static String DOWNLOAD_URL = "Url_Download";

    /**
     * 存储下载任务集合
     */
    private Map<String, HttpClient> mDownloadTasks = new LinkedHashMap<>();

    private Context mContext;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // 启动下载请求
        if (intent != null) {
            String url = intent.getStringExtra(DOWNLOAD_URL);
            if (intent.getAction().equals(DOWNLOAD_START)) {
                startDownLoad(url);
            }

            // 暂停请求/取消下载，需要记录当前下载到的位置
            if (intent.getAction().equals(DOWNLOAD_STOP)) {
                stopDownLoad(url);
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 启动下载
     */
    private void startDownLoad(final String url) {

        if (mDownloadTasks.get(url) != null) {
            LogUtil.e("该下载任务已经存在");
            return;
        }

        if (mDownloadTasks.size() >= 2) {
            ToastUtil.showToast(mContext, "最多可同时下载2个");
            return;
        }

        HttpClient build = new HttpClient.Builder().setUrl(url + "/")
                .setProgressListener(new ProgressListener() {

                    @Override
                    public void onProgress(final long progress,
                                           final long total, boolean done) {
                        try {
                            // 防止频繁发送/接收广播造成的界面卡顿
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        int prs = (int) ((double) progress / (double) total * 100);

                        // 发送广播
                        // TODO: 目前是以发送广播的形式回传消息给UI，需要修改成以回调方式的形式
                        Intent intent = new Intent();
                        mContext.sendBroadcast(intent);
                    }
                }).create();

        build.requestDownLoad(url);

        // 添加到下载集合
        mDownloadTasks.put(url, build);

        build.setHttpDownLoadListener(new HttpDownLoadCallBack() {

            @Override
            public void onReturnData(ResponseBody body) {
                mDownloadTasks.remove(url);
                // 发送下载完毕广播
                // TODO: 需要修改成回调方式
                // sendBroadcastToUI("aa", url);
            }

            @Override
            public void onFailure(String message) {
                mDownloadTasks.remove(url);
                LogUtil.e("下载失败->>>" + message);
                // TODO: 需要修改成回调方式
                // sendBroadcastToUI(ActivityDownload.DOWNLOAD_TYPE_FAILURE, url);
            }
        });
    }

    /**
     * 关闭下载
     */
    public void stopDownLoad(String url) {
        HttpClient httpClient = mDownloadTasks.get(url);
        httpClient.cancelRequest();
        mDownloadTasks.remove(url);
    }
//
//    /**
//     * 发送下载状态
//     *
//     * @param type
//     *            状态类型
//     * @param url
//     *            下载唯一标识
//     */
//    private void sendBroadcastToUI(String type, String url) {
//        Intent intent = new Intent();
//        intent.setAction(ActivityDownload.RECEIVER_DOWNLOAD);
//        intent.putExtra(ActivityDownload.DOWNLOAD_TYPE, type);
//        intent.putExtra(ActivityDownload.DOWNLOAD_URL, url);
//        mContext.sendBroadcast(intent);
//    }

}