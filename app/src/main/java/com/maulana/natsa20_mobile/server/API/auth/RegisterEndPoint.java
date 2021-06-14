package com.maulana.natsa20_mobile.server.API.auth;

import com.maulana.natsa20_mobile.server.RetrofitBuilder;

public class RegisterEndPoint {
    public static RegisterApi registerEndPoint() {
        return RetrofitBuilder.endPoint().create(RegisterApi.class);
    }
}
