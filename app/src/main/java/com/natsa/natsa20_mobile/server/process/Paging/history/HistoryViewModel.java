package com.natsa.natsa20_mobile.server.process.Paging.history;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class HistoryViewModel extends AndroidViewModel {

    Context context;
    LiveData productsPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;
    HistoryDSFactory historyDSFactory;

    public HistoryViewModel(Application application){
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData HistoryViewModel() {

        historyDSFactory = new HistoryDSFactory(context);
        liveDataSource = historyDSFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(5)
                        .build();

        return productsPagedList = (new LivePagedListBuilder(historyDSFactory, config)).build();

    }

    public void refresh() {
        if (historyDSFactory.getItemLiveDataSource().getValue() != null) {
            historyDSFactory.getItemLiveDataSource().getValue().invalidate();
        }
    }
}
