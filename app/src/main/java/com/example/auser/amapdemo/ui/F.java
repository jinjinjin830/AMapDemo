package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.auser.amapdemo.R;

/**
 * Created by Administrator on 2016/11/15.
 * 用户基础测试
 */

public class F extends Activity {

    private ImageView iv;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_layout);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                    Log.d("ZX","__+__");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
