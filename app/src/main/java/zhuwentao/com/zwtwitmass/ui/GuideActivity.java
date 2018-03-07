package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import zhuwentao.com.zwtwitmass.R;

/**
 * 使用jQuery实现item点击显示或隐藏的特效
 * Created by zhuwentao on 2018-03-03.
 */
public class GuideActivity extends AppCompatActivity {

    private final String htmlPath = "file:///android_asset/guide.html";

    private WebView mContentWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        mContentWv = (WebView) findViewById(R.id.web_guide_content);

        mContentWv.getSettings().setJavaScriptEnabled(true);
        mContentWv.loadUrl(htmlPath);
    }

}
