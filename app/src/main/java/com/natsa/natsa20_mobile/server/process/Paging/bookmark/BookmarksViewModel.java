package com.natsa.natsa20_mobile.server.process.Paging.bookmark;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;

public class BookmarksViewModel extends AndroidViewModel {

    Context context;
    LiveData productsPagedList;
    LiveData<PageKeyedDataSource<Integer, Data>> liveDataSource;
    BookmarksDSFactory bookmarksDSFactory;

    public BookmarksViewModel(Application application){
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData BookmarksViewModel() {

        bookmarksDSFactory = new BookmarksDSFactory(context);
        liveDataSource = bookmarksDSFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(10)
                        .build();

        return productsPagedList = (new LivePagedListBuilder(bookmarksDSFactory, config)).build();

    }

    public void refresh() {
        if (bookmarksDSFactory.getItemLiveDataSource().getValue() != null) {
            bookmarksDSFactory.getItemLiveDataSource().getValue().invalidate();
        }
    }
}
