package zhuwentao.com.zwtwitmass.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.network.DownLoadService;
import zhuwentao.com.zwtwitmass.network.callback.HttpDownLoadCallBack;
import zhuwentao.com.zwtwitmass.network.callback.ProgressListener;
import zhuwentao.com.zwtwitmass.network.common.HttpService;
import zhuwentao.com.zwtwitmass.network.common.ProgressResponseBody;
import zhuwentao.com.zwtwitmass.uimodule.BaseActivity;
import zhuwentao.com.zwtwitmass.uimodule.custom.DotProgressBar;
import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 使用Retrofit下载文件
 * Created by zhuwentao on 2017-04-25.
 */
public class RetrofitDownLoadActivity extends BaseActivity {

    private ImageView mImageView;

    private Button mButton;
    private Button mButtonStop;

    private DotProgressBar mDotProgressBar;

    private DownLoadService mDownLoadService;

    private long mRange = 0;

    // 下载地址
    String url = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_retrofit_test);

        // 启动服务，并与Service建立连接
        Intent intent = new Intent(RetrofitDownLoadActivity.this, DownLoadService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        mImageView = (ImageView) findViewById(R.id.iv_retrofit_download);
        mButton = (Button) findViewById(R.id.btn_download);
        mButtonStop = (Button) findViewById(R.id.btn_stop);
        mDotProgressBar = (DotProgressBar) findViewById(R.id.dpb_download);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // TODO: 启动下载服务的方式与Service耦合的太严重
                //mDownLoadService.startDownLoad(url);
            }
        });

        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDownLoadService.stopDownLoad(url);
            }
        });

    }


    private Call<ResponseBody> call;
    /**
     * 正常下载
     */
    private void download(String url) {
        Retrofit.Builder builder = new Retrofit.Builder();
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
                                mRange = progress;

                                final int proIndex = (int) ((double) mRange / (double) total * 100);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDotProgressBar.setProgress(proIndex);
                                    }
                                });
                            }
                        })).build();
                    }
                })
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        builder.client(client);
        Retrofit retrofit = builder.build();
        HttpService httpService = retrofit.create(HttpService.class);

        String rangeStr = "bytes=" + mRange + "-";
        call = httpService.downloadFile(url, rangeStr);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void cancel(){
        if (call != null){
            call.cancel();
        }
    }


    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.e("Activity与Service开启连接");
            mDownLoadService = ((DownLoadService.MyBinder) service).getService();

            mDownLoadService.setDownLoadListener(new HttpDownLoadCallBack() {
                @Override
                public void onReturnData(ResponseBody body) {
                    LogUtil.e("Activity下载完毕" + body.contentLength());
                }

                @Override
                public void onFailure(String message) {
                    LogUtil.e("Activity下载失败");
                }

                @Override
                public void onProgress(long progress, long total, boolean done) {
                    final int proIndex = (int) ((double) progress / (double) total * 100);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mDotProgressBar.setProgress(proIndex);
                        }
                    });
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.e("Activity与Service连接关闭");
        }
    };

    @Override
    protected void onDestroy() {
        // TODO: 与服务断开连接后将接收不到下载进度
        //unbindService(mServiceConnection);
        super.onDestroy();
    }
}
