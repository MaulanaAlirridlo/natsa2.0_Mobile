package com.maulana.natsa20_mobile.server.process.auth;

import android.content.Context;

import com.maulana.natsa20_mobile.model.auth.login.LoginForm;
import com.maulana.natsa20_mobile.model.auth.login.LoginRespone;
import com.maulana.natsa20_mobile.server.ApiEndPoint;
import com.maulana.natsa20_mobile.shared_preference.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login {
    private LoginForm loginForm;

    public Login(String email, String password) {
        loginForm = new LoginForm(email, password);
    }

    public void LoginProcess(Context context) {
        ApiEndPoint.EndPoint().Login(loginForm)
                .enqueue(new Callback<LoginRespone>() {
                    @Override
                    public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                        if (response.isSuccessful()){
                            String token = response.body().getToken();
                            Preferences.setToken(context, token);
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
