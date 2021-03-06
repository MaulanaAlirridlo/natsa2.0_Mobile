package com.natsa.natsa20_mobile.server.process.auth;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.natsa.natsa20_mobile.activity.MainActivity;
import com.natsa.natsa20_mobile.model.auth.register.RegisterForm;
import com.natsa.natsa20_mobile.model.auth.register.RegisterRespone;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;
import com.natsa.natsa20_mobile.helper.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register {
    RegisterForm registerForm;

    public Register(String name, String email, String password, String password_confirmation,
                    String username, String ktp) {
        registerForm = new RegisterForm(name, email, password, password_confirmation, username, ktp);
    }

    public void RegisterProcess(Activity activity) {
        RetrofitBuilder.endPoint().Register(registerForm)
                .enqueue(new Callback<RegisterRespone>() {
                    @Override
                    public void onResponse(@NonNull Call<RegisterRespone> call, @NonNull Response<RegisterRespone> response) {
                        if (response.isSuccessful()){
                            RegisterRespone res = response.body();
                            assert res != null;
                            RegisterRespone.User resUser = res.getUser();
                            Preferences.setUser(activity.getApplicationContext(), res.getToken(),
                                    resUser.getId(), resUser.getName(), resUser.getEmail(),
                                    resUser.getUsername(), resUser.getKtp(), null,
                                    resUser.getProfile_photo_url());
                            Intent i = new Intent(activity, MainActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        } else {
                            Toast.makeText(activity, response.code() == 422 ? "Silahkan cek ulang data" :
                                    response.code() + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<RegisterRespone> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
