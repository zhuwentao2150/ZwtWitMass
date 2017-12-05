package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import zhuwentao.com.zwtwitmass.utils.DensityUtil;

/**
 * 雷达扫描控件
 * Created by zhuwentao on 2017-12-05.
 */
public class RadarScanView extends View {

    // View宽
    private float mWidth;

    // View高
    private float mHeight;

    /**
     * 扫描的位置
     */
    public float mScanProgress;

    private Paint mLineCirclePaint;

    private Paint mScanCirclePaint;

    private Shader scanShader;

    private ValueAnimator animator;

    private Context mContext;

    public RadarScanView(Context context) {
        this(context, null);
    }

    public RadarScanView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        mContext = getContext();

        mLineCirclePaint = new Paint();
        mLineCirclePaint.setAntiAlias(true);
        mLineCirclePaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1/2f));
        mLineCirclePaint.setColor(Color.GRAY);
        mLineCirclePaint.setStyle(Paint.Style.STROKE);

        mScanCirclePaint = new Paint();
        mScanCirclePaint.setAntiAlias(true);
        mScanCirclePaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1/2f));
        mScanCirclePaint.setColor(Color.GRAY);
        mScanCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawLineCircle(canvas);
        if (isShowScan) {
            drawScanCircle(canvas);
        }
    }

    /**
     * 画雷达线圈
     * @param canvas
     */
    private void drawLineCircle(Canvas canvas) {
        canvas.save();

        float space = (mWidth / 2) / 5;
        for (int i = 0; i < 5; i++) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, space * i, mLineCirclePaint);
        }
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mLineCirclePaint);

        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mLineCirclePaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mLineCirclePaint);

        canvas.restore();
    }

    /**
     * 画扫描的线条
     * @param canvas
     */
    private void drawScanCircle(Canvas canvas) {
        canvas.save();

        canvas.rotate(mScanProgress * 3.6f, mWidth / 2, mHeight / 2);
        scanShader = new SweepGradient(mWidth / 2, mHeight / 2,
                new int[]{Color.TRANSPARENT, Color.parseColor("#00EE76")}, null);
        mScanCirclePaint.setShader(scanShader); // 设置着色器
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mScanCirclePaint);

        canvas.restore();
    }

    /**
     * 启动扫描旋转动画
     */
    public void startScanAnimator() {
        isShowScan = true;
        animator = ValueAnimator.ofFloat(0, 100);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 设置小圆点的进度，并通知界面重绘
                mScanProgress = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    private boolean isShowScan;

    /**
     * 停止动画
     */
    public void stopScanAnimator() {
        isShowScan = false;
        if (animator != null) {
            animator.cancel();
        }
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽度
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = DensityUtil.dip2px(mContext, 120);
        }

        // 获取高度
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = DensityUtil.dip2px(mContext, 120);
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }
}
