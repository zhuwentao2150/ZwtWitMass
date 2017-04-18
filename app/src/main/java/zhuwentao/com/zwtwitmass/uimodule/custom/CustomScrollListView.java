package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决ScrollView中嵌套RefreshListView时后者无法显示完全的问题
 * Created by zhuwentao on 2017-04-18.
 */
public class CustomScrollListView extends ListView {

    public CustomScrollListView(Context context) {
        super(context);
    }

    public CustomScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
