package com.natsa.natsa20_mobile.server.process.Paging.products.user_products;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class UserProductsVM extends AndroidViewModel {

    Context context;
    LiveData productsPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;
    UserProductsDSFactory userProductsDSFactory;

    public UserProductsVM(Application application){
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData UserProductsViewModel() {

        userProductsDSFactory = new UserProductsDSFactory(context);
        liveDataSource = userProductsDSFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(10)
                        .build();

        return productsPagedList = (new LivePagedListBuilder(userProductsDSFactory, config)).build();

    }

    public void refresh() {
        if (userProductsDSFactory.getItemLiveDataSource().getValue() != null) {
            userProductsDSFactory.getItemLiveDataSource().getValue().invalidate();
        }
    }
}