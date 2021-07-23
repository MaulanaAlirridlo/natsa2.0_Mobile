package com.natsa.natsa20_mobile.server.process.social_media;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Request;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserSocialMedia {
    public void addUserSocialMedia(Context context, Request request) {
        RetrofitBuilder.endPoint().addUserSocialMedia("Bearer " + Preferences.getToken(context), request)
                .enqueue(new Callback<com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Response>() {
                    @Override
                    public void onResponse(Call<com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Response> call, Response<com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Response> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, response.body().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, response.code() == 422 ? "Cek ulang data, Harap masukkan url beserta 'http'" :
                                    response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Response> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
