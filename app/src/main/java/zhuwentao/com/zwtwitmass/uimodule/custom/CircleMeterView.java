package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import zhuwentao.com.zwtwitmass.utils.DensityUtil;

/**
 * 圆形仪表盘
 * Created by zhuwentao on 2017-06-08.
 */
public class CircleMeterView extends View {

    /**
     * 圆的直径
     */
    private float raduis = 250;

    private float value = 0;

    // 普通画笔
    private Paint mPaint;
    private Paint mPaintText;

    // View宽
    private int mWidth;
    // View高
    private int mHeight;

    private int mPercent;

    //刻度宽度
    private float mTikeWidth;

    //第二个弧的宽度
    private int mScendArcWidth;

    //最小圆的半径
    private int mMinCircleRadius;

    //小圆和指针颜色
    private int mMinCircleColor;

    //刻度的个数
    private int mTikeCount;

    private float mProgress = 0;

    private Context mContext;

    public CircleMeterView(Context context) {
        this(context, null);
    }

    public CircleMeterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleMeterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取用户配置属性



        initUI();
    }

    private void initUI() {
        mContext = getContext();

        // 设置圆的直径
        raduis = DensityUtil.dip2px(mContext, raduis);

        mPaint = new Paint();
        mPaintText = new Paint();
        mPaintText.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画刻度
        drawArcScale(canvas);

        // 画内圆弧
        drawArcInside(canvas);

        // 画中间文字
        drawInsideSumText(canvas);

        // 画指针
        drawLine(canvas);
    }

    /**
     * 画外刻度
     */
    private void drawArcScale(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
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
                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + DensityUtil.dip2px(mContext, 10), pointY, mPaint);

                String text = String.valueOf(i / 5 + 1 == 12 ? 0 : i / 5 + 1);    // 修改11位子上的数字

                Rect textBound = new Rect();
                mPaintText.getTextBounds(text, 0, text.length(), textBound);    // 获取文字的矩形范围
                int textHeight = textBound.bottom - textBound.top;  //获得文字高度

                canvas.save();
                canvas.translate(pointX - raduis / 2 + DensityUtil.dip2px(mContext, 10) + textHeight, pointY);    // 移动圆点坐标到文字需要绘制的区域
                canvas.rotate(-6 * i);
                canvas.drawText(text, -(textBound.right - textBound.left) / 2, textBound.bottom + DensityUtil.dip2px(mContext, 2), mPaintText);
                canvas.restore();
            } else {
                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + DensityUtil.dip2px(mContext, 5), pointY, mPaint);
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
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 10));

        float inside = DensityUtil.dip2px(mContext, 40);
        canvas.drawArc(new RectF((getWidth() / 2 - raduis / 2) + inside, (getHeight() / 2 - raduis / 2) + inside, (getWidth() / 2 + raduis / 2) - inside, (getHeight() / 2 + raduis / 2) - inside), 140, 240, false, mPaint);
        // 一个小方格占用6等份的角度
        // canvas.drawArc(new RectF((getWidth() / 2 - raduis / 2) + inside + 1, (getHeight() / 2 - raduis / 2) + inside, (getWidth() / 2 + raduis / 2) - inside, (getHeight() / 2 + raduis / 2) - inside), 140, 12, false, mPaint);

        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        mPaint.setColor(Color.WHITE);

        canvas.rotate(-10, getWidth() / 2, getWidth() / 2);

        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        for (int i = 0; i < 60; i++) {
            if (i > 35 && i < 55) {
                canvas.rotate(6, pointX, pointY);
                continue;
            }
            canvas.drawLine((pointX - raduis / 2) + DensityUtil.dip2px(mContext, 30), pointY, (pointX - raduis / 2) + inside + DensityUtil.dip2px(mContext, 5), pointY, mPaint);
            canvas.rotate(6, pointX, pointY);
        }
        canvas.restore();
    }

    /**
     * 画内部数值
     */
    private void drawInsideSumText(Canvas canvas) {
        canvas.save();

        // 将画布圆点移动到View中心
        //canvas.translate(getWidth() / 2, getHeight() / 2);

        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(60);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        // 获取文字居中显示需要的参数
        String showValue = String.valueOf(value);
        Rect textBound = new Rect();
        mPaint.getTextBounds(showValue, 0, showValue.length(), textBound);    // 获取文字的矩形范围
        float textWidth = textBound.right - textBound.left;  // 获得文字宽
        float textHeight = textBound.bottom - textBound.top; // 获得文字高
        canvas.drawText(showValue, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight + DensityUtil.dip2px(mContext, 45), mPaint);

        canvas.restore();
    }

    /**
     * 画指针
     */
    private void drawLine(Canvas canvas) {
        canvas.save();

        // 旋转到0的位置
        canvas.rotate(-30, getWidth() / 2, getHeight() / 2);

        mPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 3));
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.rotate(mProgress, getWidth() / 2, getHeight() / 2);
        canvas.drawLine((getWidth() / 2 - raduis / 2) + DensityUtil.dip2px(mContext, 50), getHeight() / 2, getHeight() / 2, getHeight() / 2, mPaint);


        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, DensityUtil.dip2px(mContext, 10), mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, DensityUtil.dip2px(mContext, 5), mPaint);

        canvas.restore();
    }


    public void setProgress(int progress) {
        // 计算公式 = progress * ((格子数目40 * 每个小刻度6) / 100)
        // 设置指针旋转的位置
        this.mProgress = (float) progress * 2.4f;

        // 设置显示的数值
        this.value = (float) (progress * 0.08);
        invalidate();
    }
}
