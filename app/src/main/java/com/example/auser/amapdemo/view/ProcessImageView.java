package com.example.auser.amapdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lce on 2016/11/9.
 */

public class ProcessImageView extends ImageView {

    private Paint mPaint;// 画笔
    Context context = null;
    int progress = 0;

    public ProcessImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ProcessImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProcessImageView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mPaint = new Paint();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawShade(canvas);

        drawText(canvas);
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        mPaint.setAntiAlias(true); // 去锯齿
        mPaint.setTextSize(30);//画笔大小
        mPaint.setColor(Color.parseColor("#FFFFFF"));//画笔颜色
        mPaint.setStrokeWidth(2);//画笔宽度

        Rect rect = new Rect();
        mPaint.getTextBounds("100%", 0, "100%".length(), rect);// 确定文字的宽度

        canvas.drawText(
                progress + "%",
                getWidth() / 2 - rect.width() / 2,
                getHeight() / 2,
                mPaint);
    }

    /**
     * 绘制遮罩层,从左下角开始,根据进度,遮罩到右上角(全部遮罩)
     * @param canvas
     */
    private void drawShade(Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#70000000"));// 半透明

        //定义一个Path对象,封闭成一个三角形
        Path path1 = new Path();
        if(progress<=50) {
            path1.moveTo(0, getHeight());
            path1.lineTo(0, getHeight()- getHeight() * progress / 50);
            path1.lineTo(getWidth()*progress/50,getHeight() );
            path1.close();
            //根据path进行绘制,绘制三角形
            canvas.drawPath(path1,mPaint);
        }

        if(progress > 50) {
            //当进度大于50%的时候,让三角形一直显示
            mPaint.setAntiAlias(false); // 取消消除锯齿,否则会有一条线出现.
            Path path2 = new Path();
            path2.moveTo(0, getHeight());
            path2.lineTo(0, 0);
            path2.lineTo(getWidth(), getHeight() );
            path2.close();
            canvas.drawPath(path2,mPaint);

            //开始画梯形
            Path path3 = new Path();//梯形
            path3.moveTo(0, 0);//绘画基点
            path3.lineTo(getWidth()*(progress-50)/50,0 );
            path3.lineTo(getWidth(), getHeight()-getHeight()*(progress-50)/50);
            path3.lineTo(getWidth(), getHeight());
            path3.close();
            canvas.drawPath(path3,mPaint);
        }
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

}