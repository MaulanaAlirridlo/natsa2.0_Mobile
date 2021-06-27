package com.natsa.natsa20_mobile.server.process.products;

import android.util.Log;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.adapter.RandomRiceFieldsAdapter;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.model.products.product.RandomRiceFields;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProduct {

    private final static List<RiceField> product = new ArrayList<>();
    private final static List<RandomRiceFields> randomRiceFieldsList = new ArrayList<>();

    public static List<RiceField> getProductList() {
        return product;
    }

    public static void setProductList(RiceField riceField, ProductAdapter adapter) {
        product.clear();
        product.add(riceField);
        adapter.notifyDataSetChanged();
    }

    public static List<RandomRiceFields> getRandomRiceFields() {
        return randomRiceFieldsList;
    }

    public static void setRandomProductList(List<RandomRiceFields> randomRiceFields, RandomRiceFieldsAdapter adapter) {
        randomRiceFieldsList.clear();
        randomRiceFieldsList.addAll(randomRiceFields);
        adapter.notifyDataSetChanged();
    }

    public void getProductFromApi(int id, ProductAdapter productAdapter, RandomRiceFieldsAdapter randomRiceFieldsAdapter) {
        RetrofitBuilder.endPoint().showRiceField(id)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Product res = response.body();
                            RiceField riceField = res.getRiceField();
                            List<RandomRiceFields> randomRiceFields = res.getRandomRiceFields();
                            setProductList(riceField, productAdapter);
                            setRandomProductList(randomRiceFields, randomRiceFieldsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
