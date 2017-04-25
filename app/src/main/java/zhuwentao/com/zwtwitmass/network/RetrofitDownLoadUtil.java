package zhuwentao.com.zwtwitmass.network;

import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 下载工具
 * Created by zhuwentao on 2017-04-25.
 */
public class RetrofitDownLoadUtil {

    public static final String TAG = "下载进度：";

    public void download(String url, String path) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1");
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response orginalResponse = chain.proceed(chain.request());

                        return orginalResponse.newBuilder()
                                .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                                    @Override
                                    public void onProgress(long progress, long total, boolean done) {
                                        Log.e(TAG, Looper.myLooper() + "");
                                        Log.e(TAG, "onProgress: " + "total ---->" + total + "done ---->" + progress);
                                    }
                                }))
                                .build();
                    }
                })
                .build();
        RetrofitService api = builder.client(client).build().create(RetrofitService.class);


        Call<ResponseBody> call = api.download("http://img.blog.csdn.net/20160421204842652");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
}
