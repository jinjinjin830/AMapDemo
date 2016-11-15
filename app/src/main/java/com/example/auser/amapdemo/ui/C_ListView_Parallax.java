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

import com.example.auser.amapdemo.Constant;
import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.view.MyListView;

import java.util.Arrays;

/**
 * Created by Zx on 2016/10/30.
 */

public class C_ListView_Parallax extends Activity {
    private MyListView listView;
    private ImageView paralaxView;

    int toolbarHeight;

    private SparseArray recordSp = new SparseArray(0);
    private int mCurrentfirstVisibleItem = 0;
    private ImageView iv2;
    int flag= 0;



    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition;// 标记上次滑动位置

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
        //获取视差控件的ImageView,后面要用到.
        paralaxView = (ImageView) ivHeader.findViewById(R.id.iv_c_header);

        //将上一步的图片,局部抽取之后,设置为这个布局的背景,达到粘性效果.
        iv2 = (ImageView) findViewById(R.id.iv2);
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

                //TODO 该方法可以优化,减少调用次数
                if (getScrollY() >= showStickinessFlag && showStickinessFlag >= 0) {
                    if(flag == 0) {
                        iv2.scrollBy(0,showStickinessFlag);
                        iv2.setVisibility(View.VISIBLE);
                        flag++;
                    }

                } else {
                    //否则,将toolbarLayout设置为透明.
                    iv2.scrollTo(0,0);
                    iv2.setVisibility(View.INVISIBLE);
                    flag=0;
                }
            }
        });
    }

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
