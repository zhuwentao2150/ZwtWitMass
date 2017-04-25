package zhuwentao.com.zwtwitmass.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 下载工具
 * Created by zhuwentao on 2017-04-25.
 */
public class RetrofitDownLoadUtil {

    public static final String TAG = "下载进度：";

    public void download(String url, String path) {

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk/")
                .client(getHttpClient())
                .build();
        RetrofitService api = builder.create(RetrofitService.class);


        Call<ResponseBody> call = api.download();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                LogUtil.e("下载完成");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("下载出错");
            }
        });


    }

    public OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response orginalResponse = chain.proceed(chain.request());
                        return orginalResponse.newBuilder().body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                            @Override
                            public void onProgress(long progress, long total, boolean done) {
                                LogUtil.e("下载进度：onProgress: " + "total ---->" + total + "done ---->" + progress);
                            }
                        })).build();
                    }
                }).build();

        return client;
    }
}
