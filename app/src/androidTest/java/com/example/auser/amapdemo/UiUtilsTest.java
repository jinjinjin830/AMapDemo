package com.example.auser.amapdemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Administrator on 2017-3-1.
 */
@RunWith(JUnit4.class)
public class UiUtilsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getScreenWidth() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        System.out.println(appContext.getResources().getDisplayMetrics().widthPixels+"===");
    }

}