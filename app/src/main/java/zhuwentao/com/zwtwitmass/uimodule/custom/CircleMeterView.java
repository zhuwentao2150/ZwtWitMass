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
 *
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

    private void initUI(){
        mPaint = new Paint();


        mPaintText = new Paint();
        mPaintText.setStrokeWidth(1);
        mPaintText.setStyle(Paint.Style.FILL);
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
                String text = String.valueOf(i);
                Rect textBound = new Rect();
                mPaintText.getTextBounds(text, 0, text.length(), textBound);
                int textHeight = textBound.bottom - textBound.top; //获得文字高度


                canvas.save();
                canvas.translate(0,  raduis/2 + textHeight);
                canvas.rotate(-6 * i);
                canvas.drawText(text, -(textBound.right - textBound.left) / 2, textBound.bottom, mPaintText);
                canvas.restore();
            } else {
                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + 10, pointY, mPaint);
            }
            canvas.rotate(6, pointX, pointY);
        }
        canvas.restore();


        // 画文字刻度
        canvas.save();
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(24);
        canvas.rotate(-30, pointX, pointY);

        canvas.restore();
//        for (int i = 0; i < 60; i++) {
//            if (i > 40) {
//                canvas.rotate(6, pointX, pointY);
//                continue;
//            }
//            if (i % 5 == 0) {
//                canvas.drawText("" + i/5, pointX - raduis / 2 + 30, pointY , mPaint);
//            }
//            canvas.rotate(6, pointX, pointY);
//        }

//        canvas.drawText("1",
//                (float) (raduis/2 + (raduis/4 - 60) * Math.cos(Math.toRadians(60))) - 20,
//                (float) (raduis/2 - (raduis/4 - 60) * Math.cos(Math.toRadians(30))) + 20,
//                mPaint);
//        canvas.drawText("3",
//                getWidth() - 90,
//                getHeight() / 2 + 10,
//                mPaint);
//        canvas.drawText("4",
//                (float) (raduis + (raduis/2 - 60) * Math.cos(Math.toRadians(30))) - 20,
//                (float) (raduis + (raduis/2 - 60) * Math.cos(Math.toRadians(60))),
//                mPaint);
//
//        canvas.drawText("5",
//                (float) (raduis + (raduis/2 - 60) * Math.cos(Math.toRadians(60))) - 20,
//                (float) (raduis + (raduis/2 - 60) * Math.cos(Math.toRadians(30))),
//                mPaint);
//
//        canvas.drawText("6",
//                (float) (raduis + (raduis/2 - 60) * Math.cos(Math.toRadians(60))) - 20,
//                (float) (raduis + (raduis/2 - 60) * Math.cos(Math.toRadians(30))),
//                mPaint);
    }

    /**
     * 画内弧形
     */
    private void drawArcInside(Canvas canvas) {
        // 画外圆
        canvas.save();
        canvas.rotate(10, getWidth() / 2, getWidth() / 2);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        canvas.drawArc(new RectF((getWidth() / 2 - raduis / 2) + 80, (getHeight() / 2 - raduis / 2) + 80, (getWidth() / 2 + raduis / 2) - 80, (getHeight() / 2 + raduis / 2) - 80), 140, 240, false, mPaint);
        canvas.restore();
    }

    /**
     * 画内部数值
     */
    private void drawInsideSumText(Canvas canvas) {
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(80);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("3620", getWidth()/2 - 90, getHeight()/2, mPaint);
    }
}
