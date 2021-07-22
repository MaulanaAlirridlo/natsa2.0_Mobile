package com.natsa.natsa20_mobile.server.process.social_media;

import android.content.Context;
import android.util.Log;

import com.natsa.natsa20_mobile.adapter.UserSocialMediaAdapter;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.social_media.GetUserSocialMediaResponse;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserSocialMedia {
    public static List<UserSocialMedia> userSocialMediaList = new ArrayList<>();

    public static List<UserSocialMedia> getUserSocialMediaList() {
        return userSocialMediaList;
    }

    public void setUserSocialMedia(List<UserSocialMedia> userSocialMediaList, UserSocialMediaAdapter adapter) {
        GetUserSocialMedia.userSocialMediaList.clear();
        GetUserSocialMedia.userSocialMediaList.addAll(userSocialMediaList);
        adapter.notifyDataSetChanged();
    }

    public void getUserSocialMediaFromApi(Context context, UserSocialMediaAdapter adapter) {
        RetrofitBuilder.endPoint().getUserSocialMedia("Bearer " + Preferences.getToken(context))
                .enqueue(new Callback<GetUserSocialMediaResponse>() {
                    @Override
                    public void onResponse(Call<GetUserSocialMediaResponse> call, Response<GetUserSocialMediaResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<UserSocialMedia> data = response.body().getUserSocialMedia();
                            Log.d("TAG", "onResponse: "+response.body().getUserSocialMedia());
                            setUserSocialMedia(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserSocialMediaResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
