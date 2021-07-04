package com.natsa.natsa20_mobile.server.process.products.Paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class ProductsViewModel extends ViewModel {

    LiveData productsPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;

    public LiveData ProductsViewModel() {

        ProductsDSFactory productsDSFactory = new ProductsDSFactory();
        liveDataSource = productsDSFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(10)
                        .build();

        return productsPagedList = (new LivePagedListBuilder(productsDSFactory, config)).build();

    }
}