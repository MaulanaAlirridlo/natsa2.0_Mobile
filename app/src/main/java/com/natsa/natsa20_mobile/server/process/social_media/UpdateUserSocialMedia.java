package com.natsa.natsa20_mobile.server.process.social_media;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.social_media.update_social_media_link.Request;
import com.natsa.natsa20_mobile.model.social_media.update_social_media_link.Response;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;

public class UpdateUserSocialMedia {
    public void updateUserSocialMedia(Context context, Request request, Integer id) {
        RetrofitBuilder.endPoint().updateUserSocialMedia("Bearer " + Preferences.getToken(context),
                request, id).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, response.body().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, response.code() == 422 ? "Cek ulang data, Harap masukkan url beserta 'http'" :
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
