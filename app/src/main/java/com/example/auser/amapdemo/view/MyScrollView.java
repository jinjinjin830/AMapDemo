package com.example.auser.amapdemo.view;


import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.ScrollView;

import com.example.auser.amapdemo.R;

/**
 * Created by Auser on 2016/10/28.
 */

public class MyScrollView extends ScrollView {
    private ImageView ivHeader;

    /**
     * 头部的原始高度
     */
    private int mOriginalHeight;
    private FrameLayout mFm;


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {


        // 顶部到头往下拉时，改变头部ImageView的高度
        if (deltaY < 0 && isTouchEvent) {
            ViewGroup.LayoutParams param = ivHeader.getLayoutParams();

            // 头部不能大于最大高度
            if (param.height <= 400) {
                param.height = param.height + Math.abs(deltaY) / 2;
                ivHeader.setLayoutParams(param);
            }
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
                isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ivHeader = (ImageView) findViewById(R.id.iv_c_header);
                mOriginalHeight = ivHeader.getMeasuredHeight();
                Log.d("Zxxxxx",ivHeader.getBottom()+"点击时");

                mFm = (FrameLayout) findViewById(R.id.fr_user);
                break;
            case MotionEvent.ACTION_MOVE:

                float x = ivHeader.getY();

                break;
            case MotionEvent.ACTION_UP:

                // 头部高度发生了改变才需要执行回弹动画
                if (ivHeader.getLayoutParams().height != mOriginalHeight) {
                    amimateUP();
                }

                break;
        }

        return super.onTouchEvent(ev);
    }


    /**
     * 还原头部高度
     */
    private void amimateUP() {
        final ViewGroup.LayoutParams param =  ivHeader.getLayoutParams();
        final int start = param.height;
        final int end = mOriginalHeight;
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                param.height = (int) animation.getAnimatedValue(); // 变化值
                ivHeader.setLayoutParams(param);  // 更新头部高度
            }
        });
        animator.setDuration(500);
        // 实现抖动效果的插值器:OvershootInterpolator
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);


        Log.d("Zxxxxx","上:"+t);
        Log.d("Zxxxxx","旧的上:"+oldt);
//
//        if(t>=160) {
////            ViewGroup.LayoutParams layoutParams = mFm.getLayoutParams();
////            layoutParams.width =ViewGroup.LayoutParams.MATCH_PARENT;
////            layoutParams.height = 80;
////            mFm.setLayoutParams(layoutParams);
//
//
//
//
//            FrameLayout.LayoutParams param = (LayoutParams) ivHeader.getLayoutParams();
//            param.height = 80;
//            param.width =ViewGroup.LayoutParams.MATCH_PARENT;
//            param.topMargin =160;
//            ivHeader.setLayoutParams(param);
//        }
    }


    private OverScroller mScroller;

    @Override
    public void computeScroll()
    {
        if (mScroller.computeScrollOffset())
        {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
