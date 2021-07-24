package com.natsa.natsa20_mobile.server.process.products;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.natsa.natsa20_mobile.activity.BackActivity;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.UpdateResponse;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProduct {
    Activity activity;

    public UpdateProduct(Activity activity) {
        this.activity = activity;
    }

    public void addProductToServer(
            Integer id,
            RequestBody title,
            RequestBody harga,
            RequestBody luas,
            RequestBody alamat,
            RequestBody maps,
            RequestBody deskripsi,
            RequestBody sertifikasi,
            RequestBody tipe,
            RequestBody vestige,
            RequestBody irigation,
            RequestBody region,
            List<MultipartBody.Part> photo
    ) {
        RetrofitBuilder.endPoint().updateRiceFields(
                id, "PUT", "Bearer " + Preferences.getToken(activity),
                title, harga, luas, alamat, maps, deskripsi, sertifikasi, tipe, vestige, irigation,
                region, photo
        ).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful()){
                    Intent i = new Intent(activity, BackActivity.class);
                    i.putExtra("page", "detailSawah");
                    i.putExtra("id", id);
                    activity.startActivity(i);
                    activity.finish();
                } else {
                    Toast.makeText(activity, response.code() == 422 ? "Silahkan cek ulang data" :
                            response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
