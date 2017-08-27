package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import zhuwentao.com.zwtwitmass.R;
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

    private Paint mScalePaint;

    private Paint mPaintText;

    private Paint mScaleTextPaint;

    private Paint mTextPaint;

    private Paint mPointerPaint;

    private Paint mInsidePaint;


    // View宽
    private float mWidth;
    // View高
    private float mHeight;

    private float mProgress = 0;

    private int scaleColor;

    private int scaleTextColor;

    private int insideCircleColor;

    private int textSize;

    private int textColor;

    private int pointerColor;

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
        TypedArray tya = context.obtainStyledAttributes(attrs, R.styleable.CircleMeter);
        scaleColor = tya.getColor(R.styleable.CircleMeter_scaleColor, Color.BLUE);
        scaleTextColor = tya.getColor(R.styleable.CircleMeter_scaleTextColor, Color.BLACK);
        insideCircleColor = tya.getColor(R.styleable.CircleMeter_insideCircleColor, Color.BLUE);
        textSize = tya.getDimensionPixelSize(R.styleable.CircleMeter_textSize2, 36);
        textColor = tya.getColor(R.styleable.CircleMeter_textColor2, Color.BLACK);
        pointerColor = tya.getColor(R.styleable.CircleMeter_pointerColor, Color.RED);
        tya.recycle();

        initUI();
    }

    private void initUI() {
        mContext = getContext();

        // 设置圆的直径
        raduis = DensityUtil.dip2px(mContext, raduis);

        // 刻度圆画笔
        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setStrokeWidth(DensityUtil.dip2px(mContext, 2));
        mScalePaint.setColor(scaleColor);
        mScalePaint.setStrokeCap(Paint.Cap.ROUND);
        mScalePaint.setStyle(Paint.Style.STROKE);

        // 刻度文字画笔
        mScaleTextPaint = new Paint();
        mScaleTextPaint.setAntiAlias(true);
        mScaleTextPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 2));
        mScaleTextPaint.setColor(scaleTextColor);
        mScaleTextPaint.setTextSize(24);
        mScaleTextPaint.setStyle(Paint.Style.FILL);

        // 中间值的画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        mTextPaint.setTextSize(24);
        mTextPaint.setColor(textColor);
        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint.setStyle(Paint.Style.FILL);

        // 内部扇形刻度画笔
        mInsidePaint = new Paint();
        mInsidePaint.setAntiAlias(true);
        mInsidePaint.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        mInsidePaint.setColor(insideCircleColor);
        mInsidePaint.setStyle(Paint.Style.FILL);


        mPaint = new Paint();
        mPaintText = new Paint();
        mPaintText.setStrokeWidth(DensityUtil.dip2px(mContext, 1));
        mPaintText.setStyle(Paint.Style.FILL);

        mPaintText.setColor(Color.BLACK);

        mPaintText.setTextSize(24);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArcScale(canvas);
        drawArcInside(canvas);
        drawInsideSumText(canvas);
        drawPointer(canvas);
    }

    private void drawArcScaleText(Canvas canvas) {
        canvas.save();
        for (int i = 0; i <= 40; i++) {

        }
        canvas.restore();
    }

    /**
     * 画外圆和文字刻度
     */
    private void drawArcScale(Canvas canvas) {
        canvas.save();

        // 让画布逆向旋转30度
        canvas.rotate(-30, mWidth / 2, mHeight / 2);

        // 最外圆的线条宽度，避免线条粗时被遮蔽
        float scaleWidth = mScalePaint.getStrokeWidth();

        canvas.drawArc(new RectF(scaleWidth, scaleWidth, mWidth - scaleWidth, mHeight - scaleWidth), 180, 240, false, mScalePaint);

        // 定义文字旋转回的角度
        int rotateValue = 30;

        // 总八个等分，每等分5个刻度，所以总共需要40个刻度
        for (int i = 0; i <= 40; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(scaleWidth, mHeight / 2, DensityUtil.dip2px(mContext, 15), mHeight / 2, mScalePaint);

                // 画文字
                String text = String.valueOf(i / 5);
                Rect textBound = new Rect();
                mScaleTextPaint.getTextBounds(text, 0, text.length(), textBound);   // 获取文字的矩形范围
                int textWidth = textBound.right - textBound.left;  // 获得文字宽度
                int textHeight = textBound.bottom - textBound.top;  // 获得文字高度

                canvas.save();
                canvas.translate(DensityUtil.dip2px(mContext, 15) + textWidth + DensityUtil.dip2px(mContext, 5), mHeight / 2);  // 移动画布的圆点

                // 简化写法
                if (i == 0) {
                    canvas.rotate(rotateValue);
                } else {
                    canvas.rotate(rotateValue);
                }
                rotateValue = rotateValue - 30;

                // 笨方法
//                if (i == 0) {
//                    canvas.rotate(30);
//                }
//                if (i == 5) {
//                    canvas.rotate(0);
//                }
//                if (i == 10) {
//                    canvas.rotate(-30);
//                }
//                if (i == 15) {
//                    canvas.rotate(-60);
//                }
//                if (i == 20) {
//                    canvas.rotate(-90);
//                }
//                if (i == 25) {
//                    canvas.rotate(-120);
//                }
//                if (i == 30) {
//                    canvas.rotate(-150);
//                }
//                if (i == 35) {
//                    canvas.rotate(-180);
//                }
//                if (i == 40) {
//                    canvas.rotate(-210);
//                }
//                canvas.drawText(text, -textWidth + DensityUtil.dip2px(mContext, 2),  textHeight/2, mScaleTextPaint);
                canvas.drawText(text, -textWidth / 2, textHeight / 2, mScaleTextPaint);
                canvas.restore();

            } else {
                canvas.drawLine(scaleWidth, mHeight / 2, DensityUtil.dip2px(mContext, 10), mHeight / 2, mScalePaint);
            }
            canvas.rotate(6, mWidth / 2, mHeight / 2);
        }

//
//        int pointX = getHeight() / 2;
//        int pointY = getWidth() / 2;
//        for (int i = 0; i < 60; i++) {
//            if (i > 35 && i < 55) {
//                canvas.rotate(6, pointX, pointY);
//                continue;
//            }
//            if (i % 5 == 0) {
//                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + DensityUtil.dip2px(mContext, 10), pointY, mPaint);
//
//                String text = String.valueOf(i / 5 + 1 == 12 ? 0 : i / 5 + 1);    // 修改11位子上的数字
//
//                Rect textBound = new Rect();
//                mPaintText.getTextBounds(text, 0, text.length(), textBound);    // 获取文字的矩形范围
//                int textHeight = textBound.bottom - textBound.top;  //获得文字高度
//
//                canvas.save();
//                canvas.translate(pointX - raduis / 2 + DensityUtil.dip2px(mContext, 10) + textHeight, pointY);    // 移动圆点坐标到文字需要绘制的区域
//                canvas.rotate(-6 * i);
//                canvas.drawText(text, -(textBound.right - textBound.left) / 2, textBound.bottom + DensityUtil.dip2px(mContext, 2), mPaintText);
//                canvas.restore();
//            } else {
//                canvas.drawLine(pointX - raduis / 2, pointY, pointX - raduis / 2 + DensityUtil.dip2px(mContext, 5), pointY, mPaint);
//            }
//            canvas.rotate(6, pointX, pointY);
//        }
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
        canvas.drawText(showValue, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight + DensityUtil.dip2px(mContext, 45), mPaint);

        canvas.restore();
    }

    /**
     * 画指针
     */
    private void drawPointer(Canvas canvas) {
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
