package com.natsa.natsa20_mobile.server.process.Paging.history;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.natsa.natsa20_mobile.model.products.products.Data;

public class HistoryDSFactory extends DataSource.Factory {

    Context context;

    public HistoryDSFactory(Context context) {
        this.context = context;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> historyLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        HistoryDataSource historyDataSource = new HistoryDataSource(context);
        historyLiveDataSource.postValue(historyDataSource);
        return historyDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return historyLiveDataSource;
    }
}
