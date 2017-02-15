package com.example.auser.amapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.auser.amapdemo.adapter.MainAdapter;
import com.example.auser.amapdemo.ui.A;
import com.example.auser.amapdemo.ui.B;
import com.example.auser.amapdemo.ui.C;
import com.example.auser.amapdemo.ui.D;
import com.example.auser.amapdemo.ui.E;
import com.example.auser.amapdemo.ui.F;
import com.example.auser.amapdemo.ui.G;
import com.umeng.analytics.MobclickAgent;


public class MainActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewGroup view = (ViewGroup) findViewById(R.id.all_demo);
//
//       for (int i = 0;i< view.getChildCount();i++) {
//           view.getChildAt(i).setOnClickListener(this);
//       }

        RecyclerView rv = (RecyclerView) findViewById(R.id.main_rv);
        //1.添加布局管理器
        rv.setLayoutManager(new LinearLayoutManager(this));
        //2.添加适配器
        MainAdapter mainAdapter = new MainAdapter(this);
        rv.setAdapter(mainAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_map :
                Intent intent1 = new Intent(this, A.class);
                startActivity(intent1);
                break;
            case R.id.btn_material :
                Intent intent2 = new Intent(this,B.class);
                startActivity(intent2);
                break;
            case R.id.btn_three:
                Intent intent3 = new Intent(this, C.class);
                startActivity(intent3);
                break;
            case R.id.btn_four:
                Intent intent4 = new Intent(this, D.class);
                startActivity(intent4);
                break;
            case R.id.btn_five:
                Intent intent5 = new Intent(this, E.class);
                startActivity(intent5);
                break;
            case R.id.btn_six:
                Intent intent6 = new Intent(this, F.class);
                startActivity(intent6);
                break;
            case R.id.btn_seven:
                Intent intent7 = new Intent(this, G.class);
                startActivity(intent7);
                break;
        }
    }
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

}
