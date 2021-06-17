package com.natsa.natsa20_mobile.server.process.auth;

import android.app.Activity;
import android.content.Intent;

import com.natsa.natsa20_mobile.activity.MainActivity;
import com.natsa.natsa20_mobile.model.auth.login.LoginForm;
import com.natsa.natsa20_mobile.model.auth.login.LoginRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login {
    private final LoginForm loginForm;

    public Login(String email, String password) {
        loginForm = new LoginForm(email, password);
    }

    public void LoginProcess(Activity activity) {
        RetrofitBuilder.endPoint().Login(loginForm)
                .enqueue(new Callback<LoginRespone>() {
                    @Override
                    public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                        if (response.isSuccessful()){
                            String token = response.body().getToken();
                            Preferences.setToken(activity.getApplicationContext(), token);
                            Intent i = new Intent(activity, MainActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
