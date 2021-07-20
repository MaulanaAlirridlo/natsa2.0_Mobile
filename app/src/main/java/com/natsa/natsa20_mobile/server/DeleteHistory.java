package com.natsa.natsa20_mobile.server;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.DeleteProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteHistory {
    public void deleteHistory(Context context, Integer id){
        RetrofitBuilder.endPoint().deleteHistory("Bearer "+ Preferences.getToken(context), id)
                .enqueue(new Callback<DeleteProductResponse>() {
                    @Override
                    public void onResponse(Call<DeleteProductResponse> call, Response<DeleteProductResponse> response) {
                        if (response.isSuccessful()){
                            DeleteProductResponse res = response.body();
                            if (res.getRiceField() == 1) {
                                Toast.makeText(context, res.getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteProductResponse> call, Throwable t) {

                    }
                });
    }
}
