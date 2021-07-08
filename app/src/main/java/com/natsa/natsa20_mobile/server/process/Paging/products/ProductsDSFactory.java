package com.natsa.natsa20_mobile.server.process.Paging.products;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class ProductsDSFactory extends DataSource.Factory {

    String keyword;
    TextView noData;

    public ProductsDSFactory(@Nullable String keyword, TextView noData) {
        this.keyword = keyword;
        this.noData = noData;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> productsLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ProductsDataSource productsDataSource = new ProductsDataSource(keyword, noData);
        productsLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return productsLiveDataSource;
    }
}
