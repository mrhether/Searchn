package com.markhetherington.searchn.network;

import com.markhetherington.searchn.network.model.Shot;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by markhetherington on 15-08-16.
 */
public interface DribbbleService {

    String ACCESS_TOKEN = "0e67af688b5cba3214219d3151e409c661ea05f3c07449649418b7b84b7f1599";

    @GET("shots?access_token=" + ACCESS_TOKEN)
    Call<List<Shot>> shots(@Query("page") Integer page);

    @GET("shots?type=animated&access_token=" + ACCESS_TOKEN)
    Call<List<Shot>> animatedShots(@Query("page") Integer page);

    @GET("shots/{id}?access_token=" + ACCESS_TOKEN)
    Call<Shot> shot(@Path("id") String shotId);
}
