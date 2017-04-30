package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.auser.amapdemo.R;



/**
 * Created by Administrator on 2016/11/15.
 * 用户基础测试
 */
public class F extends Activity {

    private ImageView iv;

//    @Bind(R.id.btn_)
    public Button btn;

//
//
//    @OnClick(R.id.btn_)
    public void onButtonClick( ) {
        Toast.makeText(this, "onButtonClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_layout);
//        ViewFinder.inject(this);
//        ViewInjector.injectView(this);

//        btn.setText("成功了吗");
    }

}
