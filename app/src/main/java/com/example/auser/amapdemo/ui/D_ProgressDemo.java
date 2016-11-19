package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.view.ProcessImageView;

/**
 * Created by Zx on 2016/11/9.
 */

public class D_ProgressDemo extends Activity{
    ProcessImageView processImageView =null;

    private final int SUCCESS=0;
    int progress=0;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    Toast.makeText(D_ProgressDemo.this, "图片上传完成", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.d_layout);


        processImageView=(ProcessImageView) findViewById(R.id.image);
        //模拟图片上传进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(progress==100){//图片上传完成
                        handler.sendEmptyMessage(SUCCESS);
                        return;
                    }
                    progress++;
                    processImageView.setProgress(progress);
                    try{
                        Thread.sleep(200);  //暂停0.2秒
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
