package zhuwentao.com.zwtwitmass.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhuwentao.com.zwtwitmass.R;


/**
 * Activity 与 Fragment的多种通信方式
 */
public class FragmentLinkActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fragment_link);
        initUI();


        addView();
    }

    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp_fragment_content);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_fragment_add_view);
    }


    private void addView() {
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);



        mLinearLayout.addView(getTextView("one TextView"), layoutParams);
        mLinearLayout.addView(getTextView("two TextView"), layoutParams);
        mLinearLayout.addView(getTextView("Three TextView"), layoutParams);
    }

    private View getLine() {
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(layoutParams);



        return linearLayout;
    }


    private View getTextView(String name) {
        TextView mNameTv = new TextView(this);
        mNameTv.setText(name);
        mNameTv.setTextColor(Color.RED);
        mNameTv.setTextSize(22);
        mNameTv.setGravity(Gravity.CENTER);
        return mNameTv;
    }


}

