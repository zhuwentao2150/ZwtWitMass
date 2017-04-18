package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决ScrollView中嵌套GridView时后者无法显示完全的问题
 * Created by zhuwentao on 2017-04-18.
 */
public class CustomScrollGridView extends GridView {

    public CustomScrollGridView(Context context) {
        super(context);
    }

    public CustomScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
