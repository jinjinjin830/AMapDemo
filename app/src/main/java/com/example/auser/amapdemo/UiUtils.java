package com.example.auser.amapdemo;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017-3-1.
 */

public class UiUtils {

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;

    }
}
