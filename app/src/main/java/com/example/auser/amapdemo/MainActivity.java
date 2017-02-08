package com.example.auser.amapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.auser.amapdemo.ui.A_AMap;
import com.example.auser.amapdemo.ui.B_ANR;
import com.example.auser.amapdemo.ui.C_ListView_Parallax;
import com.example.auser.amapdemo.ui.D_ProgressDemo;
import com.example.auser.amapdemo.ui.E_RecyclerView_Stickiness;
import com.example.auser.amapdemo.ui.F_TestDemo;
import com.example.auser.amapdemo.ui.G_RecyclerView_Parallax_Stick;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup view = (ViewGroup) findViewById(R.id.all_demo);

       for (int i = 0;i< view.getChildCount();i++) {
           view.getChildAt(i).setOnClickListener(this);
       }

        retrofitTest();
    }

//    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    public static final String BASE_URL = "http://172.16.1.50/web/client/tag/category/";

    private void retrofitTest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .build();

        //获取接口实例
        MovieService  movieService = retrofit.create(MovieService.class);
            Call<ResponseBody> call = movieService.getDatas("40379db889f9124819228947faaeb1f7", Long.decode("12"));
            call.enqueue(new Callback<ResponseBody>() {
                @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("ZX",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("ZX","失败");
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_map :
                Intent intent1 = new Intent(this, A_AMap.class);
                startActivity(intent1);
                break;
            case R.id.btn_material :
                Intent intent2 = new Intent(this,B_ANR.class);
                startActivity(intent2);
                break;
            case R.id.btn_three:
                Intent intent3 = new Intent(this, C_ListView_Parallax.class);
                startActivity(intent3);
                break;
            case R.id.btn_four:
                Intent intent4 = new Intent(this, D_ProgressDemo.class);
                startActivity(intent4);
                break;
            case R.id.btn_five:
                Intent intent5 = new Intent(this, E_RecyclerView_Stickiness.class);
                startActivity(intent5);
                break;
            case R.id.btn_six:
                Intent intent6 = new Intent(this, F_TestDemo.class);
                startActivity(intent6);
                break;
            case R.id.btn_seven:
                Intent intent7 = new Intent(this, G_RecyclerView_Parallax_Stick.class);
                startActivity(intent7);
                break;
        }
    }
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

}
