package com.natsa.natsa20_mobile.server.process.User;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.user.GetLoginUser;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUser {
    public void getUserFromApi(Context context,
                               View view,
                               ImageView photoProfile,
                               TextView name,
                               TextView username,
                               TextView email,
                               TextView ktp,
                               TextView noHp) {
        RetrofitBuilder.endPoint().getLoginUser("Bearer " + Preferences.getToken(context))
                .enqueue(new Callback<GetLoginUser>() {
                    @Override
                    public void onResponse(Call<GetLoginUser> call, Response<GetLoginUser> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            User data = response.body().getUser();
                            Preferences.setUser(context, data.getId(), data.getName(),
                                    data.getEmail(), data.getUsername(), data.getKtp(),
                                    data.getNo_hp(),
                                    data.getProfile_photo_url());
                            new GlideLoader().glideLoader(view, photoProfile, data.getProfile_photo_url());
                            name.setText(data.getName());
                            username.setText(data.getUsername());
                            email.setText(data.getEmail());
                            ktp.setText(data.getKtp());
                            noHp.setText(data.getNo_hp());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetLoginUser> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getUserFromApiWithoutSetView(Context context) {
        RetrofitBuilder.endPoint().getLoginUser("Bearer " + Preferences.getToken(context))
                .enqueue(new Callback<GetLoginUser>() {
                    @Override
                    public void onResponse(Call<GetLoginUser> call, Response<GetLoginUser> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            User data = response.body().getUser();
                            Preferences.setUser(context, data.getId(), data.getName(),
                                    data.getEmail(), data.getUsername(), data.getKtp(),
                                    data.getNo_hp(),
                                    data.getProfile_photo_url());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetLoginUser> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
