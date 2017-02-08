package com.example.auser.amapdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-1-11.
 */

public interface MovieService {

    @POST("hobby")
    Call<ResponseBody> getDatas(@Query("token") String token, @Query("community_id")Long community_id);
}
