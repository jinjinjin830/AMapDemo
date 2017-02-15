package com.example.auser.amapdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.view.MyRecyclerView;

import static com.example.auser.amapdemo.base.Constant.LIST_DATAS2;

/**
 * Created by Administrator on 2016/11/19.
 */

public class G_RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = null;
//        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0://头控件
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.g_headers, parent, false);
                RecyclerView.ViewHolder holder = new G_RecyclerView_Adapter.G_Head_ViewHolder(view);

                ImageView header = (ImageView) parent.findViewById(R.id.iv_g_header);
                ((MyRecyclerView)parent).setParalaxView(header);

                return holder;
            case 1://其它Item
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.g_item, parent, false);
                RecyclerView.ViewHolder holder1 = new G_RecyclerView_Adapter.G_Item_ViewHolder(view1);
                return holder1;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case 0:
                break;
            case 1:
                ((G_RecyclerView_Adapter.G_Item_ViewHolder) holder).tv.setText(LIST_DATAS2[position]);
                break;

        }


    }

    @Override
    public int getItemCount() {
        return LIST_DATAS2.length;
    }

    class G_Head_ViewHolder extends RecyclerView.ViewHolder {
        public G_Head_ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class G_Item_ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public G_Item_ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.g_tv);
        }
    }



}
