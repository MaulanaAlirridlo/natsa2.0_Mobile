package com.natsa.natsa20_mobile.server;

import com.natsa.natsa20_mobile.model.auth.login.LoginForm;
import com.natsa.natsa20_mobile.model.auth.login.LoginRespone;
import com.natsa.natsa20_mobile.model.auth.logout.LogoutRespone;
import com.natsa.natsa20_mobile.model.auth.register.RegisterForm;
import com.natsa.natsa20_mobile.model.auth.register.RegisterRespone;
import com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone.AddBookmarkRespone;
import com.natsa.natsa20_mobile.model.bookmark.delete_bookmark.DeleteBookmarkRespone;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.GetBookmarkRespone;
import com.natsa.natsa20_mobile.model.irrigations.Irrigations;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.model.products.products.Products;
import com.natsa.natsa20_mobile.model.regions.Regions;
import com.natsa.natsa20_mobile.model.vestiges.Vestiges;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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


    //dynamic product url
    @GET
    Call<Products> getProductsFromDynamicUrl(@Url String url);

    //product
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getRiceFields();

    //pagination
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getRiceFieldsPerPage(@Query("page") int page);

    //show product
    @Headers({"Accept: application/json"})
    @GET(Server.product+"{id}")
    Call<Product> showRiceField(@Path("id") int id);

    //user product
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getUserRiceFields(@Query("filter[user_id]") int id);

    //add user product



    //bookmarks
    @Headers({"Accept: application/json"})
    @POST(Server.bookmarks+"{riceFieldId}")
    Call<AddBookmarkRespone> addBookmark(@Header("Authorization") String token, @Path("riceFieldId") int riceFieldId);

    @Headers({"Accept: application/json"})
    @GET(Server.bookmarks)
    Call<GetBookmarkRespone> getBookmark(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @DELETE(Server.bookmarks+"{bookmarkId}")
    Call<DeleteBookmarkRespone> deleteBookmark(@Header("Authorization") String token, @Path("bookmarkId") int bookmarkId);

    //regions
    @Headers({"Accept: application/json"})
    @GET(Server.regions)
    Call<Regions> getRegions();

    //vestiges atau bekas sawah
    @Headers({"Accept: application/json"})
    @GET(Server.vestiges)
    Call<Vestiges> getVestiges();

    //irrigation
    @Headers({"Accept: application/json"})
    @GET(Server.irrigations)
    Call<Irrigations> getIrrigations();


}
