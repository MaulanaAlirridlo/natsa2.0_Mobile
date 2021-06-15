package com.natsa.natsa20_mobile.fragment;

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
import com.natsa.natsa20_mobile.server.process.products.GetProduct;
import com.natsa.natsa20_mobile.server.process.products.GetProducts;

public class ProductFragment extends Fragment{

    private ProductAdapter productAdapter;
    private ProductsAdapter productsAdapter;
    private GetProduct getProduct = new GetProduct();
    private GetProducts getProducts = new GetProducts();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        //show product
        RecyclerView productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productAdapter = new ProductAdapter(GetProduct.getProductList());
        RecyclerView.LayoutManager productLayoutManager = new LinearLayoutManager(getActivity());
        productRecyclerView.setLayoutManager(productLayoutManager);
        productRecyclerView.setAdapter(productAdapter);

        //show products
        RecyclerView productsRecyclerView = view.findViewById(R.id.productsRecyclerView);
        productsAdapter = new ProductsAdapter(getContext(), GetProducts.getProductsDataList());
        RecyclerView.LayoutManager productsLayoutManager = new LinearLayoutManager(getActivity());
        productsRecyclerView.setLayoutManager(productsLayoutManager);
        productsRecyclerView.setAdapter(productsAdapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProduct.getProductFromApi((Integer) getActivity().getIntent().getExtras().get("id"), productAdapter);
                getProducts.getProductsFromApi(productsAdapter);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.setRefreshing(false);
                    }
                }, 700);
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        getProduct.getProductFromApi((Integer) getActivity().getIntent().getExtras().get("id"), productAdapter);
        getProducts.getProductsFromApi(productsAdapter);
    }
}