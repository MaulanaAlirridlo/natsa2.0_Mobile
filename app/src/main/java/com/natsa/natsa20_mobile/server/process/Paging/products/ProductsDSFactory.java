package com.natsa.natsa20_mobile.server.process.Paging.products;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class ProductsDSFactory extends DataSource.Factory {

    String keyword, tipe, sertifikasi, sort;
    Integer maxharga, minharga, maxluas, minluas, idBekasSawah, idIrigasi;
    TextView noData;

    public ProductsDSFactory(@Nullable String keyword, @Nullable String tipe,
                             @Nullable String sertifikasi, @Nullable Integer maxluas,
                             @Nullable Integer minluas, @Nullable Integer maxharga,
                             @Nullable Integer minharga, @Nullable Integer idBekasSawah,
                             @Nullable Integer idIrigasi, @Nullable String sort, TextView noData) {
        this.keyword = keyword;
        this.tipe = tipe;
        this.sertifikasi = sertifikasi;
        this.sort = sort;
        this.maxharga = maxharga;
        this.minharga = minharga;
        this.maxluas = maxluas;
        this.minluas = minluas;
        this.idBekasSawah = idBekasSawah;
        this.idIrigasi = idIrigasi;
        this.noData = noData;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> productsLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ProductsDataSource productsDataSource = new ProductsDataSource(keyword, tipe, sertifikasi,
                maxluas, minluas, maxharga, minharga, idBekasSawah, idIrigasi, sort, noData);
        productsLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return productsLiveDataSource;
    }
}
