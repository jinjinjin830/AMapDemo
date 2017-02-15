package com.example.auser.amapdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.auser.amapdemo.R;

import static com.example.auser.amapdemo.base.Constant.ALL_DEMO_CLASS;
import static com.example.auser.amapdemo.base.Constant.ALL_DEMO_STRING;

/**
 * Created by Administrator on 2017-2-14.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Context context;
    public MainAdapter(Context context){
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        MainViewholder mainViewholder = new MainViewholder(view);
        return mainViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MainViewholder)holder).button.setText(ALL_DEMO_STRING[position]);
        ((MainViewholder)holder).button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context,ALL_DEMO_CLASS[position]));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ALL_DEMO_STRING.length;
    }


    class MainViewholder extends RecyclerView.ViewHolder {
        public TextView button;
        public MainViewholder(View itemView) {
            super(itemView);
            button = (TextView) itemView.findViewById(R.id.main_tv);
        }
    }
}
