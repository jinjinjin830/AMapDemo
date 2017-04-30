package com.example.auser.amapdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

public class BounceBackViewPager extends ViewPager {
    private final static String TAG = BounceBackViewPager.class.getSimpleName();
    //摩擦系数
    private static final float RATIO = 0.5f;
    private float srcX = 0;
    private float mTouchSlop = 5f;
    private boolean isMoved;
    private int mItemCount;
    private int mCurrentPos;
    private Rect mSrcRect;
    private boolean firstTime;

    public BounceBackViewPager(Context context) {
        this(context, null);
    }

    public BounceBackViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBounceBackViewPager();
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private void initBounceBackViewPager() {
        mItemCount = 0;
        mCurrentPos = 0;
        firstTime = true;
        isMoved = false;
        mSrcRect = new Rect();
    }

    //获取viewpager的位置，防止被覆盖，数据，只取一次数据
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (firstTime) {
            mSrcRect.left = l;
            mSrcRect.top = t;
            mSrcRect.right = r;
            mSrcRect.bottom = b;
            Log.i(TAG, mSrcRect.left + " / " + mSrcRect.top
                    + "  /  " + mSrcRect.right + "  /  " + mSrcRect.bottom);
            firstTime = false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                srcX = ev.getX();
                mCurrentPos = getCurrentItem();
                if (getAdapter() != null) {
                    mItemCount = getAdapter().getCount();
                }
                break;
            //松手后回弹，执行回弹动画
            case MotionEvent.ACTION_UP:
                BounceMoveBack();
                isMoved = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float mMoveLength = ev.getX() - srcX;
                srcX = ev.getX();
                //以下几个判断，是为了保证除去两个的情况和一个的情况下
                //其余的项没有动画
                if (mItemCount == 1) {
                    if (Math.abs(mMoveLength) > mTouchSlop) {
                        Move(mMoveLength);
                    }
                } else if (mItemCount >= 2) {
                    if (mCurrentPos == 0 && mMoveLength > mTouchSlop) {
                        Move(mMoveLength);
                    } else if (mCurrentPos == mItemCount - 1
                            && mMoveLength < -mTouchSlop) {
                        Move(mMoveLength);
                    } else {
                        isMoved = false;
                    }
                } else {
                    isMoved = false;
                }
                if (isMoved) {
                    return true; //事件消费了
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
    public void Move(float offset) {
        isMoved = true;
        offsetLeftAndRight((int) (offset * RATIO));
    }
    public void BounceMoveBack() {
        if (!mSrcRect.isEmpty())
        {
            TranslateAnimation animation = new TranslateAnimation(getLeft(), mSrcRect.left, 0, 0);
            animation.setInterpolator(new OvershootInterpolator(4));
            animation.setDuration(300);
            startAnimation(animation);
            layout(mSrcRect.left, mSrcRect.top, mSrcRect.right, mSrcRect.bottom);
        }
    }
}