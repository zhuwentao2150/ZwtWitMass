package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆形仪表盘
 * Created by zhuwentao on 2017-06-08.
 */
public class CircleMeterView extends View{

    public CircleMeterView(Context context) {
        super(context);
    }

    public CircleMeterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleMeterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
