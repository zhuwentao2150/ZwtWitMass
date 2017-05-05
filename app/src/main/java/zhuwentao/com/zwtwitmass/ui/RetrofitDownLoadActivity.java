package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.BaseActivity;
import zhuwentao.com.zwtwitmass.uimodule.custom.DotProgressBar;

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
                // 下载地址
                String url = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";

            }
        });

    }
}
