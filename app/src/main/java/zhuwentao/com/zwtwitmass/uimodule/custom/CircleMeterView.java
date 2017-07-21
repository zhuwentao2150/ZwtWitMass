package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆形仪表盘
 * <p>
 * Created by zhuwentao on 2017-06-08.
 */
public class CircleMeterView extends View {

    private int raduis = 500;


    private Paint mPaint;
    private Paint mPaintText;

    private int mWidth;
    private int mHeight;

    private int mPercent;

    //刻度宽度
    private float mTikeWidth;

    //第二个弧的宽度
    private int mScendArcWidth;

    //最小圆的半径
    private int mMinCircleRadius;

    //文字矩形的宽
    private int mRectWidth;

    //文字矩形的高
    private int mRectHeight;


    //文字内容
    private String mText = "";

    //文字的大小
    private int mTextSize;

    //设置文字颜色
    private int mTextColor;
    private int mArcColor;

    //小圆和指针颜色
    private int mMinCircleColor;

    //刻度的个数
    private int mTikeCount;

    private int mProgress;

    private Context mContext;

    public CircleMeterView(Context context) {
        super(context);
        initUI();
    }

    public CircleMeterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public CircleMeterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        mPaint = new Paint();


        mPaintText = new Paint();
        mPaintText.setStrokeWidth(1);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(24);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawArcScale(canvas);
        drawArcInside(canvas);
        drawInsideSumText(canvas);
        drawLine(canvas);
    }

    /**
     * 画外刻度
     */
    private void drawArcScale(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

        // 画外圆
        canvas.save();
        float x = (getWidth() - getHeight());
        float y = getHeight();
        canvas.drawArc(new RectF(getWidth() / 2 - raduis / 2, getHeight() / 2 - raduis / 2, getWidth() / 2 + raduis / 2, getHeight() / 2 + raduis / 2), 150, 240, false, mPaint);
        canvas.restore();

        // 画线刻度
        canvas.save();
        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        for (int i = 0; i < 60; i++) {
            if (i > 35 && i < 55) {
                canvas.rotate(6, pointX, pointY);
                continue;
            }
            if (i % 5 == 0) {
                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + 25, pointY, mPaint);

                // http://blog.csdn.net/qq_26971803/article/details/52061943
                String text = String.valueOf(i/5 + 1 == 12?0:i/5+1);
                Rect textBound = new Rect();
                mPaintText.getTextBounds(text, 0, text.length(), textBound);
                int textHeight = textBound.bottom - textBound.top;  //获得文字高度

                canvas.save();
                canvas.translate(pointX - raduis / 2 + 25 + textHeight + 5, pointY);    // 移动圆点坐标到文字需要绘制的区域
                canvas.rotate(-6 * i);
                canvas.drawText(text, -(textBound.right - textBound.left) / 2, textBound.bottom + 5, mPaintText);
                canvas.restore();
            } else {
                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + 10, pointY, mPaint);
            }
            canvas.rotate(6, pointX, pointY);
        }
        canvas.restore();
    }

    /**
     * 画内弧形
     */
    private void drawArcInside(Canvas canvas) {
        canvas.save();
        canvas.rotate(10, getWidth() / 2, getWidth() / 2);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        canvas.drawArc(new RectF((getWidth() / 2 - raduis / 2) + 100, (getHeight() / 2 - raduis / 2) + 100, (getWidth() / 2 + raduis / 2) - 100, (getHeight() / 2 + raduis / 2) - 100), 140, 240, false, mPaint);

        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.WHITE);

        canvas.rotate(-10, getWidth() / 2, getWidth() / 2);
        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        for (int i = 0; i < 60; i++) {
            if (i > 35 && i < 55) {
                canvas.rotate(6, pointX, pointY);
                continue;
            }
            canvas.drawLine((pointX - raduis / 2) + 85, pointY, (pointX - raduis / 2) + 100 + 15, pointY, mPaint);
            canvas.rotate(6, pointX, pointY);
        }


        canvas.restore();
    }

    /**
     * 画内部数值
     */
    private void drawInsideSumText(Canvas canvas) {
        canvas.save();

        // 需要让文字居中显示
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(60);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawText("3620", getWidth() / 2 - 70, getHeight() / 2 + 120, mPaint);
        canvas.restore();
    }

    /**
     * 画指针
     */
    private void drawLine(Canvas canvas) {
        canvas.save();

        canvas.rotate(-30, getWidth() / 2, getHeight() / 2);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.rotate(10, getWidth() / 2, getHeight() / 2);
        canvas.drawLine((getWidth() / 2 - raduis / 2) + 80, getHeight() / 2, getHeight() / 2, getHeight() / 2, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 20, mPaint);

        canvas.restore();
    }


    public void setProgress(int progress) {
        this.mProgress = progress;

        invalidate();
    }
}
