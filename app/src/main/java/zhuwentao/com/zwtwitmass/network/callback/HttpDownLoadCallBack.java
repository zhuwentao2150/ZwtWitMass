package zhuwentao.com.zwtwitmass.network.callback;

import okhttp3.ResponseBody;

public interface HttpDownLoadCallBack {
	
	
	void onReturnData(ResponseBody body);

	/**
	 * 下载失败
	 * @param message 失败消息
	 */
	void onFailure(String message);


	void onProgress(long progress, long total, boolean done);

}
