package zhuwentao.com.zwtwitmass.network.download;

import okhttp3.ResponseBody;

/**
 * 下载包邮
 * Created by zhuwentao on 2017-05-25.
 */
public interface DownloadCallBack {

    /**
     * 开始下载
     */
    void onStart();

    /**
     * 请求成功
     * @param result 返回值
     */
    void onSuccess(ResponseBody result);

    /**
     * 请求失败
     * @param message 失败消息
     */
    void onFailure(String message);

    /**
     * 正在下载
     * @param progress 当前下载进度
     * @param total 下载总量
     * @param done 是否下载完毕
     */
    void onProgress(long progress, long total, boolean done);

}
