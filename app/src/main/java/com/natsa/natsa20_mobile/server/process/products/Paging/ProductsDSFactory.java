package com.natsa.natsa20_mobile.server.process.products.Paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class ProductsDSFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> productsLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ProductsDataSource productsDataSource = new ProductsDataSource();
        productsLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return productsLiveDataSource;
    }
}
