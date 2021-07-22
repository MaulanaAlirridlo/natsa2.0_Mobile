package com.natsa.natsa20_mobile.server.process.User;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.natsa.natsa20_mobile.activity.LoginActivity;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.user.DeleteUserResponse;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUser {
    public void deleteLoginUser(Context context) {
        RetrofitBuilder.endPoint().deleteLoginUser("Bearer " + Preferences.getToken(context))
                .enqueue(new Callback<DeleteUserResponse>() {
                    @Override
                    public void onResponse(Call<DeleteUserResponse> call, Response<DeleteUserResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Preferences.removeUser(context);
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteUserResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
