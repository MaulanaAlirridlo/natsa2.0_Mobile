package com.maulana.natsa20_mobile.server.API.products;

import com.maulana.natsa20_mobile.model.products.product.Product;
import com.maulana.natsa20_mobile.model.products.products.Products;
import com.maulana.natsa20_mobile.server.Server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsApi {
    @GET(Server.riceFields)
    Call<Products> getRiceFields();

    @GET(Server.riceFields+"/{id}")
    Call<Product> showRiceFields(@Path("id") int id);
}
