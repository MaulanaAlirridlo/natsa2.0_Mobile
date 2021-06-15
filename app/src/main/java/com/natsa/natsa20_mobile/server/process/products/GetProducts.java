package com.natsa.natsa20_mobile.server.process.products;

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

    public static List<Data> productsDataList = new ArrayList<Data>();

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
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.isSuccessful()) {
                            List<Data> data = response.body().getRiceField().getData();
                            setProducts(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


}