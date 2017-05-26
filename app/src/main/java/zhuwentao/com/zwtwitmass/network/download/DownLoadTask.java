package zhuwentao.com.zwtwitmass.network.download;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import zhuwentao.com.zwtwitmass.network.callback.ProgressListener;
import zhuwentao.com.zwtwitmass.network.common.HttpService;
import zhuwentao.com.zwtwitmass.network.common.ProgressResponseBody;
import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 下载任务
 * Created by zhuwentao on 2017-05-21.
 */
public class DownLoadTask {

    private String url;

    private Call<ResponseBody> call;

    private DownloadCallBack callback;

    private long mRange = 0;
    private long mRangeIndex = 0;

    public DownLoadTask(String url) {
        this.url = url;
    }

    /**
     * 开始下载
     */
    public void start() {
        Retrofit.Builder builder = new Retrofit.Builder();
        // 这里的URL会被自动忽略
        builder.baseUrl("http://47.52.27.193:8088/myweb//software/Android/CN/OILRESET/V_PRO_OILRESET_V30.0_CN_20170112.7z/");
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        // 添加Http请求头部
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Range", "bytes=" + mRange + "-")
                                .build();
                        okhttp3.Response orginalResponse = chain.proceed(request);

                        // 监听返回
                        return orginalResponse.newBuilder()
                                .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {

                                    @Override
                                    public void onProgress(long progress, long total, boolean done) {
                                        mRange = mRangeIndex + progress;
                                        LogUtil.e("下载进度：" + mRange + "、progress=" + progress + "、total=" + total);
                                        final int proIndex = (int) ((double) mRange / (double) total * 100);
                                        if(callback != null){
                                            callback.onProgress(progress, total, done);
                                        }
                                    }
                                }))
                                .build();
                    }
                })
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        builder.client(client);
        Retrofit retrofit = builder.build();
        HttpService httpService = retrofit.create(HttpService.class);
        call = httpService.downloadFile(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(callback != null){
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mRangeIndex = mRange;
                try {
                    long count = call.request().body().contentLength();
                    LogUtil.e("下载失败，当前下载总量：" + count);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(callback != null){
                    callback.onFailure("download error,url="+call.request().body()+"message=" + t.getMessage());
                }
            }
        });
    }

    /**
     * 停止下载
     */
    public void stop() {
        if (call != null) {
            call.cancel();
        }
    }

    /**
     * 设置请求回调监听
     * @param callback
     */
    public void setDownloadCallback(DownloadCallBack callback){
        this.callback = callback;
    }

    /**
     * 获取URl
     * @return
     */
    public String getUrl(){
        if(url != null){
            return url;
        } else {
            return null;
        }
    }


}
