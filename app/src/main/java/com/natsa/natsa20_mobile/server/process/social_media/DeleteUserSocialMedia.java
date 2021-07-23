package com.natsa.natsa20_mobile.server.process.social_media;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.UpdateProductResponse;
import com.natsa.natsa20_mobile.model.social_media.DeleteUserSocialMediaResponse;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUserSocialMedia {
    public void deleteUserSocialMedia(Context context, Integer id) {
        RetrofitBuilder.endPoint().deleteUserSocialMedia("Bearer " + Preferences.getToken(context), id)
                .enqueue(new Callback<DeleteUserSocialMediaResponse>() {
                    @Override
                    public void onResponse(Call<DeleteUserSocialMediaResponse> call, Response<DeleteUserSocialMediaResponse> response) {
                        if (response.isSuccessful()){
                            DeleteUserSocialMediaResponse res = response.body();
                            if (res.getUserSocialMedia() == 1) {
                                Toast.makeText(context, res.getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteUserSocialMediaResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
