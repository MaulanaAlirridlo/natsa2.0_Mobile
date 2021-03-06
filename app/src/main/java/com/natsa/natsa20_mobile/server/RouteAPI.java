package com.natsa.natsa20_mobile.server;

import androidx.annotation.Nullable;

import com.natsa.natsa20_mobile.model.MakelarResponse;
import com.natsa.natsa20_mobile.model.irrigations.Irrigation;
import com.natsa.natsa20_mobile.model.products.DeletePhotoResponse;
import com.natsa.natsa20_mobile.model.products.UpdateProductResponse;
import com.natsa.natsa20_mobile.model.products.UpdateResponse;
import com.natsa.natsa20_mobile.model.products.get_ricefield.GetRiceFieldResponse;
import com.natsa.natsa20_mobile.model.regions.Region;
import com.natsa.natsa20_mobile.model.social_media.DeleteUserSocialMediaResponse;
import com.natsa.natsa20_mobile.model.social_media.GetSocialMediaResponse;
import com.natsa.natsa20_mobile.model.social_media.GetUserSocialMediaResponse;
import com.natsa.natsa20_mobile.model.user.DeleteUserResponse;
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
import com.natsa.natsa20_mobile.model.user.update_password.Request;
import com.natsa.natsa20_mobile.model.user.update_password.Response;
import com.natsa.natsa20_mobile.model.vestiges.Vestige;
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
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RouteAPI {

    //user
    @Headers({"Accept: application/json"})
    @GET(Server.userDetails)
    Call<GetLoginUser> getLoginUser(@Header("Authorization") String token);

    //update profile
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(Server.users)
    Call<com.natsa.natsa20_mobile.model.user.update_profile.Response>
    updateProfile(@Query("_method") String method, @Header("Authorization") String token,
                  @Part("name") RequestBody name,
                  @Part("email") RequestBody email,
                  @Part("username") RequestBody username,
                  @Part("ktp") RequestBody ktp,
                  @Part("no_hp") RequestBody no_hp,
                  @Part MultipartBody.Part photo);

    //update password
    @Headers({"Accept: application/json"})
    @PUT(Server.updatePassword)
    Call<Response> updatePassword(@Header("Authorization") String token, @Body Request request);

    //delete user
    @Headers({"Accept: application/json"})
    @DELETE(Server.users)
    Call<DeleteUserResponse> deleteLoginUser(@Header("Authorization") String token);

    //show user
    @Headers({"Accept: application/json"})
    @GET(Server.usersWithSlash + "{userId}")
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
    @GET(Server.makelarWithSlash + "{id}")
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
    @GET(Server.searchProductWithSlash + "{search}")
    Call<Products> getRiceFieldsSearchResult(@Path("search") String search, @Query("page") int page);

    //show product
    @Headers({"Accept: application/json"})
    @GET(Server.productWithSlash + "{id}")
    Call<Product> showProduct(@Path("id") int id);

    //show riceField
    @Headers({"Accept: application/json"})
    @GET(Server.riceFieldsWithSlash + "{id}")
    Call<GetRiceFieldResponse> showRiceField(@Path("id") int id);

    //update product
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(Server.riceFieldsWithSlash + "{id}")
    Call<UpdateResponse> updateRiceFields(
            @Path("id") int id,
            @Query("_method") String method,
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

    //delete product
    @Headers({"Accept: application/json"})
    @DELETE(Server.riceFieldsWithSlash + "{productId}")
    Call<UpdateProductResponse> deleteProduct(@Header("Authorization") String token, @Path("productId") int id);

    //delete photo product
    @Headers({"Accept: application/json"})
    @DELETE(Server.deletePhotoWithSlash + "{id}")
    Call<DeletePhotoResponse> deletePhotoProduct(@Header("Authorization") String token, @Path("id") Integer id);

    //user product
    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getUserRiceFields(@Query("filter[user_id]") int id);

    @Headers({"Accept: application/json"})
    @GET(Server.riceFields)
    Call<Products> getUserRiceFieldsPerPage(@Query("filter[user_id]") int id, @Query("page") int page);

    //update ketersediaan product
    @Headers({"Accept: application/json"})
    @PUT(Server.ketersediaanWithSlash + "{id}")
    Call<UpdateProductResponse> updateKetersediaan(@Header("Authorization") String token, @Path("id") int id);


    //history
    @Headers({"Accept: application/json"})
    @GET(Server.history)
    Call<Products> getHistoryPerPage(@Header("Authorization") String token, @Query("page") int page);

    //delete history
    @Headers({"Accept: application/json"})
    @DELETE(Server.historyWithSlash + "{historyId}")
    Call<UpdateProductResponse> deleteHistory(@Header("Authorization") String token, @Path("historyId") int id);


    //bookmarks
    @Headers({"Accept: application/json"})
    @POST(Server.bookmarksWithSlash + "{riceFieldId}")
    Call<AddBookmarkRespone> addBookmark(@Header("Authorization") String token, @Path("riceFieldId") int riceFieldId);

    @Headers({"Accept: application/json"})
    @GET(Server.bookmarks)
    Call<GetBookmarkRespone> getBookmark(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET(Server.bookmarks)
    Call<GetBookmarkRespone> getBookmarkPerPage(@Header("Authorization") String token, @Query("page") int page);

    @Headers({"Accept: application/json"})
    @DELETE(Server.bookmarksWithSlash + "{bookmarkId}")
    Call<DeleteBookmarkRespone> deleteBookmark(@Header("Authorization") String token, @Path("bookmarkId") int bookmarkId);

    //regions
    @Headers({"Accept: application/json"})
    @GET(Server.regions)
    Call<Regions> getRegions();

    //regions by id
    @Headers({"Accept: application/json"})
    @GET(Server.regionsWithSlash + "{id}")
    Call<Region> getRegionById(@Path("id") int id);

    //vestiges atau bekas sawah
    @Headers({"Accept: application/json"})
    @GET(Server.vestiges)
    Call<Vestiges> getVestiges();

    //vestiges atau bekas sawah by id
    @Headers({"Accept: application/json"})
    @GET(Server.vestigesWithSlash + "{id}")
    Call<Vestige> getVestigeById(@Path("id") int id);

    //irrigation
    @Headers({"Accept: application/json"})
    @GET(Server.irrigations)
    Call<Irrigations> getIrrigations();

    //irrigation by id
    @Headers({"Accept: application/json"})
    @GET(Server.irrigationsWithSlash + "{id}")
    Call<Irrigation> getIrrigationById(@Path("id") int id);

    //social media
    @Headers({"Accept: application/json"})
    @GET(Server.socialMedia)
    Call<GetSocialMediaResponse> getSocialMedia();

    //user social media
    @Headers({"Accept: application/json"})
    @GET(Server.userSocialMedia)
    Call<GetUserSocialMediaResponse> getUserSocialMedia(@Header("Authorization") String token);

    //add user social media
    @Headers({"Accept: application/json"})
    @POST(Server.userSocialMedia)
    Call<com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Response>
    addUserSocialMedia(@Header("Authorization") String token,
                          @Body com.natsa.natsa20_mobile.model.social_media.add_new_social_media.Request request);

    //add user social media
    @Headers({"Accept: application/json"})
    @PUT(Server.userSocialMediaWithSlash + "{id}")
    Call<com.natsa.natsa20_mobile.model.social_media.update_social_media_link.Response>
    updateUserSocialMedia(@Header("Authorization") String token,
                          @Body com.natsa.natsa20_mobile.model.social_media.update_social_media_link.Request request,
                          @Path("id") Integer id);

    //delete user social media
    @Headers({"Accept: application/json"})
    @DELETE(Server.userSocialMediaWithSlash + "{id}")
    Call<DeleteUserSocialMediaResponse> deleteUserSocialMedia(@Header("Authorization") String token,
                                                              @Path("id") Integer id);

}
