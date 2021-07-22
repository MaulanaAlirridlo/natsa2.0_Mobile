package com.natsa.natsa20_mobile.server.process.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.natsa.natsa20_mobile.activity.BackActivity;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.model.user.update_profile.Request;
import com.natsa.natsa20_mobile.model.user.update_profile.Response;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class UpdateProfile {
    public void uploadDataProfile(Activity activity,
                                  RequestBody name,
                                  RequestBody email,
                                  RequestBody username,
                                  RequestBody ktp,
                                  RequestBody no_hp,
                                  MultipartBody.Part photo
    ) {
        RetrofitBuilder.endPoint().updateProfile("PUT", "Bearer " + Preferences.getToken(activity),
                name, email, username, ktp, no_hp, photo
        ).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, response.body().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                    Response res = response.body();
                    User user = res.getUser();
                    Preferences.setUser(activity, user.getId(), user.getName(), user.getEmail(),
                            user.getUsername(), user.getKtp(), user.getNo_hp(),
                            user.getProfile_photo_url());
                    Intent i = new Intent(activity, BackActivity.class);
                    i.putExtra("page", "account");
                    i.putExtra("accountPage", "Profile");
                    activity.startActivity(i);
                    activity.finish();
                } else {
                    Toast.makeText(activity, response.code() == 422 ? "Silahkan cek ulang data" :
                            response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
