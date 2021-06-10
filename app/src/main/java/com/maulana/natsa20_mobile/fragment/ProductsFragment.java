package com.maulana.natsa20_mobile.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maulana.natsa20_mobile.R;
import com.maulana.natsa20_mobile.adapter.ProductsAdapter;
import com.maulana.natsa20_mobile.model.Products;
import com.maulana.natsa20_mobile.server.Server;
import com.maulana.natsa20_mobile.server.process.products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    ProductsAdapter adapter;
    products productsClass = new products();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.productsRecyclerView);
        adapter = new ProductsAdapter(getActivity(), products.getProductsArrayList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productsClass.getData(getContext(), adapter, null);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        productsClass.getData(getContext(), adapter, null);
    }
}