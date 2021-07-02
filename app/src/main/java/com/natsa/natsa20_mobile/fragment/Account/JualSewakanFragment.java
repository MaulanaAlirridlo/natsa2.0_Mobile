package com.natsa.natsa20_mobile.fragment.Account;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.natsa.natsa20_mobile.adapter.UserProductsadapter;
import com.natsa.natsa20_mobile.server.process.products.GetUserProducts;
import com.natsa.natsa20_mobile.helper.Preferences;

public class JualSewakanFragment extends Fragment {

    FloatingActionButton showAddProductForm;
    UserProductsadapter adapter;
    GetUserProducts getUserProducts = new GetUserProducts();
    Context context;
    int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jual_sewakan, container, false);

        context = getContext();
        showAddProductForm = view.findViewById(R.id.show_add_product_form);
        userId = Preferences.getId(context);


        RecyclerView recyclerView = view.findViewById(R.id.jual_sewakan_recycler_view);
        adapter = new UserProductsadapter(context, GetUserProducts.getUserProductsDataList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        showAddProductForm.setOnClickListener(v -> {
            loadFragment(new AddProductFragment());
        });

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            getUserProducts.getUserProductsFromApi(adapter, userId);
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        getUserProducts.getUserProductsFromApi(adapter, userId);
    }

    //fragment loader
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.accountFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}