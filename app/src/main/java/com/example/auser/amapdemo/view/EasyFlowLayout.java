package com.example.auser.amapdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/27.
 */

public class EasyFlowLayout extends ViewGroup {

    public EasyFlowLayout(Context context) {
        this(context, null);
    }

    public EasyFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取该控件的宽高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //每一行中的每一个View的宽高
        int width = 0, height = 0;

        //每一行的宽高
        int lineWidth = 0, lineHeight = 0;


        int viewCount = getChildCount();

        for (int i = 0; i < viewCount; i++) {
            View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                //当前行过长，换行
                width = Math.max(width, lineWidth);
                height += lineHeight;

                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不换行
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }

        }

        //最后一个子View
        width = Math.max(width, lineWidth);
        height += lineHeight;

//        MeasureSpec.EXACTLY  -->  match parent ,100dp
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth :
                        (width + getPaddingLeft() + getPaddingRight()),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight :
                        (height + getPaddingTop() + getPaddingBottom()));

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        int viewCount = getChildCount();

        //子View位置标识
        int left = getPaddingLeft();
        int top = getPaddingTop();

        for (int i = 0; i < viewCount; i++) {
            View child = getChildAt(i);

            //若子View不可见，则跳过
            if (child.getVisibility() == View.GONE) continue;

            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            if (lineWidth + childWidth > width - getPaddingRight() - getPaddingLeft()) {
                //换行

                //重置位置标识
                left = getPaddingLeft();
                top += lineHeight;

                //重置当前行宽高记录
                lineWidth = 0;
                lineHeight = childHeight;
            }


            //设置子View位置
            child.layout(left + params.leftMargin, top + params.topMargin,
                    left - params.rightMargin + childWidth,
                    top - params.bottomMargin + childHeight);


            //累加行宽
            lineWidth += childWidth;
            //比较行高
            lineHeight = Math.max(lineHeight, childHeight);

            //更新位置标识
            left += childWidth;
        }


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


}
