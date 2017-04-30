package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import com.example.auser.amapdemo.MyItemTouchHelper;
import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.adapter.viewholder.G_RecyclerView_Adapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zx on 2016/11/24.
 * 实现:
 *  1.顶部图片下拉放大效果
 *  2.粘性控件效果
 *  3.透明状态栏效果
 */

public class G extends Activity {

    private RecyclerView rv;

    List<String> mDatas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.g_layout);


        mDatas = new ArrayList<>();

        for(int i = 0; i < 50; i++) {
            mDatas.add("上山打老虎"+"----"+i);
        }

        initRecyclerView();

    }

    private void initParallax() {
        ImageView header = (ImageView) rv.findViewById(R.id.iv_g_header);
//        rv.setParalaxView(header);
    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.g_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
         final RecyclerView.Adapter adapter =new G_RecyclerView_Adapter2(mDatas);
        rv.setAdapter(adapter);

        MyItemTouchHelper callback =new MyItemTouchHelper((G_RecyclerView_Adapter2) adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);

//        ((G_RecyclerView_Adapter2) adapter).setOnItemClickListener(new G_RecyclerView_Adapter2.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view) {
//                int childAdapterPosition = rv.getChildAdapterPosition(view);
//                Toast.makeText(G.this, mDatas.get(childAdapterPosition), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
