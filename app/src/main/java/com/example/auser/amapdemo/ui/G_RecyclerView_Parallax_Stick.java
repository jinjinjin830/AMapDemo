package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.adapter.G_RecyclerView_Adapter;
import com.example.auser.amapdemo.view.MyRecyclerView;

/**
 * Created by Zx on 2016/11/24.
 * 实现:
 *  1.顶部图片下拉放大效果
 *  2.粘性控件效果
 *  3.透明状态栏效果
 */

public class G_RecyclerView_Parallax_Stick extends Activity {

    private MyRecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.g_layout);

        initRecyclerView();
//        initParallax();
    }

    private void initParallax() {
        ImageView header = (ImageView) rv.findViewById(R.id.iv_g_header);
        rv.setParalaxView(header);
    }

    private void initRecyclerView() {
        rv = (MyRecyclerView) findViewById(R.id.g_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new G_RecyclerView_Adapter());

        rv.setScrollbarFadingEnabled(true);
        rv.setEnabled(true);
    }
}
