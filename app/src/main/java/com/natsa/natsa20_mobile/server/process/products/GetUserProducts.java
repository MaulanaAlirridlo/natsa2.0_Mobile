package com.natsa.natsa20_mobile.server.process.products;

import com.natsa.natsa20_mobile.adapter.UserProductsAdapter;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.model.products.products.Products;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserProducts {
    public static List<Data> userProductsDataList = new ArrayList<>();

    public static List<Data> getUserProductsDataList() {
        return userProductsDataList;
    }

    public void setUserProductsDataList(List<Data> products, UserProductsAdapter adapter) {
        userProductsDataList.clear();
        userProductsDataList.addAll(products);
        adapter.notifyDataSetChanged();
    }

    public void getUserProductsFromApi(UserProductsAdapter adapter, int id) {
        RetrofitBuilder.endPoint().getUserRiceFields(id)
                .enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getRiceField().getData();
                            setUserProductsDataList(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
