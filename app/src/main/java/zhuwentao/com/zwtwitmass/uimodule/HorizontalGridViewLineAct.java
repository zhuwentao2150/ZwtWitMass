package zhuwentao.com.zwtwitmass.uimodule;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.List;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.adapter.HorizontalGvLineAdapter;
import zhuwentao.com.zwtwitmass.utils.DataUtil;
import zhuwentao.com.zwtwitmass.utils.DensityUtil;

/**
 * 单行横向滚动的GridView
 * Created by zhuwentao on 2017-04-19.
 */
public class HorizontalGridViewLineAct extends AppCompatActivity {

    private GridView mContentGv;

    private List<ResolveInfo> datas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_horizontal_gridview_line);

        initUI();

        changeGridView();

        mContentGv.setAdapter(new HorizontalGvLineAdapter(HorizontalGridViewLineAct.this, datas));
    }

    private void initUI() {
        mContentGv = (GridView) findViewById(R.id.gv_horizontal_gridview_line);
        datas = DataUtil.getAppData(HorizontalGridViewLineAct.this);
    }

    /**
     * 将GridView改成单行横向布局
     */
    private void changeGridView() {
        // item宽度
        int itemWidth = DensityUtil.dip2px(this, 100);
        // item之间的间隔
        int itemPaddingH = DensityUtil.dip2px(this, 1);

        int size = datas.size();
        // GridView宽度
        int gridviewWidth = size * (itemWidth + itemPaddingH);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        mContentGv.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        mContentGv.setColumnWidth(itemWidth);
        mContentGv.setHorizontalSpacing(itemPaddingH);
        mContentGv.setStretchMode(GridView.NO_STRETCH);
        mContentGv.setNumColumns(size);
    }


}
