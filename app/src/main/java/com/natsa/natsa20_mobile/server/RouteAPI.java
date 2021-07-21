package com.natsa.natsa20_mobile.server;

import androidx.annotation.Nullable;

import com.natsa.natsa20_mobile.model.MakelarResponse;
import com.natsa.natsa20_mobile.model.products.DeleteProductResponse;
import com.natsa.natsa20_mobile.model.products.get_ricefield.GetRiceFieldResponse;
import com.natsa.natsa20_mobile.model.user.GetLoginUser;
import com.natsa.natsa20_mobile.model.user.GetUserRes;
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

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RouteAPI {

    //user
    @Headers({"Accept: application/json"})
    @GET(Server.userDetails)
    Call<GetLoginUser> getLoginUser(@Header("Authorization") String token);

    //show user
    @Headers({"Accept: application/json"})
    @GET(Server.users + "{userId}")
    Call<GetUserRes> getUser(@Path("userId") Integer id);

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

    //get makelar
    @Headers({"Accept: application/json"})
    @GET(Server.makelar + "{id}")
    Call<MakelarResponse> getMakelar(@Header("Authorization") String token, @Path("id") Integer id);

    //product
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getRiceFields();

    //add product
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(Server.riceFields)
    Call<Product> addRiceFields(
            @Header("Authorization") String token,
            @Part("title") RequestBody title,
            @Part("harga") RequestBody harga,
            @Part("luas") RequestBody luas,
            @Part("alamat") RequestBody alamat,
            @Part("maps") RequestBody maps,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("sertifikasi") RequestBody sertifikasi,
            @Part("tipe") RequestBody tipe,
            @Part("vestige") RequestBody vestige,
            @Part("irrigation") RequestBody irrigation,
            @Part("region") RequestBody region,
            @Part List<MultipartBody.Part> photo
            );

    //pagination
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getRiceFieldsPerPage(
            @Nullable @Query("region") String region,
            @Nullable @Query("filter[tipe]") String tipe,
            @Nullable @Query("filter[sertifikasi]") String sertifikasi,
            @Nullable @Query("maxluas") Integer maxluas,
            @Nullable @Query("minluas") Integer minluas,
            @Nullable @Query("maxharga") Integer maxharga,
            @Nullable @Query("minharga") Integer minharga,
            @Nullable @Query("filter[vestige_id]") Integer vestige_id,
            @Nullable @Query("filter[irrigation_id]") Integer irrigation_id,
            @Nullable @Query("sort") String sort,
            @Query("page") Integer page
    );

    //dynamic product url
    @Headers({"Accept: application/json"})
    @GET
    Call<Products> getProductsFromDynamicUrl(@Url String url);

    //search ricefield with paging
    @Headers({"Accept: application/json"})
    @GET(Server.searchProduct + "{search}")
    Call<Products> getRiceFieldsSearchResult(@Path("search") String search, @Query("page") int page);

    //show product
    @Headers({"Accept: application/json"})
    @GET(Server.product + "{id}")
    Call<Product> showProduct(@Path("id") int id);

    //show riceField
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields + "{id}")
    Call<GetRiceFieldResponse> showRiceField(@Path("id") int id);

    //delete product
    @Headers({"Accept: application/json"})
    @DELETE(Server.riceFields + "{productId}")
    Call<DeleteProductResponse> deleteProduct(@Header("Authorization") String token, @Path("productId") int id);


    //user product
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getUserRiceFields(@Query("filter[user_id]") int id);

    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getUserRiceFieldsPerPage(@Query("filter[user_id]") int id, @Query("page") int page);


    //history
    @Headers({"Accept: application/json"})
    @GET(Server.history)
    Call<Products> getHistoryPerPage(@Header("Authorization") String token, @Query("page") int page);

    //delete history
    @Headers({"Accept: application/json"})
    @DELETE(Server.history + "{historyId}")
    Call<DeleteProductResponse> deleteHistory(@Header("Authorization") String token, @Path("historyId") int id);


    //bookmarks
    @Headers({"Accept: application/json"})
    @POST(Server.bookmarks + "{riceFieldId}")
    Call<AddBookmarkRespone> addBookmark(@Header("Authorization") String token, @Path("riceFieldId") int riceFieldId);

    @Headers({"Accept: application/json"})
    @GET(Server.bookmarks)
    Call<GetBookmarkRespone> getBookmark(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET(Server.bookmarks)
    Call<GetBookmarkRespone> getBookmarkPerPage(@Header("Authorization") String token, @Query("page") int page);

    @Headers({"Accept: application/json"})
    @DELETE(Server.bookmarks + "{bookmarkId}")
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
