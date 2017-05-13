package zhuwentao.com.zwtwitmass.network.callback;

import okhttp3.ResponseBody;

/**
 * 下载文件监听回调
 */
public interface HttpDownLoadCallBack {

	/**
	 * 下载成功
	 * @param body
     */
	void onReturnData(ResponseBody body);

	/**
	 * 下载失败
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
