package com.maulana.natsa20_mobile.server.API.Products;

import com.maulana.natsa20_mobile.server.RetrofitBuilder;

public class ProductsEndPoint {
    public static ProductsApi productEndPoint() {
        return RetrofitBuilder.endPoint().create(ProductsApi.class);
    }
}
