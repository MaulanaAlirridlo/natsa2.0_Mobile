package com.natsa.natsa20_mobile.fragment;

import android.annotation.SuppressLint;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.process.Paging.products.ProductsViewModel;

public class ProductsFragment extends Fragment {

    ProductsAdapter adapter;
    ProductsViewModel productsViewModel;
    TextView searchKeyword, noData;
    String keyword, sort;
    Integer maxharga, minharga, maxluas, minluas;
    ImageView showFilter;
    LinearLayout filter;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.productsRecyclerView);
        adapter = new ProductsAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchKeyword = view.findViewById(R.id.search_keyword);
        noData = view.findViewById(R.id.no_data);
        showFilter = view.findViewById(R.id.show_filter);
        filter = view.findViewById(R.id.filter);
        keyword = getActivity().getIntent().getExtras().getString("keyword");
        maxharga = null;
        minharga = null;
        maxluas = null;
        minluas = null;

        if (keyword == null) {
            searchKeyword.setVisibility(TextView.GONE);
        } else {
            searchKeyword.setText("Menampilakan hasil dari '" + keyword + "'");
        }

        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.customConstructor(keyword, maxharga, minharga, maxluas, minluas, sort, noData);

        productsViewModel.ProductsViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
            @Override
            public void onChanged(@Nullable PagedList<Data> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);

        showFilter.setOnClickListener(v -> {
            if (filter.getVisibility() == TextView.GONE) {
                filter.setVisibility(TextView.VISIBLE);
            } else {
                filter.setVisibility(TextView.GONE);
            }
        });

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            productsViewModel.refresh();
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        productsViewModel.refresh();
    }
}