package com.natsa.natsa20_mobile.server.process.Paging.products;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class ProductsViewModel extends ViewModel {
    String keyword, tipe, sertifikasi, sort;
    Integer maxharga, minharga, maxluas, minluas, idBekasSawah, idIrigasi;
    LiveData productsPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;
    ProductsDSFactory productsDSFactory;
    TextView noData;

    public void customConstructor(@Nullable String keyword, @Nullable String tipe,
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


    public LiveData ProductsViewModel() {

        productsDSFactory = new ProductsDSFactory(keyword, tipe, sertifikasi, maxluas, minluas, maxharga,
                minharga, idBekasSawah, idIrigasi, sort, noData);
        liveDataSource = productsDSFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(10)
                        .build();

        return productsPagedList = (new LivePagedListBuilder(productsDSFactory, config)).build();

    }

    public void refresh() {
        if (productsDSFactory.getItemLiveDataSource().getValue() != null) {
            productsDSFactory.getItemLiveDataSource().getValue().invalidate();
        }
    }
}
