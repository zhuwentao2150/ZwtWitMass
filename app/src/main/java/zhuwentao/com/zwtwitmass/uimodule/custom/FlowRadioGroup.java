package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

/**
 * 流式布局的RadioGroup
 * 用于处理多个宽度不一致的RadioButton的布局显示
 * Created by zhuwentao on 2017-04-18.
 */
public class FlowRadioGroup extends RadioGroup {

    /**
     * 左边边距
     */
    private int widthLeft = 20;

    /**
     * 上边边距
     */
    private int heightTop = 0;

    public FlowRadioGroup(Context context) {
        super(context);
    }

    public FlowRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        int x = 0;
        int y = 0;
        int row = 0;
        // 计算容器所需宽高
        for (int index = 0; index < childCount; index++) {
            final View child = getChildAt(index);
            if (child.getVisibility() != View.GONE) {
                child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                x += (width + widthLeft);
                y = row * (height + heightTop) + (height + heightTop);
                if (x > maxWidth) {
                    if (index != 0) {
                        row++;
                    }
                    if (width >= maxWidth) {
                        width = maxWidth - 30;
                    }
                    x = (width + widthLeft);
                    y = row * (height + heightTop) + (height + heightTop);
                }
            }
        }
        // 设置容器所需的宽度和高度
        setMeasuredDimension(maxWidth, y);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int childCount = getChildCount();
        int maxWidth = r - l;
        int x = 0;
        int y = 0;
        int row = 0;
        for (int i = 0; i < childCount; i++) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                x += (width + widthLeft);
                y = row * (height + heightTop) + (height + heightTop);
                if (x > maxWidth) {
                    if (i != 0)
                        row++;
                    if (width >= maxWidth) {
                        width = maxWidth - 30;
                    }
                    x = (width + widthLeft);
                    y = row * (height + heightTop) + (height + heightTop);
                }
                child.layout(x - width, y - height, x, y);
            }
        }
    }
}
