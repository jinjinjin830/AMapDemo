package com.example.auser.amapdemo.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.auser.amapdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */

public class G_RecyclerView_Adapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<String> mDatas ;



    public G_RecyclerView_Adapter2(List<String> mDatas ) {
        this.mDatas = mDatas;
    }

    public void serDatas(List<String> mDatas ) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.g_item, parent, false);
                RecyclerView.ViewHolder holder1 = new G_RecyclerView_Adapter2.G_Item_ViewHolder(view1);
                return holder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((G_RecyclerView_Adapter2.G_Item_ViewHolder) holder).tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void onItemDissmiss(int adapterPosition) {
        String currentDatas =mDatas.remove(adapterPosition-1);
        Log.v("zx",currentDatas);
        notifyItemRemoved(adapterPosition);
    }

    class G_Item_ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public G_Item_ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.g_tv);
        }

    }



}
