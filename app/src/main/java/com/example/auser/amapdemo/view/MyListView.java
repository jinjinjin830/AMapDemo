package com.example.auser.amapdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by Auser on 2016/10/25.
 */

public class MyListView extends ListView {

    private ImageView ivHeader;

    /** 头部的原始高度 */
    private int mOriginalHeight;



    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    // 顶部到头往下拉，底部到头往上拉时调用
    // deltaY： 顶部到头往下拉为负值, 底部到头往上拉为正值
    // isTouchEvent: 按下滑动为true， 照惯性滑动为false
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        System.out.println("-----------deltaY: " + deltaY
                + "    scrollY: " + scrollY + "       "
                + "    scrollRangeX:" + scrollRangeX
                + "    isTouchEvent: " + isTouchEvent);

        // 顶部到头往下拉时，改变头部ImageView的高度
        if (deltaY < 0 && isTouchEvent) {
            RelativeLayout.LayoutParams param =
                    (RelativeLayout.LayoutParams) ivHeader.getLayoutParams();

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
        System.out.println("-------------------amimateUP");
        final RelativeLayout.LayoutParams param =
                (RelativeLayout.LayoutParams) ivHeader.getLayoutParams();

//		param.height = mOriginalHeight;
//		ivHeader.setLayoutParams(param);

        // 开始值
//		final int start = param.height;
//		final int end = mOriginalHeight;
//		ValueAnimator animator = ValueAnimator.ofInt(100);
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				float percent = animation.getAnimatedFraction();
//				System.out.println("-----------percent: " + percent);
//				int value = (int) (start + (end - start) * percent);
//				param.height = value;
//				ivHeader.setLayoutParams(param);
//			}
//		});
//		animator.setDuration(300);
//		animator.start();

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
    public void setParalaxView(ImageView header) {
        this.ivHeader = header;
        // 监听布局的变化: 控件的隐藏或显示，添加或删除，输入法弹出与隐藏
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mOriginalHeight = ivHeader.getHeight();
                int measuredHeight = ivHeader.getMeasuredHeight();
                System.out.println("----------height: " + mOriginalHeight
                        + "    measuredHeight: " + measuredHeight);

                // 取消监听
                ivHeader.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }
}
