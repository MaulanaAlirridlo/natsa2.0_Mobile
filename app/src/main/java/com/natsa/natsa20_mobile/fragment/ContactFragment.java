package com.natsa.natsa20_mobile.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.natsa.natsa20_mobile.activity.BackActivity;
import com.natsa.natsa20_mobile.R;

public class ContactFragment extends Fragment {

    View view;
    LinearLayout emailLayout, phoneLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        emailLayout = view.findViewById(R.id.emailLayout);
        phoneLayout = view.findViewById(R.id.phoneLayout);
        emailLayout.setOnClickListener(this::showEmail);
        phoneLayout.setOnClickListener(this::showCall);
        return view;
    }

    // onclick function

    public void showEmail(View v) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:natsa@email.com"));
        startActivity(i);
    }

    public void showCall(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }

}