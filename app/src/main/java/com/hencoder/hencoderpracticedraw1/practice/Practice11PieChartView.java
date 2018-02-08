package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class Practice11PieChartView extends View {

    private static final String title = "饼图";
    private Paint paint;
    private int mWidth, mHeight;
    private int radius;
    private int lineLength;
    private int indicatorLength = 20;
    private String[] subTitles = {" Froyo", " Gingerbread", " Ice Cream Sandwich", " Jelly Bean", " KitKat", " Lollipop", " Marshmallow"};
    private int[] angles = {5, 10, 10, 55, 100, 135, 45};
    private int[] colors = {Color.YELLOW, Color.DKGRAY, Color.CYAN, Color.GRAY, Color.GREEN, Color.BLUE, Color.RED};
    private RectF rectF;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        float titleLength = paint.measureText(title);
        paint.setTextSize(40);
        canvas.drawText(title, mWidth / 2 - titleLength / 2, mHeight - 10, paint);

        canvas.translate(mWidth / 2, mHeight / 2);
        paint.setTextSize(25);
//        drawMashmallow(canvas);
//        drawFroyo(canvas);
        int saveCount = canvas.save();
        int sumAngle = 0;
        for (int i = 0; i < subTitles.length; i++) {
            if (i == subTitles.length-2){
                canvas.translate(-20,-20);
            }else if(i == subTitles.length-1){
                canvas.translate(20,20);
            }
            drawArc(canvas,i,sumAngle,sumAngle + angles[i]);
            sumAngle += angles[i];
        }

    }

    /**
     * 画
     *
     * @param canvas
     */
    private void drawMashmallow(Canvas canvas) {
        float stopX = lineLength * (float) Math.cos(-22.5 * Math.PI / 180);
        float stopY = lineLength * (float) Math.sin(-22.5 * Math.PI / 180);
        canvas.drawLine(0, 0, stopX, stopY, paint);
        canvas.drawLine(stopX, stopY, stopX + indicatorLength, stopY, paint);
        canvas.drawText(subTitles[0], stopX + indicatorLength, stopY, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 0, angles[0], true, paint);
    }

    /**
     * 画
     *
     * @param canvas
     */
    private void drawFroyo(Canvas canvas) {
        float stopX = lineLength * (float) Math.cos(5 * Math.PI / 180);
        float stopY = lineLength * (float) Math.sin(5 * Math.PI / 180);
        paint.setColor(Color.WHITE);
        canvas.drawLine(0, 0, stopX, stopY, paint);
        canvas.drawLine(stopX, stopY, stopX + indicatorLength, stopY, paint);
        canvas.drawText(subTitles[1], stopX + indicatorLength, stopY, paint);
        paint.setColor(Color.DKGRAY);
        canvas.drawArc(rectF, 0, angles[1], true, paint);
    }

    /**
     * 画
     *
     * @param canvas
     */
    private void drawGingerbread(Canvas canvas) {
        float stopX = lineLength * (float) Math.cos(2.5 * Math.PI / 180);
        float stopY = lineLength * (float) Math.sin(2.5 * Math.PI / 180);
        paint.setColor(Color.WHITE);
        canvas.drawLine(0, 0, stopX, stopY, paint);
        canvas.drawLine(stopX, stopY, stopX + indicatorLength, stopY, paint);
        canvas.drawText(subTitles[1], stopX + indicatorLength, stopY, paint);
        paint.setColor(Color.DKGRAY);
        canvas.drawArc(rectF, 0, angles[1], true, paint);
    }


    private void drawArc(Canvas canvas, int position,int startAngle, int endAngle) {
        int centAngle = (startAngle + endAngle)/2;
        float stopX = lineLength * (float) Math.cos(centAngle * Math.PI / 180);
        float stopY = lineLength * (float) Math.sin(centAngle * Math.PI / 180);
        paint.setColor(Color.WHITE);
        float finalX;
        if (stopX < 0) {
            finalX = stopX - indicatorLength;
        } else {
            finalX = stopX + indicatorLength;
        }
        canvas.drawLine(0, 0, stopX, stopY, paint);
        canvas.drawLine(stopX, stopY, finalX, stopY, paint);
        if (stopX<0){
            float textLength = paint.measureText(subTitles[position]+" ");
            canvas.drawText(subTitles[position], finalX - textLength, stopY, paint);
        }else {
            canvas.drawText(subTitles[position], finalX, stopY, paint);
        }
        paint.setColor(colors[position]);
        canvas.drawArc(rectF, startAngle, endAngle - startAngle, true, paint);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        radius = h / 3;
        lineLength = radius + 30;
        rectF = new RectF(-radius, -radius, radius, radius);
    }
}
