package com.natsa.natsa20_mobile.server.process.Paging.bookmark;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;;import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;

public class BookmarksDSFactory extends DataSource.Factory {

    Context context;

    public BookmarksDSFactory(Context context) {
        this.context = context;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Data>> bookmarksLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        BookmarksDataSource productsDataSource = new BookmarksDataSource(context);
        bookmarksLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Data>> getItemLiveDataSource() {
        return bookmarksLiveDataSource;
    }
}
