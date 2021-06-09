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
import com.maulana.natsa20_mobile.model.Products;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private ArrayList<Products> productsArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        addData();

        RecyclerView recyclerView = view.findViewById(R.id.productRecyclerView);
        ProductsAdapter adapter = new ProductsAdapter(getActivity(), productsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void addData(){
        productsArrayList = new ArrayList<>();
        productsArrayList.add(new Products("1","Sawah 1", "100000000", "@drawable/bg"));
        productsArrayList.add(new Products("2", "Sawah 2", "200000000", "@drawable/bg"));
        productsArrayList.add(new Products("3", "Sawah 3", "300000000", "@drawable/bg"));
        productsArrayList.add(new Products("4", "Sawah 4", "400000000", "@drawable/bg"));
    }
}