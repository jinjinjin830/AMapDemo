package com.example.auser.amapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.auser.amapdemo.ui.A_AMap;
import com.example.auser.amapdemo.ui.B_ANR;
import com.example.auser.amapdemo.ui.C_ListView_Parallax;
import com.example.auser.amapdemo.ui.D_ProgressDemo;
import com.example.auser.amapdemo.ui.E_RecyclerView_Stickiness;
import com.example.auser.amapdemo.ui.F_TestDemo;

/**
 * 实现思路:
 *      遮挡!将IV添加到LV的头布局中.在用相对布局或者帧布局将IV用include标签遮挡在LV的头部.
 *      然后,通过计算LV的滑动量,我们为这个include标签中的IV进行操作.
 *      Activity
 */
public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup view = (ViewGroup) findViewById(R.id.all_demo);

       for (int i = 0;i< view.getChildCount();i++) {
           view.getChildAt(i).setOnClickListener(this);
       }


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_map :
                Intent intent1 = new Intent(this, A_AMap.class);
                startActivity(intent1);
                break;
            case R.id.btn_material :
                Intent intent2 = new Intent(this,B_ANR.class);
                startActivity(intent2);
                break;
            case R.id.btn_three:
                Intent intent3 = new Intent(this, C_ListView_Parallax.class);
                startActivity(intent3);
                break;
            case R.id.btn_four:
                Intent intent4 = new Intent(this, D_ProgressDemo.class);
                startActivity(intent4);
                break;
            case R.id.btn_five:
                Intent intent5 = new Intent(this, E_RecyclerView_Stickiness.class);
                startActivity(intent5);
                break;
            case R.id.btn_six:
                Intent intent6 = new Intent(this, F_TestDemo.class);
                startActivity(intent6);
                break;
        }
    }

}
