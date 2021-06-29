package com.natsa.natsa20_mobile.fragment.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.fragment.AddProductFragment;

public class JualSewakanFragment extends Fragment {

    FloatingActionButton showAddProductForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jual_sewakan, container, false);
        showAddProductForm = view.findViewById(R.id.show_add_product_form);

        showAddProductForm.setOnClickListener(v -> {
            loadFragment(new AddProductFragment());
        });
        return view;
    }

    //fragment loader
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.accountFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}