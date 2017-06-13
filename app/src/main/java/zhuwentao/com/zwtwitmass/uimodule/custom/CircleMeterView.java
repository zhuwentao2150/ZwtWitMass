package zhuwentao.com.zwtwitmass.uimodule.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆形仪表盘
 * Created by zhuwentao on 2017-06-08.
 */
public class CircleMeterView extends View{

    private int raduis;

    private Paint mPaint;


    public CircleMeterView(Context context) {

        super(context);
    }

    public CircleMeterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleMeterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArcScale(canvas);
        drawArcInside(canvas);
    }

    /**
     * 画外刻度
     */
    private void drawArcScale(Canvas canvas) {
        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        raduis = 250;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(pointX, pointY, raduis, mPaint);

        for (int i = 0; i < 60; i++) {
            if (i % 6 == 0) {
                canvas.drawLine(pointX - raduis + 10, pointY, pointX - raduis + 50 , pointY, mPaint);
            } else {
                canvas.drawLine(pointX - raduis + 10, pointY, pointX - raduis + 30, pointY, mPaint);
            }
            canvas.rotate(6, pointX, pointY);
        }
    }

    /**
     * 画内弧形
     */
    private void drawArcInside(Canvas canvas) {
        int pointX = getHeight() / 2;
        int pointY = getWidth() / 2;
        raduis = 250;

        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(pointX, pointY, pointX, pointY);
        mPaint.setColor(Color.GREEN);
        // 绘制圆弧：oval:圆弧所在的椭圆对象，startAngle:圆弧的起始角度，sweepAngle:圆弧的角度，useCenter:是否显示半径连线,true表示显示圆弧与圆心的半径连线，false表示不显示
        canvas.drawArc(oval, -90, 10, false, mPaint);    // 根据进度画圆弧

    }

    /**
     * 画内部数值
     */
    private void drawInsideSumText(Canvas canvas) {

    }
}
