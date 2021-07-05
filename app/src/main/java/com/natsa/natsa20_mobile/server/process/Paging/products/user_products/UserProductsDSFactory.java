package com.natsa.natsa20_mobile.server.process.Paging.products.user_products;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class UserProductsDSFactory extends DataSource.Factory {

    Context context;

    public UserProductsDSFactory(Context context) {
        this.context = context;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> productsLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        UserProductsDS productsDataSource = new UserProductsDS(context);
        productsLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return productsLiveDataSource;
    }
}
