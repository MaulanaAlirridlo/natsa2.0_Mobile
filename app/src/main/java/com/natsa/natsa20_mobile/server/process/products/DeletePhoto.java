package com.natsa.natsa20_mobile.server.process.products;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.DeletePhotoResponse;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePhoto {
    public void deletePhoto(Context context, Integer id) {
        RetrofitBuilder.endPoint().deletePhotoProduct("Bearer " + Preferences.getToken(context), id)
                .enqueue(new Callback<DeletePhotoResponse>() {
                    @Override
                    public void onResponse(Call<DeletePhotoResponse> call, Response<DeletePhotoResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeletePhotoResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
