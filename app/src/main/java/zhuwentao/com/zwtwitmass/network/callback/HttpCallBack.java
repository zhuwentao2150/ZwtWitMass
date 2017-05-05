package zhuwentao.com.zwtwitmass.network.callback;

/**
 * 网络请求返回的结果
 * @author zwt Create by 2017-4-20
 */
public interface HttpCallBack {

	/**
	 * 请求成功
	 * @param result 返回值
	 */
	void onSuccess(String result);

	/**
	 * 请求失败
	 * @param message 失败消息
	 */
	void onFailure(String message);
}
