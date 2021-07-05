package com.natsa.natsa20_mobile.server.process.products.Paging.products.user_products;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.products.products.Products;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProductsDS  extends PageKeyedDataSource<Integer, Data> {
    Context context;

    public UserProductsDS(Context context) {
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getUserRiceFieldsPerPage(Preferences.getId(context), 1)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            callback.onResult(response.body().getRiceField().getData(), null, 2);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getUserRiceFieldsPerPage(Preferences.getId(context), params.key)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getRiceField().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getUserRiceFieldsPerPage(Preferences.getId(context), params.key)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Integer key = response.body().getRiceField().getNext_page_url() != null ? params.key + 1 : null;
                            callback.onResult(response.body().getRiceField().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
