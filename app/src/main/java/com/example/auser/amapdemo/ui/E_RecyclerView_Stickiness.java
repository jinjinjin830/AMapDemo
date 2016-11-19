package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.adapter.E_RecyclerView_Adapter;

/**
 * Created by Zx on 2016/11/10.
 * 1.RecyclerView头布局和脚布局
 * 2.自定义粘性控件结合PopupWindow.
 */

public class E_RecyclerView_Stickiness extends Activity {

    private RecyclerView rv;
    int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_layout);
        initRecyclerView();
        initStickiness();
    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.e_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new E_RecyclerView_Adapter());
    }

    private void initStickiness() {
        final TextView stickiness_TextView = (TextView) findViewById(R.id.e_layout_stickiness_tv);

        //滑动监听
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //Log.d("Zxxxxx","dy"+dy);//往上滑是正数,往下滑是负数
                int headHeight = getResources().getDimensionPixelOffset(R.dimen.recyclerview_head_height);

                offset+=dy;
                //Log.d("Zxxxxx","offset"+offset);
                if(offset>headHeight) {
                    stickiness_TextView.setVisibility(View.VISIBLE);
                }else {
                    stickiness_TextView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
