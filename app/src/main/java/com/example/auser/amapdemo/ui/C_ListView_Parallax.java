package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.auser.amapdemo.Constant;
import com.example.auser.amapdemo.view.MyListView;
import com.example.auser.amapdemo.R;

import java.util.Arrays;

/**
 * Created by Auser on 2016/10/30.
 */

public class C_ListView_Parallax extends Activity{
    private MyListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.c_layout_listview);

        final View header = View.inflate(this, R.layout.c_list_header, null);
        final ImageView ivHeader = (ImageView) header.findViewById(R.id.iv_header);

        listView = (MyListView) findViewById(R.id.c_lv);
        // 添加列表头部，需要放在setAdapter前面，否则可以导致报错
        listView.addHeaderView(header);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(Constant.LIST_DATAS2)));

        listView.setParalaxView(ivHeader);
    }
}
