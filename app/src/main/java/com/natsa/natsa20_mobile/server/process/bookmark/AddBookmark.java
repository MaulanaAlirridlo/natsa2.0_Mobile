package com.natsa.natsa20_mobile.server.process.bookmark;

import android.content.Context;
import android.widget.Toast;

import com.natsa.natsa20_mobile.model.bookmark.add_bookmark.add_bookmark_respone.AddBookmarkRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.helper.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookmark {

    public void addBookmarkProcess(int riceFieldId, Context context) {
        RetrofitBuilder.endPoint().addBookmark("Bearer "+ Preferences.getToken(context), riceFieldId)
                .enqueue(new Callback<AddBookmarkRespone>() {
                    @Override
                    public void onResponse(Call<AddBookmarkRespone> call, Response<AddBookmarkRespone> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, response.body().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddBookmarkRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
