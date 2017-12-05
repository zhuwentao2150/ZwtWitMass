package zhuwentao.com.zwtwitmass.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;

import zhuwentao.com.zwtwitmass.R;

/**
 * 图标控件
 * Created by zhuwentao on 2017-11-20.
 */
public class MPChartActivity extends AppCompatActivity {

    /**
     * 直线图表控件
     */
    private LineChart mChart;

    private LineData mLineData;

    private Button mAddData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mpchart);

        initUI();

        initListener();
    }

    private void initUI() {
        mChart = (LineChart) findViewById(R.id.lc_line_chart);
        mAddData = (Button) findViewById(R.id.btn_chart_add_data);

        initChart();
    }

    private void initChart() {
        mChart.setNoDataText("暂无数据");
        mChart.setNoDataTextColor(Color.RED);
        mChart.setBackgroundColor(Color.LTGRAY);


        Description des = new Description();
        des.setText("内容");
        des.setTextColor(Color.BLUE);
        des.setTextSize(24);
        mChart.setDescription(new Description());


        LineData linedata = new LineData();
        mChart.setData(linedata);
    }

    private void initListener() {
        mAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
