package com.example.auser.amapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.auser.amapdemo.ui.A_AMap;
import com.example.auser.amapdemo.ui.B_MaterialDesign;
import com.example.auser.amapdemo.ui.C_ListView_Parallax;
import com.example.auser.amapdemo.view.MyListView;

/**
 * 实现思路:
 *      遮挡!将IV添加到LV的头布局中.在用相对布局或者帧布局将IV用include标签遮挡在LV的头部.
 *      然后,通过计算LV的滑动量,我们为这个include标签中的IV进行操作.
 *      Activity
 */
public class MainActivity extends Activity implements View.OnClickListener{

    MyListView listView;

    private int curScrollDist;

    private RelativeLayout.LayoutParams relaParams;
    private View headerLayout;
    private ImageView ivHeader;
    private View mRealView;
    private FrameLayout mFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnMap = (Button) findViewById(R.id.btn_map);
        btnMap.setOnClickListener(this);

        Button btnMaterial = (Button) findViewById(R.id.btn_material);
        btnMaterial.setOnClickListener(this);

        Button btnThree = (Button) findViewById(R.id.btn_three);
        btnThree.setOnClickListener(this);

    }

//    private void initView() {
//            EasyFlowLayout flowLayout = (EasyFlowLayout) findViewById(R.id.flow);
//        String[] listDatas2 = Constant.LIST_DATAS2;
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(5,5,5,5);
//        for(int i = 0;i < listDatas2.length;i++) {
//            TextView tv = new TextView(this);
//
//            tv.setText(listDatas2[i]);
//            tv.setTextSize(16);
//            tv.setBackgroundResource(R.drawable.tv_bg);
//
//            flowLayout.addView(tv);
//            tv.setLayoutParams(layoutParams);
//        }
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_map :
                Intent intent1 = new Intent(this, A_AMap.class);
                startActivity(intent1);
                break;
            case R.id.btn_material :
//                initMaterial();
                Intent intent2 = new Intent(this,B_MaterialDesign.class);
                startActivity(intent2);
                break;
            case R.id.btn_three:
                Intent intent3 = new Intent(this, C_ListView_Parallax.class);
                startActivity(intent3);
                break;
        }
    }

    private void initMaterial() {

    }
}
