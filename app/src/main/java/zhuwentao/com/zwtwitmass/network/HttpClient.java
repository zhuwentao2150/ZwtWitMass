package zhuwentao.com.zwtwitmass.network;

import android.text.TextUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import zhuwentao.com.zwtwitmass.network.callback.HttpCallBack;
import zhuwentao.com.zwtwitmass.network.callback.HttpDownLoadCallBack;
import zhuwentao.com.zwtwitmass.network.callback.ProgressListener;
import zhuwentao.com.zwtwitmass.network.common.HttpLoggingInterceptor;
import zhuwentao.com.zwtwitmass.network.common.HttpService;
import zhuwentao.com.zwtwitmass.network.common.ProgressResponseBody;
import zhuwentao.com.zwtwitmass.network.entity.Entity;
import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 基于Retrofit2.2网络请求封装
 * 
 * @author zwt Create by 2017-4-20
 */
public class HttpClient {

	/**
	 * 设置请求超时的时间
	 */
	public final static int REQUEST_TIME_OUT = 30;

	/**
	 * 接口服务
	 */
	private HttpService mService;

	private Call<ResponseBody> call;
	
	private HttpCallBack onResultListener;
	private HttpDownLoadCallBack onDownLoadListener;

	private HttpClient(HttpService service) {
		this.mService = service;
	}

	/**
	 * 使用构造者模式，配置参数
	 * 
	 * @author zwt Create by 2017-4-20
	 */
	public static class Builder {
		String url;
		ProgressListener onProgressListener;

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}
		
		public Builder setProgressListener(ProgressListener onProgressListener) {
			this.onProgressListener = onProgressListener;
			return this;
		}
		
		public HttpClient create() {

			Retrofit.Builder builder = new Retrofit.Builder();

			if (!TextUtils.isEmpty(url)) {
				builder.baseUrl(url);
			} else {
				throw new IllegalArgumentException();
			}
			
			if(onProgressListener != null){
				// 下载回调进度
				OkHttpClient client = new OkHttpClient.Builder()
		        .addNetworkInterceptor(new Interceptor() {
		            @Override
		            public okhttp3.Response intercept(Chain chain) throws IOException {
		            	
		            	// 如果要添加断点下载
		            	// chain.request().newBuilder().addHeader("Range", "bytes="+postion+"-");
		            	
		                okhttp3.Response orginalResponse = chain.proceed(chain.request());
		                return orginalResponse.newBuilder().body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
		                    @Override
		                    public void onProgress(long progress, long total, boolean done) {
		                    	if(onProgressListener != null){
		                    		onProgressListener.onProgress(progress, total, done);
		                    	}
		                    }
		                })).build();
		            }
		        })
		        .connectTimeout(60, TimeUnit.SECONDS)
		        .build();
				
				builder.client(client);
			}else{
				// 添加Log打印信息
				HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
				logging.setLevel(HttpLoggingInterceptor.Level.BODY);
				// 需要打印请求log时使用
				OkHttpClient httpClient = new OkHttpClient.Builder()
						.connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
						.addInterceptor(logging).build();
				builder.client(httpClient);
			}

			Retrofit retrofit = builder.build();

			HttpService httpService = retrofit.create(HttpService.class);

			return new HttpClient(httpService);
		}

	}

	/**
	 * 发送Get请求
	 *
	 * @param data
	 *            数据
	 */
	public void requestGet(Entity data) {

		try {
			Map<String, String> map = objectToMap(data);

			call = mService.get(map);

			call.enqueue(new Callback<ResponseBody>() {

				@Override
				public void onFailure(Call<ResponseBody> call, Throwable t) {
					cancelRequest();
					if (onResultListener != null) {
						String message = t.getMessage();
						// 是否为取消请求
						if (call.isCanceled()) {
							LogUtil.i("请求被取消");
						} else {
							LogUtil.e("发送请求失败：" + t.getMessage());
							onResultListener.onFailure(message);
						}
					}
				}

				@Override
				public void onResponse(Call<ResponseBody> call,
						Response<ResponseBody> response) {
					cancelRequest();
					try {
						String message = new String(response.body().bytes());
						LogUtil.e("连接服务器成功：" + message);

						if (onResultListener != null) {
							if (response.isSuccessful()) {
								onResultListener.onSuccess(message);
							} else {
								onResultListener.onFailure(message);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送Post请求
	 *
	 * @param data
	 *            数据
	 */
	public void requestPost(Entity data) {
		
		try {
			Map<String, String> map = objectToMap(data);
			
			call = mService.post(map);
			
			call.enqueue(new Callback<ResponseBody>() {
				
				@Override
				public void onFailure(Call<ResponseBody> call, Throwable t) {
					cancelRequest();
					if (onResultListener != null) {
						String message = t.getMessage();

						// 是否为取消请求
						if (call.isCanceled()) {
							LogUtil.i("请求被取消");
						} else {
							LogUtil.e("发送请求失败：" + t.getMessage());
							onResultListener.onFailure(message);
						}
					}
					
					
				}
				
				@Override
				public void onResponse(Call<ResponseBody> call,
						Response<ResponseBody> response) {
					cancelRequest();
					
					try {
						String message = new String(response.body().bytes());
						LogUtil.e("连接服务器成功：" + message);
						
						if (onResultListener != null) {
							if (response.isSuccessful()) {
								onResultListener.onSuccess(message);
							} else {
								onResultListener.onFailure(message);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 下载文件
	 * @param url
	 */
	public void requestDownLoad(String url){
		call = mService.downloadFile(url);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				if (onDownLoadListener != null) {
					String message = t.getMessage();
					// 是否为取消请求
					if (call.isCanceled()) {
						LogUtil.i("下载请求被取消");
						onDownLoadListener.onFailure(t.getMessage());
					} else {
						LogUtil.e("下载发送请求失败：" + t.getMessage());
						message = "下载服务器异常";
						onDownLoadListener.onFailure(message);
					}
				}
			}

			@Override
			public void onResponse(Call<ResponseBody> call,
					Response<ResponseBody> response) {
				if (onDownLoadListener != null) {
					if (response.isSuccessful()) {
						onDownLoadListener.onReturnData(response.body());
					} else {
						onDownLoadListener.onFailure("请求成功，下载失败");
					}
				}
			}
			
		});
	}

	/**
	 * 关闭请求
	 */
	public void cancelRequest() {
		if (call != null) {
			call.cancel();
			call = null;
		}
	}

	/**
	 * 设置请求监听
	 *
	 * @param mCallBack
	 */
	public void setHttpListener(HttpCallBack mCallBack) {
		this.onResultListener = mCallBack;
	}

	public void setHttpDownLoadListener(HttpDownLoadCallBack mCallBack){
		this.onDownLoadListener = mCallBack;
	}

	/**
	 * 将对象转换成Map集合
	 *
	 * @param obj
	 *            要转换的对象
	 * @return Map集合
	 * @throws Exception
	 */
	public static Map<String, String> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}

		Map<String, String> map = new HashMap<String, String>();

		Field[] declaredFields = obj.getClass().getFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.get(obj) != null) {
				map.put(field.getName(), field.get(obj).toString());
			}
		}

		return map;
	}
}
