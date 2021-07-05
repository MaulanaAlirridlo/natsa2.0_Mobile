package com.natsa.natsa20_mobile.server.process.Paging.bookmark;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.GetBookmarkRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookmarksDataSource extends PageKeyedDataSource<Integer, Data> {


    Context context;

    public BookmarksDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getBookmarkPerPage("Bearer " + Preferences.getToken(context), 1)
                .enqueue(new Callback<GetBookmarkRespone>() {
                    @Override
                    public void onResponse(Call<GetBookmarkRespone> call, Response<GetBookmarkRespone> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            callback.onResult(response.body().getBookmarks().getData(), null, 2);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBookmarkRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getBookmarkPerPage("Bearer " + Preferences.getToken(context), params.key)
                .enqueue(new Callback<GetBookmarkRespone>() {
                    @Override
                    public void onResponse(Call<GetBookmarkRespone> call, Response<GetBookmarkRespone> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getBookmarks().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBookmarkRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Data> callback) {
        RetrofitBuilder.endPoint().getBookmarkPerPage("Bearer " + Preferences.getToken(context), params.key)
                .enqueue(new Callback<GetBookmarkRespone>() {
                    @Override
                    public void onResponse(Call<GetBookmarkRespone> call, Response<GetBookmarkRespone> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Integer key = response.body().getBookmarks().getNext_page_url() != null ? params.key + 1 : null;
                            callback.onResult(response.body().getBookmarks().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBookmarkRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
