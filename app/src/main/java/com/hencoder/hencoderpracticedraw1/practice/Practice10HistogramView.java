package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class Practice10HistogramView extends View {

    private Paint paint;
    private int mWidth,mHeight;
    private static final int marginHorizontal = 100;
    private static final int marginVertical = 30;
    private String[] subTitles = {"Froyo","GB","ICS","JB","KitKat","L","M"};
    private String title = "直方图";
    private int xLength,yLength;
    private static final int space = 30;
    private int rectWidth;

    public Practice10HistogramView(Context context) {
        this(context,null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        drawTitle(canvas);
        drawXY(canvas);
        drawSubTitles(canvas);

    }

    /**
     * 画标题
     * @param canvas
     */
    private void drawTitle(Canvas canvas){
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setTextSize(50);
        float textWidth = paint.measureText(title);
        float baseLineX = mWidth/2 - textWidth/2;
        int baseLineY = mHeight - marginVertical;
        canvas.drawText(title,baseLineX,baseLineY,paint);
    }

    /**
     * 画坐标系
     * @param canvas
     */
    private void drawXY(Canvas canvas){
        canvas.drawLine(marginHorizontal,marginVertical,marginHorizontal,marginVertical + yLength,paint);
        canvas.drawLine(marginHorizontal,marginVertical + yLength,mWidth-marginHorizontal,marginVertical + yLength,paint);
        canvas.translate(marginHorizontal,marginVertical + yLength);
    }

    /**
     * 画子标题
     * @param canvas
     */
    private void drawSubTitles(Canvas canvas){
        paint.setTextSize(20);
        paint.setStrokeWidth(1);
        int titleCount = subTitles.length;
        int eachWidth  = xLength /titleCount;
        for (int i=0;i< titleCount;i++){
            float subTextLength = paint.measureText(subTitles[i]);
            int centerX = eachWidth * 1/2 + eachWidth*i ;
            float baseLineX = centerX - subTextLength/2;
            float baseLineY = 30;
            paint.setColor(Color.WHITE);
            canvas.drawText(subTitles[i],baseLineX,baseLineY,paint);
            drawValue(canvas,centerX);
        }
    }

    /**
     * 获取
     * @param centerPosition
     */
    private void drawValue(Canvas canvas,int centerPosition){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        int height = new Random().nextInt(yLength-1);
        RectF rectF = new RectF(centerPosition-rectWidth/2,-height,centerPosition+rectWidth/2,0);
        canvas.drawRect(rectF,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        xLength = mWidth - marginHorizontal *2;
        yLength = mHeight *2/3;
        rectWidth = xLength/subTitles.length - space;
    }
}
