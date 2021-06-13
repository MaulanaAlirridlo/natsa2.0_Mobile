package com.maulana.natsa20_mobile.server.API.Products;

import com.maulana.natsa20_mobile.model.product.Product;
import com.maulana.natsa20_mobile.model.products.Products;
import com.maulana.natsa20_mobile.server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsApi {
    @GET(Server.riceFields)
    Call<Products> getRiceFields();

    @GET(Server.riceFields+"/{id}")
    Call<Product> showRiceFields(@Path("id") int id);
}
