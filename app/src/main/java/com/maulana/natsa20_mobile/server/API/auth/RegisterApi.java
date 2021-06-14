package com.maulana.natsa20_mobile.server.API.auth;

import com.maulana.natsa20_mobile.model.auth.register.RegisterForm;
import com.maulana.natsa20_mobile.model.auth.register.RegisterRespone;
import com.maulana.natsa20_mobile.server.Server;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApi {
    @POST(Server.register)
    Call<RegisterRespone> Register(@Body RegisterForm registerForm);
}
