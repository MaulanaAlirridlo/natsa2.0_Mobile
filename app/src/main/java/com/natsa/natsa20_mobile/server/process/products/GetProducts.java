package com.natsa.natsa20_mobile.server.process.products;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.products.products.Products;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProducts {

    public static List<Data> productsDataList = new ArrayList<>();

    public static List<Data> getProductsDataList() {
        return productsDataList;
    }

    public void setProducts(List<Data> products, ProductsAdapter adapter) {
        productsDataList.clear();
        productsDataList.addAll(products);
        adapter.notifyDataSetChanged();
    }

    public void getProductsFromApi(ProductsAdapter adapter) {
        RetrofitBuilder.endPoint().getRiceFields()
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getRiceField().getData();
                            setProducts(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    // if need data from full url.
    // ex: http://192.168.1.5:8000/api/RiceFields?page=1
    public void getProductsFromApiWithDynamicUrl(ProductsAdapter adapter, String url) {
        RetrofitBuilder.endPoint().getProductsFromDynamicUrl(url)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getRiceField().getData();
                            setProducts(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    // if need data list from a page
    // input page number
//    public void getProductsPerPageFromApi(ProductsAdapter adapter, int page) {
//        RetrofitBuilder.endPoint().getRiceFieldsPerPage(page)
//                .enqueue(new Callback<Products>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
//                        if (response.isSuccessful()) {
//                            assert response.body() != null;
//                            List<Data> data = response.body().getRiceField().getData();
//                            setProducts(data, adapter);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
//    }

}
