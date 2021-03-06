package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.auser.amapdemo.base.Constant;
import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.view.MyListView;
import com.umeng.analytics.MobclickAgent;

import java.util.Arrays;

/**
 * Created by Zx on 2016/10/30.
 *
 * 大概的实现思路.
 * 1.放大的效果请参照MyListView.
 * 2.粘性控件的思路是:在最顶部事先藏了一个View(这个一开始是隐藏的),
 *      然后ListView中也有一个和隐藏View一模一样的View(通过addHeader添加.)
 *      通过监听ListView滑动距离,控制这个事先隐藏的View的show和hide.
 * 3.透明状态栏的思路是:
 *      a.
 */

public class C extends Activity  {
    private MyListView listView;

    int toolbarHeight;

    private SparseArray recordSp = new SparseArray(0);
    private int mCurrentfirstVisibleItem = 0;


    private ImageView stickView;//这个就是事先隐藏的View

    int flag= 0;//标记位


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.c_layout);

        toolbarHeight = getResources().getDimensionPixelOffset(R.dimen.toolbar_height);

        initListView();

        initStickinessView();
    }


    private void initListView() {

        listView = (MyListView) findViewById(R.id.c_lv);

        final View header = LayoutInflater.from(this).inflate(R.layout.c_list_header, listView, false);

        final FrameLayout ivHeader = (FrameLayout) header.findViewById(R.id.iv_header);
        // 添加列表头部，需要放在setAdapter前面，否则可以导致报错
        listView.addHeaderView(header);

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(Constant.LIST_DATAS2)));
        listView.setParalaxView(ivHeader);

        stickView = (ImageView) findViewById(R.id.stickView);
    }


    private void initStickinessView() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                initMeasureScrollY(view, firstVisibleItem);

                //图片的高度减去toolbar的高度
                int showStickinessFlag = getResources().getDimensionPixelOffset(R.dimen.recyclerview_head_height) - toolbarHeight;

                if (getScrollY() >= showStickinessFlag && showStickinessFlag >= 0) {
                    if(flag == 0) {
                        stickView.scrollBy(0,showStickinessFlag);
                        stickView.setVisibility(View.VISIBLE);
                        flag++;
                    }
                } else {
                    //否则,将toolbarLayout设置为透明.
                    stickView.scrollTo(0,0);
                    stickView.setVisibility(View.INVISIBLE);
                    flag=0;
                }
            }
        });
    }



    //以下代码都是为了计算出ListView的滑动距离,与逻辑无关,而且存在Bug,建议使用RecyclerVIew.
    private void initMeasureScrollY(AbsListView view, int firstVisibleItem) {
        mCurrentfirstVisibleItem = firstVisibleItem;
        View firstView = view.getChildAt(0);
        if (null != firstView) {
            ItemRecod itemRecord = (ItemRecod) recordSp.get(firstVisibleItem);
            if (null == itemRecord) {
                itemRecord = new ItemRecod();
            }
            itemRecord.height = firstView.getHeight();
            itemRecord.top = firstView.getTop();
            recordSp.append(firstVisibleItem, itemRecord);
        }
    }

    private int getScrollY() {
        int height = 0;
        for (int i = 0; i < mCurrentfirstVisibleItem; i++) {
            ItemRecod itemRecod = (ItemRecod) recordSp.get(i);
            height += itemRecod.height;
        }
        ItemRecod itemRecod = (ItemRecod) recordSp.get(mCurrentfirstVisibleItem);
        if (null == itemRecod) {
            itemRecod = new ItemRecod();
        }
        return height - itemRecod.top;
    }

    class ItemRecod {
        int height = 0;
        int top = 0;
    }

}
