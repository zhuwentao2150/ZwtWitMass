package zhuwentao.com.zwtwitmass.network.callback;

/**
 * 进度返回
 * @author zwt Create by 2017-4-26
 */
public interface ProgressListener {

	/**
	 * 回传进度
	 * @param progress 当前进度
	 * @param total 数据总量
	 * @param done true=下载完毕
	 */
	void onProgress(long progress, long total, boolean done);
}
