package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private RecyclerView rv;
    int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_layout);
        initRecyclerView();
        initStickiness();
    }

    private void initStickiness() {
        final TextView stickiness_TextView = (TextView) findViewById(R.id.e_layout_stickiness_tv);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                Log.d("Zxxxxx","dy"+dy);//往上滑是正数,往下滑是负数
                int headHeight = getResources().getDimensionPixelOffset(R.dimen.recyclerview_head_height);

                offset+=dy;
                Log.d("Zxxxxx","offset"+offset);
                if(offset>headHeight) {
                    stickiness_TextView.setVisibility(View.VISIBLE);
                }else {
                    stickiness_TextView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.e_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new EAdapter());
    }

    public class EAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
                    view = LayoutInflater.from(E_RecyclerView_Stickiness.this).inflate(R.layout.e_head_view, parent, false);
                    holder = new E_Head_ViewHolder(view);
                    break;
                case 1://粘性控件
                    view = LayoutInflater.from(E_RecyclerView_Stickiness.this).inflate(R.layout.e_stickiness_view, parent, false);
                    holder = new E_Stickiness_ViewHolder(view);
                    break;
                default://其它item
                    view = LayoutInflater.from(E_RecyclerView_Stickiness.this).inflate(R.layout.e_item, parent, false);
                    holder = new E_Item_ViewHolder(view);
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
                    ((E_Item_ViewHolder) holder).tv.setText(LIST_DATAS2[position]);
                    break;
            }


        }

        @Override
        public int getItemCount() {
            return LIST_DATAS2.length;
        }
    }

    public class E_Item_ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public E_Item_ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.e_item_tv);
        }
    }

    public class E_Head_ViewHolder extends RecyclerView.ViewHolder {
        public E_Head_ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class E_Stickiness_ViewHolder extends RecyclerView.ViewHolder {
        public E_Stickiness_ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
