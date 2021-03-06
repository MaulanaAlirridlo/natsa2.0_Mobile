package com.natsa.natsa20_mobile.server.process.Paging.history;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.products.products.Products;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryDataSource extends PageKeyedDataSource<Integer, Data> {


    Context context;

    public HistoryDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getHistoryPerPage("Bearer " + Preferences.getToken(context), 1)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            callback.onResult(response.body().getRiceField().getData(), null, 2);
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getHistoryPerPage("Bearer " + Preferences.getToken(context), params.key)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getRiceField().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getHistoryPerPage("Bearer " + Preferences.getToken(context), params.key)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Integer key = response.body().getRiceField().getNext_page_url() != null ? params.key + 1 : null;
                            callback.onResult(response.body().getRiceField().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
