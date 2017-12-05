package zhuwentao.com.zwtwitmass.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.custom.RadarScanView;

/**
 * 雷达扫描
 * Created by zhuwentao on 2017-12-05.
 */
public class RadarScanActivity extends AppCompatActivity {


    private RadarScanView mRadarScanView;

    private Button mStartScanBtn;

    private Button mStopScanBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_radar_scan);

        mRadarScanView = (RadarScanView) findViewById(R.id.rsv_radar_scan);
        mStartScanBtn = (Button) findViewById(R.id.btn_radar_scan_start);
        mStopScanBtn = (Button) findViewById(R.id.btn_radar_scan_stop);

        mStartScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadarScanView.startScanAnimator();
            }
        });

        mStopScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadarScanView.stopScanAnimator();
            }
        });

    }
}
