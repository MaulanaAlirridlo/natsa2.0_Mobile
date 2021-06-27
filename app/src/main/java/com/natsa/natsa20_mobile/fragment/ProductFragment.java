package com.natsa.natsa20_mobile.fragment;

import android.content.Context;
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
import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.adapter.RandomRiceFieldsAdapter;
import com.natsa.natsa20_mobile.server.process.products.GetProduct;
import com.natsa.natsa20_mobile.server.process.products.GetProducts;

public class ProductFragment extends Fragment {

    private ProductAdapter productAdapter;
    private RandomRiceFieldsAdapter randomRiceFieldsAdapter;
    private final GetProduct getProduct = new GetProduct();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        Context context = getContext();

        //show product
        RecyclerView productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productAdapter = new ProductAdapter(context, GetProduct.getProductList());
        RecyclerView.LayoutManager productLayoutManager = new LinearLayoutManager(getActivity());
        productRecyclerView.setLayoutManager(productLayoutManager);
        productRecyclerView.setAdapter(productAdapter);

        //show random rice fields
        RecyclerView randomRiceFieldsRecyclerView = view.findViewById(R.id.randomRiceFieldsRecyclerView);
        randomRiceFieldsAdapter = new RandomRiceFieldsAdapter(context, GetProduct.getRandomRiceFields());
        RecyclerView.LayoutManager randomRiceFieldsLayoutManager = new LinearLayoutManager(getActivity());
        randomRiceFieldsRecyclerView.setLayoutManager(randomRiceFieldsLayoutManager);
        randomRiceFieldsRecyclerView.setAdapter(randomRiceFieldsAdapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            getProduct.getProductFromApi((Integer) getActivity().getIntent().getExtras().get("id"), productAdapter, randomRiceFieldsAdapter);
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        getProduct.getProductFromApi((Integer) getActivity().getIntent().getExtras().get("id"), productAdapter, randomRiceFieldsAdapter);
    }
}