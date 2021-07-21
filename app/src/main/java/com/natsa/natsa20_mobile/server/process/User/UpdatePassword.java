package com.natsa.natsa20_mobile.server.process.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.natsa.natsa20_mobile.activity.LoginActivity;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.user.update_password.Request;
import com.natsa.natsa20_mobile.model.user.update_password.Response;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;

public class UpdatePassword {
    public void updateUserPassword(Context context, Request request) {
        RetrofitBuilder.endPoint().updatePassword("Bearer " + Preferences.getToken(context),
                request).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, response.body().getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                    Preferences.removeUser(context);
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);

                } else {
                    Toast.makeText(context, response.code() == 422 ? "Silahkan cek ulang data" + response.raw().request().header("current_password") :
                            response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
