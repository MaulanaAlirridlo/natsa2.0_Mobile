package com.natsa.natsa20_mobile.server.process.User;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.model.user.GetLoginUser;
import com.natsa.natsa20_mobile.model.user.GetUserRes;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUser {

    private final static List<User> userData = new ArrayList<>();

    public static List<User> getUserData() {
        return userData;
    }

    public static void setUserData(List<User> user, ProductAdapter productAdapter) {
        userData.clear();
        userData.addAll(user);
        productAdapter.notifyDataSetChanged();
    }

    public void getUserFromApi(Integer id, ProductAdapter productAdapter) {
        RetrofitBuilder.endPoint().getUser(id)
                .enqueue(new Callback<GetUserRes>() {
                    @Override
                    public void onResponse(Call<GetUserRes> call, Response<GetUserRes> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<User> user = response.body().getUsers();
                            setUserData(user, productAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserRes> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getLoginUserFromApi(Activity activity,
                                    View view,
                                    ImageView photoProfile,
                                    TextView name,
                                    TextView username,
                                    TextView email,
                                    TextView ktp,
                                    TextView noHp) {
        RetrofitBuilder.endPoint().getLoginUser("Bearer " + Preferences.getToken(activity))
                .enqueue(new Callback<GetLoginUser>() {
                    @Override
                    public void onResponse(Call<GetLoginUser> call, Response<GetLoginUser> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            User data = response.body().getUser();
                            Preferences.setUser(activity, data.getId(), data.getName(),
                                    data.getEmail(), data.getUsername(), data.getKtp(),
                                    data.getNo_hp(),
                                    data.getProfile_photo_url());
                            new GlideLoader().glideImageRoundedLoader(activity, view, photoProfile, data.getProfile_photo_url());
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

    public void getLoginUserFromApiWithoutSetView(Context context) {
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
