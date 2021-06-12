package com.maulana.natsa20_mobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maulana.natsa20_mobile.R;
import com.maulana.natsa20_mobile.adapter.ProductsAdapter;
import com.maulana.natsa20_mobile.server.process.ProductsApiProcess;

public class ProductFragment extends Fragment {

    ProductsAdapter adapter;
    ProductsApiProcess productsApiProcessClass = new ProductsApiProcess();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.productRecyclerView);
        adapter = new ProductsAdapter(getActivity(), ProductsApiProcess.getProductsDataList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onResume() {
        super.onResume();
        productsApiProcessClass.getDataFromApi(adapter);
    }
}