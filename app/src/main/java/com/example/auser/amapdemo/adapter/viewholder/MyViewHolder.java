package com.example.auser.amapdemo.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.view.TimeTextView;


class MyViewHolder extends RecyclerView.ViewHolder {
    TimeTextView tv;
    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TimeTextView) itemView.findViewById(R.id.time);
    }
}