package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import zhuwentao.com.zwtwitmass.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_retrofit_test);

        mImageView = (ImageView) findViewById(R.id.iv_retrofit_download);
        mButton = (Button) findViewById(R.id.btn_download);
        mDotProgressBar = (DotProgressBar) findViewById(R.id.dpb_download);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";
                RetrofitDownLoadUtil retrofitDownLoadUtil = new RetrofitDownLoadUtil(RetrofitDownLoadActivity.this);
                retrofitDownLoadUtil.download(url, "");
                retrofitDownLoadUtil.setOnProgressListener(new ProgressListener() {
                    @Override
                    public void onProgress(long progress, long total, boolean done) {
                        final int prs = (int) ((double) progress / (double)total * 100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDotProgressBar.setProgress(prs);
                            }
                        });
                        LogUtil.e("下载进度：onProgress: " + "total ---->" + total + "done ---->" + progress);
                    }
                });
            }
        });

    }
}
