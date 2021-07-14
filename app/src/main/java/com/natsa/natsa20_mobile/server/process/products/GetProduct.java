package com.natsa.natsa20_mobile.server.process.products;

import android.util.Log;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.adapter.ProductImageAdapter;
import com.natsa.natsa20_mobile.adapter.RandomRiceFieldsAdapter;
import com.natsa.natsa20_mobile.model.products.product.Photos;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.model.products.product.RandomRiceFields;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.server.process.User.GetUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProduct {

    private final static List<RiceField> productList = new ArrayList<>();
    private final static List<Photos> productImageList = new ArrayList<>();
    private final static List<RandomRiceFields> randomRiceFieldsList = new ArrayList<>();

    public static List<RiceField> getProductList() {
        return productList;
    }

    public static void setProductList(RiceField riceField,
                                      ProductAdapter productAdapter) {
        productList.clear();
        productList.add(riceField);
        productAdapter.notifyDataSetChanged();
    }

    public static List<Photos> getProductImageList() {
        return productImageList;
    }

    public static void setProductImageList(List<Photos> photos, ProductImageAdapter productImageAdapter) {
        productImageList.clear();
        productImageList.addAll(photos);
        productImageAdapter.notifyDataSetChanged();
    }

    public static List<RandomRiceFields> getRandomRiceFieldsList() {
        return randomRiceFieldsList;
    }

    public static void setRandomProductList(List<RandomRiceFields> randomRiceFields, RandomRiceFieldsAdapter adapter) {
        randomRiceFieldsList.clear();
        randomRiceFieldsList.addAll(randomRiceFields);
        adapter.notifyDataSetChanged();
    }

    public void getProductFromApi(int id, ProductAdapter productAdapter,
                                  ProductImageAdapter productImageAdapter,
                                  RandomRiceFieldsAdapter randomRiceFieldsAdapter) {
        RetrofitBuilder.endPoint().showRiceField(id)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Product res = response.body();
                            RiceField riceField = res.getRiceField();
                            new GetUser().getUserFromApi(riceField.getUser_id(), productAdapter);
                            List<Photos> photos = riceField.getPhotos();
                            List<RandomRiceFields> randomRiceFields = res.getRandomRiceFields();
                            setProductImageList(photos, productImageAdapter);
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
