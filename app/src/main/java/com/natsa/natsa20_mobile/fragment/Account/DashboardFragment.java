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
import com.natsa.natsa20_mobile.adapter.HistoryAdapter;
import com.natsa.natsa20_mobile.adapter.UserProductsAdapter;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.process.Paging.history.HistoryViewModel;
import com.natsa.natsa20_mobile.server.process.Paging.products.user_products.UserProductsVM;

public class DashboardFragment extends Fragment {

    HistoryAdapter adapter;
    HistoryViewModel historyViewModel;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.history_recycler_view);
        adapter = new HistoryAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        historyViewModel.HistoryViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
            @Override
            public void onChanged(@Nullable PagedList<Data> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            historyViewModel.refresh();
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;

    }

    public void onResume() {
        super.onResume();
        historyViewModel.refresh();
    }
}