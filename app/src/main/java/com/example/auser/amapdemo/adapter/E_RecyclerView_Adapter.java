package com.example.auser.amapdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.auser.amapdemo.R;

import static com.example.auser.amapdemo.base.Constant.LIST_DATAS2;

/**
 * Created by Administrator on 2016/11/19.
 */

public class E_RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0://头控件
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.e_head_view, parent, false);
                holder = new E_RecyclerView_Adapter.E_Head_ViewHolder(view);
                break;
            case 1://粘性控件
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.e_stickiness_view, parent, false);
                holder = new E_RecyclerView_Adapter.E_Stickiness_ViewHolder(view);
                break;
            default://其它item
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.e_item, parent, false);
                holder = new E_RecyclerView_Adapter.E_Item_ViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case 0:
                break;
            case 1:
                break;
            default:
                ((E_RecyclerView_Adapter.E_Item_ViewHolder) holder).tv.setText(LIST_DATAS2[position]);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return LIST_DATAS2.length;
    }


     class E_Item_ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public E_Item_ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.e_item_tv);
        }
    }

     class E_Head_ViewHolder extends RecyclerView.ViewHolder {
        public E_Head_ViewHolder(View itemView) {
            super(itemView);
        }
    }

     class E_Stickiness_ViewHolder extends RecyclerView.ViewHolder {
        public E_Stickiness_ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
