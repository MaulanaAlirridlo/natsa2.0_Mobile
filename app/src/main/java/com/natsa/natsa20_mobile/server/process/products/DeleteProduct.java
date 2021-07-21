package com.natsa.natsa20_mobile.server.process.products;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.UpdateProductResponse;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteProduct {
    public void deleteProduct(Context context, Integer id){
        RetrofitBuilder.endPoint().deleteProduct("Bearer "+ Preferences.getToken(context), id)
                .enqueue(new Callback<UpdateProductResponse>() {
                    @Override
                    public void onResponse(Call<UpdateProductResponse> call, Response<UpdateProductResponse> response) {
                        if (response.isSuccessful()){
                            UpdateProductResponse res = response.body();
                            if (res.getRiceField() == 1) {
                                Toast.makeText(context, res.getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateProductResponse> call, Throwable t) {

                    }
                });
    }
}
