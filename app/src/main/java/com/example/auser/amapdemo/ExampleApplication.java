package com.example.auser.amapdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-2-13.
 */

public class ExampleApplication extends Application {
    //记录位置与是否被初始化过.
    public static final HashMap<Integer, Boolean> flags = new HashMap<>();
    //记录位置与相应的初始化时间
    public static final HashMap<Integer, Long> originTimes = new HashMap<>();
    public static boolean updateFlag = true;

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...


        for (int i = 0; i <= 1000; i++) {
            flags.put(i, false);
        }
    }
}
