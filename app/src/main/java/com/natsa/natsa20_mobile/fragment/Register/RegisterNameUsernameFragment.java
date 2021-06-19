package com.natsa.natsa20_mobile.fragment.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.natsa.natsa20_mobile.R;

public class RegisterNameUsernameFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_name_username, container, false);
    }
}