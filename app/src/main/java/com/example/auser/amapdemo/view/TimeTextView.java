package com.example.auser.amapdemo.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.auser.amapdemo.ExampleApplication;

import static com.example.auser.amapdemo.ExampleApplication.flags;
import static com.example.auser.amapdemo.ExampleApplication.originTimes;

/**
 * create by zhu 2017/2/8
 * 倒计时
 */
public class TimeTextView extends TextView implements Runnable {
    private long mday, mhour, mmin, msecond;//天，小时，分钟，秒
    private boolean run = false; //是否启动了
    private Handler handler = new Handler();
    private long workTime = 0;
    boolean updateFlag = true;

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TimeTextView(Context context) {
        super(context);
    }

    public void setOriginTimes(long times, int position) {
        //
        if (flags.get(position) == false) {
            //第一次对控件设置时间
            this.workTime = times;
            //存储到时间集合
            originTimes.put(position, times);
            flags.put(position, true);
        } else {
            this.workTime = originTimes.get(position);
        }

        //开启修改时间集合中的数据
        if (ExampleApplication.updateFlag) {
            updateTimeList();
        }

        ExampleApplication.updateFlag = false;

        mday = workTime / 24 / 60 / 60 / 1000;
        mhour = (workTime - mday * 24 * 60 * 60 * 1000) / 60 / 60 / 1000;
        mmin = (workTime - mday * 24 * 60 * 60 * 1000 - mhour * 60 * 60 * 1000) / 60 / 1000;
        msecond = (workTime - mday * 24 * 60 * 60 * 1000 - mhour * 60 * 60 * 1000 - mmin * 60 * 1000) / 1000;
    }

    private void updateTimeList() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (Integer integer : originTimes.keySet()) {
                    originTimes.put(integer, (originTimes.get(integer) - 1000));
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    /**
     * 倒计时计算
     */
    private void ComputeTime() {
        msecond--;
        workTime = workTime - 1000;
        if (workTime > 0) {
            if (msecond < 0) {
                mmin--;
                msecond = 59;
                if (mmin < 0) {
                    mmin = 59;
                    mhour--;
                    if (mhour < 0) {
                        // 倒计时结束，一天有24个小时
                        mhour = 23;
                        mday--;

                    }
                }
            }
        } else {
            handler.removeCallbacks(this);
        }
    }

    public boolean isRun() {
        return run;
    }

    public void stopRun() {
        this.run = false;
    }

    public void beginRun() {
        if (run) {
            handler.removeCallbacks(this);
        }
        this.run = true;
        run();
    }

    public void setRun(boolean isRun) {
        this.run = isRun;
    }

    @Override
    public void run() {
        ComputeTime();
        if (workTime > 0) {
            //标示已经启动
            if (run) {
                String strTime = mday + "天:" + mhour + "小时:" + mmin + "分钟:" + msecond + "秒";
                this.setText(strTime);
                handler.postDelayed(this, 1000);
            } else {
                handler.removeCallbacks(this);
            }
        } else {
            handler.removeCallbacks(this);
        }

    }
}



