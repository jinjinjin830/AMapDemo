package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.auser.amapdemo.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class F_TestDemo extends Activity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_layout);

        iv = (ImageView) findViewById(R.id.f_iv);
    }

    public void goScroll(View view) {
        iv.scrollBy(0,100);
    }
}
