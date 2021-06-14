package com.maulana.natsa20_mobile.server.process.auth;

import android.content.Context;
import android.util.Log;

import com.maulana.natsa20_mobile.adapter.ProductAdapter;
import com.maulana.natsa20_mobile.model.auth.register.RegisterForm;
import com.maulana.natsa20_mobile.model.auth.register.RegisterRespone;
import com.maulana.natsa20_mobile.model.products.product.Product;
import com.maulana.natsa20_mobile.model.products.product.RiceField;
import com.maulana.natsa20_mobile.server.API.auth.RegisterEndPoint;
import com.maulana.natsa20_mobile.server.API.products.ProductsEndPoint;
import com.maulana.natsa20_mobile.shared_preference.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register {
    RegisterForm registerForm;
    String TAG = "jkl";

    public Register(String name, String email, String password, String password_confirmation) {
        registerForm = new RegisterForm(name, email, password, password_confirmation);
    }


    public void RegisterProcess(Context context) {
        RegisterEndPoint.registerEndPoint().Register(registerForm)
                .enqueue(new Callback<RegisterRespone>() {
                    @Override
                    public void onResponse(Call<RegisterRespone> call, Response<RegisterRespone> response) {
                        if (response.isSuccessful()){
                            String token = response.body().getToken();
                            Preferences.setToken(context, token);
                        }
                    }
                    @Override
                    public void onFailure(Call<RegisterRespone> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
