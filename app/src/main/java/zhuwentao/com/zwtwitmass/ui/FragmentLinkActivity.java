package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import zhuwentao.com.zwtwitmass.R;


/**
 * Activity 与 Fragment的多种通信方式
 */
public class FragmentLinkActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fragment_link);
        initUI();
    }

    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp_fragment_content);
    }

}

