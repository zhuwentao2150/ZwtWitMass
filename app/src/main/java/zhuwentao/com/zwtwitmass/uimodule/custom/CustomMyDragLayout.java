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

        /**
         *  1：一个ViewGroup， 也就是ViewDragHelper将要用来拖拽谁下面的子view
         *  2：灵敏度，一般设置为1.0f就行
         *  3：一个回调，用来处理拖动到位置
         */
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            // 这个方法可以用来指示哪个view可以拖动，通过这个方法，可以指定在创建ViewDragHelper时，哪一个子View可以被移动
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                // tryCaptureView如果返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
                // mEdgeTrackerView禁止移动
                return child == mDragView || child == mAutoBackView;
            }

            // 处理水平方向上的移动，第三个参数为建议的移动距离
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                // 这里的padding指的是我们自定义的ViewGroup
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - child.getWidth() - getPaddingRight();

                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            // 处理垂直方向上的移动，第三个参数为建议的移动距离
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - getPaddingBottom() - child.getHeight();

                final int newTop = Math.min(Math.max(top, topBound), bottomBound);

                return newTop;
            }

            // 手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                // mAutoBackView手指释放时可以自动回去
                if (releasedChild == mAutoBackView){
                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }
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

    // 设置mAutoBackView原先的所在位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
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
