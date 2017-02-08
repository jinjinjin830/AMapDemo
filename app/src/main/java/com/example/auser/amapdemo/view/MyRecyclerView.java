package com.example.auser.amapdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.example.auser.amapdemo.R;

/**
 * Created by Administrator on 2016/11/24.
 */

public class MyRecyclerView extends RecyclerView {
    private ImageView ivHeader;

    /**
     * 头部的原始高度
     */
    private int mOriginalHeight;
    float downY = 0;
    Context context;



    public MyRecyclerView(Context context) {
        super(context);
        this.context = context;
        mOriginalHeight=context.getResources().getDimensionPixelOffset(R.dimen.recyclerview_head_height);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mOriginalHeight=context.getResources().getDimensionPixelOffset(R.dimen.recyclerview_head_height);
        this.context = context;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.d("Zxxxxx","deltaY"+deltaY);
        Log.d("Zxxxxx","deltaY"+0);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    /**
     * 还原头部高度
     */
    public void amimateUP() {

        // 头部高度发生了改变才需要执行回弹动画
        System.out.println("-------------------amimateUP");
        final ViewGroup.LayoutParams param =
                ivHeader.getLayoutParams();

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

    /**
     * 设置视差控件，并获取高度
     */
    public void setParalaxView(View header) {

        this.ivHeader = (ImageView)header ;
//        // 监听布局的变化: 控件的隐藏或显示，添加或删除，输入法弹出与隐藏
//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mOriginalHeight = ivHeader.getHeight();
//                // 取消监听
//                ivHeader.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//            }
//        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:

                // 头部高度发生了改变才需要执行回弹动画
/*                if (ivHeader.getLayoutParams().height != mOriginalHeight) {
                    amimateUP();
                }*/

                break;
        }
        return super.onTouchEvent(ev);
    }


}
