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
    String keyword, sort;
    Integer maxharga, minharga, maxluas, minluas;
    LiveData productsPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;
    ProductsDSFactory productsDSFactory;
    TextView noData;

    public void customConstructor(@Nullable String keyword, @Nullable Integer maxharga,
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


    public LiveData ProductsViewModel() {

        productsDSFactory = new ProductsDSFactory(keyword, maxharga, minharga, maxluas, minluas, sort, noData);
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
