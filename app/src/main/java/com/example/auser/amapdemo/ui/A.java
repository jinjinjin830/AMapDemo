package com.example.auser.amapdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.auser.amapdemo.R;


/**
 * Created by Zx on 2016/10/28.
 * 高德地图Demo
 */

public class A extends Activity implements LocationSource, AMapLocationListener {

    MapView mMapView = null;

    private AMap aMap;

    //    声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //    声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    OnLocationChangedListener mListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_layout);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        mMapView.setVisibility(View.INVISIBLE);

        initLocation();
    }
    private void initLocation() {

        //初始化地图变量
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        //设置定位监听
        aMap.setLocationSource(this);

        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());

        //设置定位回调监听
        mLocationClient.setLocationListener(this);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        location1(amapLocation);

    }

    private void location1(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                if(isFirstLoc) {
                    //可在其中解析amapLocation获取相应内容。
                    String city = amapLocation.getCity();
                    Log.d("Zxxxxx","定位"+city);

                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(amapLocation);

                    LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                    MarkerOptions otMarkerOptions = new MarkerOptions();
                    otMarkerOptions.position(latLng);
                    otMarkerOptions.visible(true);//设置可见
                    aMap.addMarker(otMarkerOptions);
                    mMapView.setVisibility(View.VISIBLE);

                    isFirstLoc =false;
                }
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }
}
