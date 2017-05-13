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

import okhttp3.ResponseBody;
import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.network.DownLoadService;
import zhuwentao.com.zwtwitmass.network.callback.HttpDownLoadCallBack;
import zhuwentao.com.zwtwitmass.uimodule.BaseActivity;
import zhuwentao.com.zwtwitmass.uimodule.custom.DotProgressBar;
import zhuwentao.com.zwtwitmass.utils.LogUtil;

/**
 * 使用Retrofit下载文件
 * Created by zhuwentao on 2017-04-25.
 */
public class RetrofitDownLoadActivity extends BaseActivity{

    private ImageView mImageView;

    private Button mButton;

    private DotProgressBar mDotProgressBar;

    private DownLoadService mDownLoadService;

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
        mDotProgressBar = (DotProgressBar) findViewById(R.id.dpb_download);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载地址
                String url = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";

                // TODO: 启动下载服务的方式与Service耦合的太严重
                mDownLoadService.startDownLoad(url);
            }
        });

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
