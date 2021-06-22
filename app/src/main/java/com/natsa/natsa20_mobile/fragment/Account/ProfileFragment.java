package com.natsa.natsa20_mobile.fragment.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.natsa.natsa20_mobile.R;

public class ProfileFragment extends Fragment {
    private ImageView photoProfile;
    private EditText name, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        photoProfile = view.findViewById(R.id.photoProfile);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);

        


        return view;
    }
}