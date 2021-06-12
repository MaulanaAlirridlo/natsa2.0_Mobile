package com.maulana.natsa20_mobile.server.API.Products;

import com.maulana.natsa20_mobile.model.products.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApi {
    @GET("riceFields")
    Call<Products> getRiceFields();
}
