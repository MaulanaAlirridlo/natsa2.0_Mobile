package com.natsa.natsa20_mobile.server.process.products;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProduct {

    private final static List<RiceField> product = new ArrayList<>();

    public static List<RiceField> getProductList() {
        return product;
    }

    public static void setProductList(RiceField riceField, ProductAdapter adapter) {
        product.clear();
        product.add(riceField);
        adapter.notifyDataSetChanged();
    }

    public void getProductFromApi(int id, ProductAdapter adapter) {
        RetrofitBuilder.endPoint().showRiceField(id)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            RiceField riceField = response.body().getRiceField();
                            setProductList(riceField, adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
