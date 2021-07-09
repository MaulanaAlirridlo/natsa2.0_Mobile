package com.natsa.natsa20_mobile.server.process.Paging.products;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class ProductsDSFactory extends DataSource.Factory {

    String keyword, sort;
    Integer maxharga, minharga, maxluas, minluas;
    TextView noData;

    public ProductsDSFactory(@Nullable String keyword, @Nullable Integer maxharga,
                              @Nullable Integer minharga, @Nullable Integer maxluas,
                              @Nullable Integer minluas, @Nullable String sort, TextView noData) {
        this.keyword = keyword;
        this.sort = sort;
        this.maxharga = maxharga;
        this.minharga = minharga;
        this.maxluas = maxluas;
        this.minluas = minluas;
        this.noData = noData;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> productsLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ProductsDataSource productsDataSource = new ProductsDataSource(keyword, maxharga, minharga,
                maxluas, minluas, sort, noData);
        productsLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return productsLiveDataSource;
    }
}
