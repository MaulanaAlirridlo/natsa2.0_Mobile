package com.natsa.natsa20_mobile.server;

import com.natsa.natsa20_mobile.model.auth.login.LoginForm;
import com.natsa.natsa20_mobile.model.auth.login.LoginRespone;
import com.natsa.natsa20_mobile.model.auth.logout.LogoutRespone;
import com.natsa.natsa20_mobile.model.auth.register.RegisterForm;
import com.natsa.natsa20_mobile.model.auth.register.RegisterRespone;
import com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone.AddBookmarkRespone;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.model.products.products.Products;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RouteAPI {

    //user


    //register
    @Headers({"Accept: application/json"})
    @POST(Server.register)
    Call<RegisterRespone> Register(@Body RegisterForm registerForm);

    //login
    @Headers({"Accept: application/json"})
    @POST(Server.login)
    Call<LoginRespone> Login(@Body LoginForm loginForm);

    //logout
    @Headers({"Accept: application/json"})
    @POST(Server.logout)
    Call<LogoutRespone> Logout(@Header("Authorization") String token);



    //product
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getRiceFields();

    @Headers({"Accept: application/json"})
    @GET(Server.riceFields+"{id}")
    Call<Product> showRiceField(@Path("id") int id);

    //bookmarks
    @Headers({"Accept: application/json"})
    @POST(Server.bookmarks+"{riceFieldId}")
    Call<AddBookmarkRespone> addBookmark(@Header("Authorization") String token, @Path("riceFieldId") int riceFieldId);
}
