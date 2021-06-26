package com.natsa.natsa20_mobile.server.process.bookmark;

import android.content.Context;

import com.natsa.natsa20_mobile.adapter.BookmarkAdapater;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.GetBookmarkRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetBookmark {
    public static List<Data> bookmarkDataList = new ArrayList<>();

    public static List<Data> getBookmarkDataList() {
        return bookmarkDataList;
    }

    public void setBookmark(List<Data> products, BookmarkAdapater adapter) {
        bookmarkDataList.clear();
        bookmarkDataList.addAll(products);
        adapter.notifyDataSetChanged();
    }

    public void getBookmarkFromApi(BookmarkAdapater adapter, Context context) {
        RetrofitBuilder.endPoint().getBookmark("Bearer " + Preferences.getToken(context))
                .enqueue(new Callback<GetBookmarkRespone>() {
                    @Override
                    public void onResponse(Call<GetBookmarkRespone> call, Response<GetBookmarkRespone> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<Data> data = response.body().getBookmarks().getData();
                            setBookmark(data, adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBookmarkRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
