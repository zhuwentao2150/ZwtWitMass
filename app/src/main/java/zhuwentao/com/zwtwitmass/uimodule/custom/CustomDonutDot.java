package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import zhuwentao.com.zwtwitmass.R;

/**
 *
 * Created by zhuwentao on 2017-05-15.
 */
public class CustomDonutDot extends View{

    private int mFirstColor;

    private int mSecondColor;

    private int mCircleWidth;

    private Paint mPaint;

    private int mCurrentCount = 1;

    private Bitmap mImage;

    private int mSplitSize;

    private int mCount;

    private Rect mRect;


    public CustomDonutDot(Context context) {
        this(context, null);
    }

    public CustomDonutDot(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDonutDot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取属性文件中的属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customDonutView);
        mFirstColor = a.getColor(R.styleable.customDonutView_firstColor, Color.GREEN);
        mSecondColor = a.getColor(R.styleable.customDonutView_secondColor, Color.CYAN);
        mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.customDonutView_bg, 0));
        mCircleWidth = a.getDimensionPixelSize(R.styleable.customDonutView_circleWidth, 38);
        mCount = a.getInt(R.styleable.customDonutView_dotCount, 20);
        mSplitSize = a.getInt(R.styleable.customDonutView_splitSize, 20);

        a.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        int centre = getWidth() / 2;    // 圆心
        int radius = centre - mCircleWidth / 2; // 半径

        // 画圆块
        drawOval(canvas, centre, radius);

        /** 计算内切正方形的位置 */
        int relRadius = radius - mCircleWidth / 2;  // 获得内圆的半径
        /** 内切正方形距离左边 = mCircleWidth + relRadius - √2 / 2  */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        /** 内切正方形距离顶部 = mCircleWidth + relRadius - √2 / 2  */
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        // 如果图片比较小， 那么根据图片的尺寸放置到正中心
        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mImage.getWidth());
            mRect.bottom = (int) (mRect.top + mImage.getHeight());
        }

        // 绘图
        canvas.drawBitmap(mImage, null, mRect, mPaint);
    }



    /**
     * 根据参数画出每个小块
     *
     * @param canvas
     * @param centre
     * @param radius
     */
    private void drawOval(Canvas canvas, int centre, int radius) {
        // 根据需要画的个数以及间隙计算每个块块所占的比例*360
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;
        // 为画弧形准备
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        mPaint.setColor(mFirstColor);   // 设置圆环的颜色
        for (int i = 0; i < mCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

        mPaint.setColor(mSecondColor);  // 设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++) {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

    }


    /**
     * 当前数量+1
     */
    public void up()
    {
        mCurrentCount++;
        postInvalidate();
    }

    /**
     * 当前数量-1
     */
    public void down()
    {
        mCurrentCount--;
        postInvalidate();
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if (xUp > xDown)// 下滑
                {
                    down();
                } else
                {
                    up();
                }
                break;
        }

        return true;
    }
}
