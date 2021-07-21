package com.natsa.natsa20_mobile.fragment.Account;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.UserProductsAdapter;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.process.Paging.products.user_products.UserProductsVM;

public class JualSewakanFragment extends Fragment {

    FloatingActionButton showAddProductForm;
    UserProductsAdapter adapter;
    UserProductsVM userProductsVM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jual_sewakan, container, false);

        showAddProductForm = view.findViewById(R.id.show_add_product_form);


        RecyclerView recyclerView = view.findViewById(R.id.jual_sewakan_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userProductsVM = ViewModelProviders.of(this).get(UserProductsVM.class);
        adapter = new UserProductsAdapter(getActivity(), userProductsVM);

        userProductsVM.UserProductsViewModel().observe(getActivity(), new Observer<PagedList<Data>>() {
            @Override
            public void onChanged(@Nullable PagedList<Data> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);

        showAddProductForm.setOnClickListener(v -> {
            loadFragment(new AddProductFragment(null));
        });

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            userProductsVM.refresh();
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        userProductsVM.refresh();
    }

    //fragment loader
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.accountFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}