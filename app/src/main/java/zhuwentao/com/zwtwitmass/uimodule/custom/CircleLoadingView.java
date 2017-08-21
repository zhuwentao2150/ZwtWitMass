package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import zhuwentao.com.zwtwitmass.utils.DensityUtil;

/**
 * 圆形加载进度条
 * Created by zhuwentao on 2017-08-19.
 */
public class CircleLoadingView extends View {

    private Context mContext;

    // 普通画笔
    private Paint mPaint;

    /**
     * 圆的直径
     */
    private float raduis = 200;

    // View宽
    private int mWidth;
    // View高
    private int mHeight;


    public CircleLoadingView(Context context) {
        super(context);
        initUI();
    }

    public CircleLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        mContext = getContext();
        // 设置圆的直径
        raduis = DensityUtil.dip2px(mContext, raduis);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArcScale(canvas);
        drawArcIntoScale(canvas);
    }

    /**
     * 画刻度
     */
    private void drawArcScale(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.save();
        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        for (int i = 0; i < 100; i++) {
            canvas.rotate(3.6f, pointX, pointY);
            canvas.drawLine(getWidth()/2, 0, pointX, DensityUtil.dip2px(mContext, 15), mPaint);
        }
        canvas.restore();
    }

    /**
     * 画进度圆
     * @param canvas
     */
    private void drawArcIntoScale(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        for (int i = 0; i < 50; i++) {
            canvas.rotate(3.6f, pointX, pointY);
            canvas.drawLine(getWidth()/2, 0, pointX, DensityUtil.dip2px(mContext, 15), mPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content

        }

        /**
         * 设置高度
         */
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content

        }

        // 设置该view的宽高
        setMeasuredDimension(mWidth, mHeight);
    }
}
