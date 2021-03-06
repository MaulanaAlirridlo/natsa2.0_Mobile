package com.natsa.natsa20_mobile.server.process.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.activity.LoginActivity;
import com.natsa.natsa20_mobile.model.auth.logout.LogoutRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.helper.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Logout {
    private Context context;

    public void LogoutProcess(Activity activity) {
        context = activity.getApplicationContext();
        RetrofitBuilder.endPoint().Logout("Bearer "+Preferences.getToken(context))
                .enqueue(new Callback<LogoutRespone>() {

                    @Override
                    public void onResponse(@NonNull Call<LogoutRespone> call, @NonNull Response<LogoutRespone> response) {
                        if (response.isSuccessful()) {
                            Preferences.removeUser(context);
                            Intent i = new Intent(activity, LoginActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LogoutRespone> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
