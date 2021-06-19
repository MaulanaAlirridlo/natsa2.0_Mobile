package com.natsa.natsa20_mobile.server;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static RouteAPI endPoint(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RouteAPI.class);
    }
}
