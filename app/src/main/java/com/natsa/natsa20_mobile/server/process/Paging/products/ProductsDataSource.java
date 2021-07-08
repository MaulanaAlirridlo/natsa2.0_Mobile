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

    String keyword;
    TextView noData;

    public ProductsDataSource(@Nullable String keyword, TextView noData) {
        this.keyword = keyword;
        this.noData = noData;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        if (keyword == null) {
            RetrofitBuilder.endPoint().getRiceFieldsPerPage(1)
                    .enqueue(new Callback<Products>() {
                        @Override
                        public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                List<Data> data = response.body().getRiceField().getData();
                                if (data.isEmpty()){
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
        } else {
            RetrofitBuilder.endPoint().getRiceFieldsSearchResult(keyword, 1)
                    .enqueue(new Callback<Products>() {
                        @Override
                        public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                List<Data> data = response.body().getRiceField().getData();
                                if (data.isEmpty()){
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
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        if (keyword == null) {
            RetrofitBuilder.endPoint().getRiceFieldsPerPage(params.key)
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
        } else {
            RetrofitBuilder.endPoint().getRiceFieldsSearchResult(keyword, params.key)
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
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        if (keyword == null) {
            RetrofitBuilder.endPoint().getRiceFieldsPerPage(params.key)
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
        } else {
            RetrofitBuilder.endPoint().getRiceFieldsSearchResult(keyword, params.key)
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
}
