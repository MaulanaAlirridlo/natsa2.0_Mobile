package com.natsa.natsa20_mobile.server.process.Paging.products;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.products.products.Products;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsDataSource extends PageKeyedDataSource<Integer, Data> {

    String keyword, tipe, sertifikasi, sort;
    Integer maxharga, minharga, maxluas, minluas, idBekasSawah, idIrigasi;
    TextView noData;

    public ProductsDataSource(@Nullable String keyword, @Nullable String tipe,
                              @Nullable String sertifikasi, @Nullable Integer maxluas,
                              @Nullable Integer minluas, @Nullable Integer maxharga,
                              @Nullable Integer minharga, @Nullable Integer idBekasSawah,
                              @Nullable Integer idIrigasi, @Nullable String sort, TextView noData) {
        this.keyword = keyword;
        this.tipe = tipe;
        this.sertifikasi = sertifikasi;
        this.sort = sort;
        this.maxharga = maxharga;
        this.minharga = minharga;
        this.maxluas = maxluas;
        this.minluas = minluas;
        this.idBekasSawah = idBekasSawah;
        this.idIrigasi = idIrigasi;
        this.noData = noData;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getRiceFieldsPerPage(keyword, tipe, sertifikasi, maxluas, minluas, maxharga,
                minharga, idBekasSawah, idIrigasi, sort, 1)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getRiceField().getData();
                            if (data.isEmpty()) {
                                noData.setVisibility(TextView.VISIBLE);
                            }
                            callback.onResult(data, null, 2);
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
        RetrofitBuilder.endPoint().getRiceFieldsPerPage(keyword, tipe, sertifikasi, maxluas, minluas, maxharga,
                minharga, idBekasSawah, idIrigasi, sort, params.key)
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
        RetrofitBuilder.endPoint().getRiceFieldsPerPage(keyword, tipe, sertifikasi, maxluas, minluas, maxharga,
                minharga, idBekasSawah, idIrigasi, sort, params.key)
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
