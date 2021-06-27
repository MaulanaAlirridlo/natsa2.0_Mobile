package com.natsa.natsa20_mobile.server.process.bookmark;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone.AddBookmarkRespone;
import com.natsa.natsa20_mobile.model.bookmark.delete_bookmark.DeleteBookmarkRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteBookmark {
    public void deleteBookmarkProcess(int bookmarkId, Context context) {
        Log.d("TAG", "deleteBookmarkProcess: "+bookmarkId);
        RetrofitBuilder.endPoint().deleteBookmark("Bearer "+ Preferences.getToken(context), bookmarkId)
                .enqueue(new Callback<DeleteBookmarkRespone>() {
                    @Override
                    public void onResponse(Call<DeleteBookmarkRespone> call, Response<DeleteBookmarkRespone> response) {
                        if (response.isSuccessful()) {
                            DeleteBookmarkRespone res = response.body();
                            if (res.getRiceField() == 1) {
                                Toast.makeText(context, res.getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteBookmarkRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
