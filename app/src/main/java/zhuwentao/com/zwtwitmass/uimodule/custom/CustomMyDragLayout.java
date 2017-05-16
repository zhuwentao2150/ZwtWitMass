package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 可以拖拽child控件的一个自定义容器
 * Created by zhuwentao on 2017-05-16.
 */
public class CustomMyDragLayout extends LinearLayout {

    private ViewDragHelper mDragger;

    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();

    public CustomMyDragLayout(Context context) {
        super(context);
    }

    public CustomMyDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            // 这个方法可以用来指示哪个view可以拖动，通过这个方法，可以指定在创建ViewDragHelper时，哪一个子View可以被移动
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView || child == mAutoBackView;
            }

            // 处理水平方向上的移动，第三个参数为建议的移动距离
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child, left, dx);
            }

            // 处理垂直方向上的移动，第三个参数为建议的移动距离
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return super.clampViewPositionVertical(child, top, dy);
            }

            // 手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
            }

            // 在边界拖动时回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                mDragger.captureChildView(mEdgeTrackerView, pointerId);
            }
        });

        // 使用边界检测需要添加上以下代码
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    // 对点击事件进行处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将触摸事件传递给ViewDragHelper
        mDragger.processTouchEvent(event);
        // 拦截此事件，不再向下传递
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)){
            invalidate();
        }
    }


    // 绑定控件
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }

}
