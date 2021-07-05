package com.natsa.natsa20_mobile.fragment.Account;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.BookmarkAdapater;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;
import com.natsa.natsa20_mobile.server.process.Paging.bookmark.BookmarksViewModel;
import com.natsa.natsa20_mobile.server.process.Paging.products.user_products.UserProductsVM;
import com.natsa.natsa20_mobile.server.process.bookmark.GetBookmark;
import com.natsa.natsa20_mobile.server.process.products.GetProducts;

public class BookmarkFragment extends Fragment {

    private BookmarkAdapater adapter;
    private GetBookmark getBookmark = new GetBookmark();
    private Context context;
    BookmarksViewModel bookmarksViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.productsRecyclerView);
        context = getContext();
        adapter = new BookmarkAdapater(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookmarksViewModel = ViewModelProviders.of(this).get(BookmarksViewModel.class);
        bookmarksViewModel.BookmarksViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
            @Override
            public void onChanged(@Nullable PagedList<Data> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            bookmarksViewModel.refresh();
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        bookmarksViewModel.refresh();
    }
}