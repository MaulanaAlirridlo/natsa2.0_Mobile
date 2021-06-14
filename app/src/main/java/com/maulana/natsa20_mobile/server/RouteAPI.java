package com.maulana.natsa20_mobile.server;

import com.maulana.natsa20_mobile.model.auth.login.LoginForm;
import com.maulana.natsa20_mobile.model.auth.login.LoginRespone;
import com.maulana.natsa20_mobile.model.auth.register.RegisterForm;
import com.maulana.natsa20_mobile.model.auth.register.RegisterRespone;
import com.maulana.natsa20_mobile.model.products.product.Product;
import com.maulana.natsa20_mobile.model.products.products.Products;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RouteAPI {

    //user


    //register
    @POST(Server.register)
    Call<RegisterRespone> Register(@Body RegisterForm registerForm);

    //login
    @POST(Server.login)
    Call<LoginRespone> Login(@Body LoginForm loginForm);



    //product
    @GET(Server.riceFields)
    Call<Products> getRiceFields();

    @GET(Server.riceFields+"/{id}")
    Call<Product> showRiceFields(@Path("id") int id);
}
