package zhuwentao.com.zwtwitmass.network;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 下载服务
 *
 * @author zwt Create by 2017-5-2
 */
public class DownLoadService extends Service {

    public static String DOWNLOAD_START = "Start_Download";
    public static String DOWNLOAD_STOP = "Stop_Download";
    public static String DOWNLOAD_SOFTLIST = "SoftList_data";

    /**
     * 存储下载任务集合
     */
    private Map<String, HttpClient> mDownloadTasks = new LinkedHashMap<>();

    private Context mContext;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mContext = this;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        // 启动下载请求
//        if (intent != null) {
//            if (intent.getAction().equals(DOWNLOAD_START)) {
//
//            }
//
//            // 暂停请求/取消下载，需要记录当前下载到的位置
//            if (intent.getAction().equals(DOWNLOAD_STOP)) {
//
//            }
//
//        }
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    /**
//     * 启动下载
//     *
//     * @param soft
//     */
//    private void startDownLoad(final SoftList soft) {
//        // URL地址
//        final String softURL = soft.downloadURL;
//
//        if (mDownloadTasks.get(softURL) != null) {
//            LogUtils.e("该下载任务已经存在");
//            return;
//        }
//
//        if (mDownloadTasks.size() >= 2) {
//            ToastUtil.showToast(mContext, "最多可同时下载2个");
//            return;
//        }
//
//        HttpClient build = new HttpClient.Builder().setUrl(softURL + "/")
//                .setProgressListener(new ProgressListener() {
//
//                    @Override
//                    public void onProgress(final long progress,
//                                           final long total, boolean done) {
//
//                        try {
//                            // 防止频繁发送/接收广播造成的界面卡顿
//                            Thread.sleep(300);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        int prs = (int) ((double) progress / (double) total * 100);
//
//                        Intent intent = new Intent();
//                        intent.setAction(ActivityDownload.RECEIVER_DOWNLOAD);
//                        intent.putExtra(ActivityDownload.DOWNLOAD_TYPE,
//                                ActivityDownload.DOWNLOAD_TYPE_ING);
//                        intent.putExtra(ActivityDownload.DOWNLOAD_URL, softURL);
//                        intent.putExtra(ActivityDownload.DOWNLOAD_PROGRESS, prs);
//                        mContext.sendBroadcast(intent);
//                    }
//                }).create();
//
//        build.requestDownLoad(softURL);
//
//        // 添加到下载集合
//        mDownloadTasks.put(softURL, build);
//
//        build.setHttpDownLoadListener(new HttpDownLoadCallBack() {
//
//            @Override
//            public void onReturnData(ResponseBody body) {
//                mDownloadTasks.remove(softURL);
//                // 解压文件
//                AppFileUtil.saveDownLoadFile(body.byteStream(),
//                        soft.SoftwareCode + "_" + soft.LanguageCode + ".7z",
//                        soft);
//
//                // 发送下载完毕广播
//                sendBroadcastToUI(ActivityDownload.DOWNLOAD_TYPE_END, softURL);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                mDownloadTasks.remove(softURL);
//                LogUtils.e("下载失败->>>" + message);
//                sendBroadcastToUI(ActivityDownload.DOWNLOAD_TYPE_FAILURE,
//                        softURL);
//            }
//        });
//    }
//
//    /**
//     * 关闭下载
//     *
//     * @param soft
//     */
//    public void stopDownLoad(final SoftList soft) {
//        HttpClient httpClient = mDownloadTasks.get(soft.downloadURL);
//        httpClient.cancelRequest();
//        mDownloadTasks.remove(soft.downloadURL);
//    }
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
