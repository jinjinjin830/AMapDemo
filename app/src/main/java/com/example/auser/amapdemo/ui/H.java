package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.auser.amapdemo.R;
import com.example.auser.amapdemo.view.BounceBackViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */

public class H extends Activity {

    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.h_layout);
        BounceBackViewPager viewPager = (BounceBackViewPager) findViewById(R.id.vp);
        LayoutInflater inflater=getLayoutInflater();
        View view1 = inflater.inflate(R.layout.h_pager_a, null);
        View view2 = inflater.inflate(R.layout.h_pager_b,null);
        View view3 = inflater.inflate(R.layout.h_pager_c, null);

        // 将要分页显示的View装入数组中
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);


        MyPagerAdapter pagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
    }

    public class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
