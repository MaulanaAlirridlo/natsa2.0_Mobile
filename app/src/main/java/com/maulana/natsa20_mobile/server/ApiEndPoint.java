package com.maulana.natsa20_mobile.server;

public class ApiEndPoint {
    public static RouteAPI EndPoint() {
        return RetrofitBuilder.endPoint().create(RouteAPI.class);
    }
}
