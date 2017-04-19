package zhuwentao.com.zwtwitmass.uimodule;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import zhuwentao.com.zwtwitmass.R;
import zhuwentao.com.zwtwitmass.uimodule.adapter.HorizontalGvAdapter;
import zhuwentao.com.zwtwitmass.uimodule.adapter.HorizontalGvPagerAdapter;
import zhuwentao.com.zwtwitmass.utils.DataUtil;

/**
 * 实现横向滚动的GridView，底部加圆点导航
 * Created by zhuwentao on 2017-03-09.
 */
public class HorizontalGridViewAct extends AppCompatActivity {

    // 每页要显示的App数量
    public static int APP_SIZE = 12;

    // GridView集合
    private List<GridView> mGridViewList;

    // 放圆点的list
    private List<View> dotViewsList;

    private ViewPager viewPager;

    // ViewPager的适配器
    private HorizontalGvPagerAdapter mGvPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_horizontal_gridview);

        viewPager = (ViewPager) findViewById(R.id.vp_horizontal_gridview);

        initViews(DataUtil.getAppData(HorizontalGridViewAct.this));
    }

    /**
     * 获取系统所有的应用程序，根据每页需要显示的item数量，生成相应的GridView页面
     */
    public void initViews(List<ResolveInfo> datas) {
        int dataSize = datas.size();

        // （需要页数 = 总数量 ÷ 每页显示数量）向上取整数
        int PageCount = (int) Math.ceil(dataSize / APP_SIZE);
        mGridViewList = new ArrayList<>();
        for (int i = 0; i <= PageCount; i++) {
            GridView appPage = new GridView(this);
            appPage.setAdapter(new HorizontalGvAdapter(this, datas, i));
            appPage.setNumColumns(4);
            appPage.setVerticalSpacing(1);
            appPage.setHorizontalSpacing(1);
            appPage.setHorizontalScrollBarEnabled(false);
            appPage.setVerticalScrollBarEnabled(false);
            mGridViewList.add(appPage);
        }

        if(dataSize % APP_SIZE == 0){
            mGridViewList.remove(mGridViewList.size()-1);
            PageCount--;
        }

        mGvPagerAdapter = new HorizontalGvPagerAdapter(mGridViewList);
        viewPager.setAdapter(mGvPagerAdapter);
        viewPager.addOnPageChangeListener(new MyPageChangeListener());

        addDot(PageCount);
    }

    /**
     * 创建指定数量的圆点
     * @param dotNumber viewPager的数量
     */
    private void addDot(int dotNumber) {
        if (null == dotViewsList) {
            dotViewsList = new ArrayList<View>();
        }
        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.ll_dot_container);
        for (int i = 0; i <= dotNumber; i++) {
            ImageView dotView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

            // 圆点与圆点之间的距离
            params.leftMargin = 10;
            params.rightMargin = 10;

            // 圆点的大小
            params.height = 15;
            params.width = 15;

            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }
        // 设置圆点默认选中第一个
        setDotShow(0);
    }

    /**
     * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {

            // 循环切换
            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    // 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    // 界面切换中
                    isAutoPlay = true;
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    // 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int pos) {
            setDotShow(pos);
        }

    }

    /**
     * 显示底部圆点导航
     * @param position 选中哪个圆点
     */
    private void setDotShow(int position){
        if (dotViewsList == null){
            return;
        }
        for (int i = 0; i < dotViewsList.size(); i++) {
            if (i == position) {
                dotViewsList.get(position).setBackgroundResource(R.drawable.ic_short_home_dot_hover_on);
            } else {
                dotViewsList.get(i).setBackgroundResource(R.drawable.ic_short_home_dot_hover_off);
            }
        }
    }
}
