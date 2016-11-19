package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.auser.amapdemo.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Zx on 2016/10/28.
 */

public class B_ANR extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_layout);
    }




    public void anrButton(View view) {
        Log.d("Zxxxxx",5/0+"");
    }
}
