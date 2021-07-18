package com.natsa.natsa20_mobile.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.MakelarAdapter;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.adapter.ProductsAdapterWithPaging;
import com.natsa.natsa20_mobile.server.process.makelar.GetMakelar;

public class MakelarFragment extends Fragment {

    private MakelarAdapter makelarAdapter;
    private ProductsAdapter productsAdapter;
    private final GetMakelar getMakelar = new GetMakelar();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_makelar, container, false);

        Activity activity = getActivity();

        RecyclerView makelarRecyclerView = view.findViewById(R.id.makelarRecyclerView);
        makelarAdapter = new MakelarAdapter(activity, GetMakelar.getMakelarData());
        RecyclerView.LayoutManager makelarLayoutManager = new LinearLayoutManager(getActivity());
        makelarRecyclerView.setLayoutManager(makelarLayoutManager);
        makelarRecyclerView.setAdapter(makelarAdapter);

        RecyclerView riceFieldsRecyclerView = view.findViewById(R.id.riceFieldsRecyclerView);
        productsAdapter = new ProductsAdapter(activity, GetMakelar.getRiceFieldDataList());
        RecyclerView.LayoutManager productsLayoutManager = new LinearLayoutManager(getActivity());
        riceFieldsRecyclerView.setLayoutManager(productsLayoutManager);
        riceFieldsRecyclerView.setAdapter(productsAdapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            getMakelar.getMakelarFromApi(getContext(), (Integer) getActivity().getIntent().getExtras().get("makelar_id"),
                    makelarAdapter, productsAdapter);
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        getMakelar.getMakelarFromApi(getContext(), (Integer) getActivity().getIntent().getExtras().get("makelar_id"),
                makelarAdapter, productsAdapter);
    }
}