package zhuwentao.com.zwtwitmass.uimodule.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * 横向GridView的ViewPager适配器
 * Created by zhuwentao on 2017-03-09.
 */
public class HorizontalGvPagerAdapter extends PagerAdapter{

    private List<GridView> mGridViewList;

    public HorizontalGvPagerAdapter(List<GridView> mGridViewList){
       this.mGridViewList = mGridViewList;
    }

    @Override
    public int getCount() {
        return mGridViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mGridViewList.get(position));
        return mGridViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
