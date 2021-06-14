package com.maulana.natsa20_mobile.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static Retrofit endPoint(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
