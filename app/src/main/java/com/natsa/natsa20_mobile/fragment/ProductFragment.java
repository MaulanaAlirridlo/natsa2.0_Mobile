package com.natsa.natsa20_mobile.fragment;

import android.app.Activity;
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
import android.widget.Button;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.MakelarAdapter;
import com.natsa.natsa20_mobile.adapter.ProductAdapter;
import com.natsa.natsa20_mobile.adapter.ProductImageAdapter;
import com.natsa.natsa20_mobile.adapter.RandomRiceFieldsAdapter;
import com.natsa.natsa20_mobile.server.process.User.GetUser;
import com.natsa.natsa20_mobile.server.process.irrigations.GetIrrigations;
import com.natsa.natsa20_mobile.server.process.products.GetProduct;
import com.natsa.natsa20_mobile.server.process.regions.GetRegions;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;
import com.smarteist.autoimageslider.SliderView;

public class ProductFragment extends Fragment {

    private ProductAdapter productAdapter;
    private MakelarAdapter makelarAdapter;
    private RandomRiceFieldsAdapter randomRiceFieldsAdapter;
    private ProductImageAdapter productImageAdapter;
    private final GetProduct getProduct = new GetProduct();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        Activity activity = getActivity();

        //show product
        RecyclerView productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productImageAdapter = new ProductImageAdapter(getActivity(), GetProduct.getProductImageList());
        productAdapter = new ProductAdapter(activity, GetProduct.getProductList(), productImageAdapter);
        RecyclerView.LayoutManager productLayoutManager = new LinearLayoutManager(getActivity());
        productRecyclerView.setLayoutManager(productLayoutManager);
        productRecyclerView.setAdapter(productAdapter);

        //show makelar
        RecyclerView makelarRecyclerView = view.findViewById(R.id.makelarRecyclerView);
        makelarAdapter = new MakelarAdapter(activity, GetUser.getUserData());
        RecyclerView.LayoutManager makelarLayoutManager = new LinearLayoutManager(getActivity());
        makelarRecyclerView.setLayoutManager(makelarLayoutManager);
        makelarRecyclerView.setAdapter(makelarAdapter);

        //show random rice fields
        RecyclerView randomRiceFieldsRecyclerView = view.findViewById(R.id.randomRiceFieldsRecyclerView);
        randomRiceFieldsAdapter = new RandomRiceFieldsAdapter(activity, GetProduct.getRandomRiceFieldsList());
        RecyclerView.LayoutManager randomRiceFieldsLayoutManager = new LinearLayoutManager(getActivity());
        randomRiceFieldsRecyclerView.setLayoutManager(randomRiceFieldsLayoutManager);
        randomRiceFieldsRecyclerView.setAdapter(randomRiceFieldsAdapter);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            getProduct.getProductFromApi((Integer) getActivity().getIntent().getExtras().get("id"),
                    productAdapter, makelarAdapter, productImageAdapter, randomRiceFieldsAdapter);
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        getProduct.getProductFromApi((Integer) getActivity().getIntent().getExtras().get("id"),
                productAdapter, makelarAdapter, productImageAdapter, randomRiceFieldsAdapter);
    }
}