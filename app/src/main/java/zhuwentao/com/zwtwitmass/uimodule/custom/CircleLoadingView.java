package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import zhuwentao.com.zwtwitmass.utils.DensityUtil;

/**
 * 圆形加载进度条
 * Created by zhuwentao on 2017-08-19.
 */
public class CircleLoadingView extends View {

    private Context mContext;

    // 普通画笔
    private Paint mPaint;

    // 当前进度
    private int progress = 0;

    /**
     * 小圆的当前进度
     */
    public float mDotProgress;

    /**
     * 圆的直径
     */
    private float raduis = 180;

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


//        // 绘图线程
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    mDotProgress++;
//                    if (mDotProgress == 100) {
//                        mDotProgress = 0;
//                    }
//                    postInvalidate();
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArcScale(canvas);
        drawTextValue(canvas);
        drawRotateDot(canvas);
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
            if (progress > i) {
                mPaint.setColor(Color.BLUE);
                canvas.drawLine(getWidth() / 2, 0, pointX, DensityUtil.dip2px(mContext, 10), mPaint);
            } else {
                mPaint.setColor(Color.LTGRAY);
                canvas.drawLine(getWidth()/2, 0, pointX, DensityUtil.dip2px(mContext, 10), mPaint);
            }
            // 旋转的度数 = 100 % 360
            canvas.rotate(3.6f, pointX, pointY);
        }
        canvas.restore();
    }

    /**
     * 画内部数值
     * @param canvas
     */
    private void drawTextValue(Canvas canvas) {
        canvas.save();

        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(36);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);

        String showValue = String.valueOf(progress);
        Rect textBound = new Rect();
        mPaint.getTextBounds(showValue, 0, showValue.length(), textBound);    // 获取文字的矩形范围
        float textWidth = textBound.right - textBound.left;  // 获得文字宽
        float textHeight = textBound.bottom - textBound.top; // 获得文字高

        canvas.drawText(showValue, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, mPaint);

        canvas.restore();
    }

    /**
     * 画外部旋转小圆点
     * @param canvas
     */
    private void drawRotateDot(final Canvas canvas) {
        canvas.save();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.rotate(mDotProgress * 3.6f, mWidth / 2, mHeight / 2);
        canvas.drawCircle(getWidth()/2, DensityUtil.dip2px(mContext, 10) + DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 3), mPaint);


        canvas.restore();
    }

    /**
     * 启动小圆点旋转动画
     */
    public void start() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        // 设置旋转速率
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 只能转成float类型
                mDotProgress =  (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }




    /**
     * 设置进度
     * @param progress
     */
    public void setProgress(int progress){
        this.progress = progress;
        invalidate();
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
