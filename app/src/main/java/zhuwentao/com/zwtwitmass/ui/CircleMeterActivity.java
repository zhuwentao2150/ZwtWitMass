package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.widget.SeekBar;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.BaseActivity;
import zhuwentao.com.zwtwitmass.uimodule.custom.CircleLoadingView;
import zhuwentao.com.zwtwitmass.uimodule.custom.CircleMeterMaxMinView;

/**
 *
 * 圆形仪表盘
 *
 * Created by zhuwentao on 2017-06-11.
 */
public class CircleMeterActivity extends BaseActivity {

    private SeekBar indexSb;

    private CircleMeterMaxMinView circleMeter;
    private CircleLoadingView circleLoading;
    private CircleLoadingView circleLoadingTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_circle_meter);

        initUI();

        indexSb.setMax(100);

        initListener();

        circleMeter.setMaxMinValue(0, 100);
        circleLoading.startDotAnimator();
        circleLoadingTwo.startDotAnimator();
    }

    private void initListener() {
        indexSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleMeter.setProgress(progress);
                circleLoading.setProgress(progress);
                circleLoadingTwo.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initUI() {
        indexSb = (SeekBar) findViewById(R.id.sb_index_position);
        circleMeter = (CircleMeterMaxMinView) findViewById(R.id.cmv_meter);
        circleLoading = (CircleLoadingView) findViewById(R.id.clv_loading);
        circleLoadingTwo = (CircleLoadingView) findViewById(R.id.clv_loading_two);
    }
}
