package com.natsa.natsa20_mobile.server.process.auth;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.activity.MainActivity;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.model.auth.login.LoginForm;
import com.natsa.natsa20_mobile.model.auth.login.LoginRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.helper.Preferences;

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
                    public void onResponse(@NonNull Call<LoginRespone> call, @NonNull Response<LoginRespone> response) {
                        if (response.isSuccessful()) {
                            LoginRespone res = response.body();
                            assert res != null;
                            User resUser = res.getUser();
                            Preferences.setUser(activity.getApplicationContext(), res.getToken(),
                                    resUser.getId(), resUser.getName(), resUser.getEmail(),
                                    resUser.getUsername(), resUser.getKtp(), resUser.getNo_hp(),
                                    resUser.getProfile_photo_url());
                            Intent i = new Intent(activity, MainActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        } else {
                            Toast.makeText(activity, response.code() == 422 ? "Silahkan cek ulang data" :
                                    response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginRespone> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
