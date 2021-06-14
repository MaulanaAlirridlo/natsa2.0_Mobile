package com.maulana.natsa20_mobile.server.API.products;

import com.maulana.natsa20_mobile.server.RetrofitBuilder;

public class ProductsEndPoint {
    public static ProductsApi productsEndPoint() {
        return RetrofitBuilder.endPoint().create(ProductsApi.class);
    }
}
