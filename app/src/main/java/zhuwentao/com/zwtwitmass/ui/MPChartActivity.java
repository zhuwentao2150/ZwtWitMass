package zhuwentao.com.zwtwitmass.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

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

    private RecyclerView mContentRlv;

    private Button mAddData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mpchart);

        initUI();

        initLineChart();

        initListener();
    }

    private void initUI() {
        mChart = (LineChart) findViewById(R.id.lc_line_chart);
        mAddData = (Button) findViewById(R.id.btn_chart_add_data);
        mContentRlv = (RecyclerView) findViewById(R.id.rlv_content);
    }

    private void initLineChart() {
        // 显示显示的样式
        mChart.setDrawBorders(true);

        XAxis xAxis = mChart.getXAxis();
        // 设置 X轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);// 值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        // 设置 X轴坐标间最小间距，放大时
        xAxis.setGranularity(1f);

        //设置X轴显示在图标上的刻度数量
        xAxis.setLabelCount(10, false);
        xAxis.setGridColor(Color.BLUE);

        YAxis yRAxis = mChart.getAxisRight();
        yRAxis.setEnabled(false);
        yRAxis.setGridColor(Color.RED); //网格线颜色
        YAxis yLAxis = mChart.getAxisLeft();
        yLAxis.setGridColor(Color.BLUE); //网格线颜色

        Legend legend = mChart.getLegend();
        legend.setEnabled(false);

        Description description = new Description();
        description.setEnabled(false);
        mChart.setDescription(description);



        // 设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 80));
        }

        // 一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircles(false);  // 不显示圆点
        lineDataSet.setDrawValues(false);   // 不显示数值到界面
        lineDataSet.setMode(LineDataSet.Mode.LINEAR); //线模式为圆滑曲线（默认折线）
        lineDataSet.setColor(Color.RED);
        lineDataSet.setLineWidth(2);

        LineData data = new LineData(lineDataSet);
        mChart.setData(data);
    }

    private void initListener() {
        mAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineData data = mChart.getData();

                Entry entry = new Entry(data.getEntryCount(), (float) (Math.random()) * 80);
                data.addEntry(entry, 0);

                data.notifyDataChanged();
                mChart.notifyDataSetChanged();
                mChart.setVisibleXRangeMaximum(10);
                mChart.moveViewToX(data.getEntryCount() - 5);
            }
        });
    }
}
