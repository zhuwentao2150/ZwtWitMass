package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import zhuwentao.com.zwtwitmass.R;

/**
 * js与JAVA的互相调用
 * Created by zhuwentao on 2017-04-21.
 */
public class JsHtmlActivity extends AppCompatActivity {

    private static final String TAG = "androidAndJs";

    /**
     * Android与js的交互不成功，检查以下几点
     * 1.setJavaScriptEnabled设置为true
     * 2.网页的alert弹不出，需要重写WebChromeClient中的onJsAlert()方法
     * 3.在高于17版本上，要在每个需要和js交互的方法前加上@JavascriptInterface
     */

    private WebView mWebView;

    private Button mJsMethodBtn;

    private TextView mJsTextTv;

    private JsObject jsobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jshtml_java);

        mWebView = (WebView) findViewById(R.id.web_view);
        mJsMethodBtn = (Button) findViewById(R.id.btn_js_method);
        mJsTextTv = (TextView) findViewById(R.id.tv_js_text);

        jsobj = new JsObject();

        mWebView.loadUrl("file:///android_asset/html/index.html");

        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDefaultTextEncodingName("utf-8");

        // 如果这句话报错17SDK什么的，那么需要在类中的方法前加上@JavascriptInterface
        // 第一参数是java对象，第二个参数是对应的js里自定义的名称，也就是将一个java对象和js网页联系起来
        mWebView.addJavascriptInterface(jsobj, "contact");

        mJsMethodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加一个联系人
                jsobj.addPerson();
            }
        });

        // 无法弹出alter窗口时，需要复写这个方法
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        Log.e(TAG, "主线程name->" + Thread.currentThread().getName());


    }

    private class JsObject {

        // 此方法被js调用
        @JavascriptInterface
        public void showToast(final String message) {
            Toast.makeText(JsHtmlActivity.this, message, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "运行线程name->" + Thread.currentThread().getName());
        }

        // 在Web页面增加一个联系人
        public void addPerson() {
            Log.e(TAG, "运行线程addPerson name->" + Thread.currentThread().getName());
            String json = "[{\"name\":\"zwt\",\"phone\":\"15948888888\"}]";
            mWebView.loadUrl("javascript:addPerson(" + json + ")");

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
