package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.auser.amapdemo.R;

import static com.example.auser.amapdemo.Constant.LIST_DATAS2;

/**
 * Created by Zx on 2016/11/10.
 * 1.RecyclerView头布局和脚布局
 * 2.自定义粘性控件结合PopupWindow.
 */

public class E_RecyclerView_Stickiness extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_layout);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.e_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new EAdapter());
    }

    public class EAdapter extends RecyclerView.Adapter<EViewHolder> {

        @Override
        public EViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new EViewHolder(LayoutInflater.from(E_RecyclerView_Stickiness.this).inflate(R.layout.e_item,parent,false));
        }

        @Override
        public void onBindViewHolder(EViewHolder holder, int position) {
            holder.tv.setText(LIST_DATAS2[position]);
        }

        @Override
        public int getItemCount() {
            return LIST_DATAS2.length;
        }
    }

    public class EViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public EViewHolder(View itemView) {
            super(itemView);
             tv = (TextView) itemView.findViewById(R.id.e_item_tv);
        }
    }
}
