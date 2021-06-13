package com.maulana.natsa20_mobile.server.process.products;

import android.content.Context;
import android.util.Log;

import com.maulana.natsa20_mobile.adapter.ProductAdapter;
import com.maulana.natsa20_mobile.model.product.Product;
import com.maulana.natsa20_mobile.model.product.RiceField;
import com.maulana.natsa20_mobile.server.API.Products.ProductsEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProduct {

    private static List<RiceField> product = new ArrayList<RiceField>();

    public static List<RiceField> getProductList() {
        return product;
    }

    public static void setProductList(List<RiceField> riceField, ProductAdapter adapter) {
        product.clear();
        product.addAll(riceField);
        adapter.notifyDataSetChanged();
    }

    public void getProductFromApi(int id, ProductAdapter adapter) {
        ProductsEndPoint.productsEndPoint().showRiceFields(id)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if (response.isSuccessful()) {
                            List<RiceField> riceField = response.body().getRiceField();
                            setProductList(riceField, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
