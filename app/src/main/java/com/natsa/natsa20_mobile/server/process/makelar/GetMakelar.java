package com.natsa.natsa20_mobile.server.process.makelar;

import android.content.Context;
import android.util.Log;

import com.natsa.natsa20_mobile.adapter.MakelarAdapter;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.MakelarResponse;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMakelar {

    private final static List<User> makelarData = new ArrayList<>();
    private final static List<UserSocialMedia> makelarSocialMediaDataList = new ArrayList<>();
    private final static List<Data> riceFieldDataList = new ArrayList<>();

    public static List<User> getMakelarData() {
        return makelarData;
    }

    public static List<UserSocialMedia> getMakelarSocialMediaDataList() {
        return makelarSocialMediaDataList;
    }

    public static List<Data> getRiceFieldDataList() {
        return riceFieldDataList;
    }

    public static void setData(User makelar, List<UserSocialMedia> makelarSocialMedia, List<Data> riceField,
                               MakelarAdapter makelarAdapter, ProductsAdapter productsAdapter) {
        makelarData.clear();
        makelarSocialMediaDataList.clear();
        riceFieldDataList.clear();
        makelarData.add(makelar);
        makelarSocialMediaDataList.addAll(makelarSocialMedia);
        riceFieldDataList.addAll(riceField);
        makelarAdapter.notifyDataSetChanged();
        productsAdapter.notifyDataSetChanged();
    }

    public void getMakelarFromApi(Context context, Integer id, MakelarAdapter makelarAdapter, ProductsAdapter productsAdapter) {
        RetrofitBuilder.endPoint().getMakelar("Bearer "+ Preferences.getToken(context), id)
                .enqueue(new Callback<MakelarResponse>() {
                    @Override
                    public void onResponse(Call<MakelarResponse> call, Response<MakelarResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            MakelarResponse res = response.body();
                            List<Data> riceField = res.getRiceField();
                            setData(res.getUser(), res.getMakelarSocialMedias(), riceField
                                    , makelarAdapter, productsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<MakelarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
