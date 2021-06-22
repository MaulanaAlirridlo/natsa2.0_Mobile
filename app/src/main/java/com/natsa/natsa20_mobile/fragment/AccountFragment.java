package com.natsa.natsa20_mobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.fragment.Account.BookmarkFragment;
import com.natsa.natsa20_mobile.fragment.Account.DashboardFragment;
import com.natsa.natsa20_mobile.fragment.Account.JualSewakanFragment;
import com.natsa.natsa20_mobile.fragment.Account.ProfileFragment;

public class AccountFragment extends Fragment {
    TextView dashboard, bookmark, jualSewakan, profile, title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        dashboard = view.findViewById(R.id.dashboard);
        bookmark = view.findViewById(R.id.bookmark);
        jualSewakan = view.findViewById(R.id.jualSewakan);
        profile = view.findViewById(R.id.profile);
        title = getActivity().findViewById(R.id.title);

        dashboard.setOnClickListener(v -> openPage("Dashboard"));

        bookmark.setOnClickListener(v -> openPage("Bookmark"));

        jualSewakan.setOnClickListener(v -> openPage("Jual/Sewakan"));

        profile.setOnClickListener(v -> openPage("Profile"));

        openPage("Dashboard");

        return view;
    }

    private void openPage(String page) {
        title.setText(page);
        switch (page) {
            case "Dashboard":
                loadFragment(new DashboardFragment());
                break;
            case "Bookmark":
                loadFragment(new BookmarkFragment());
                break;
            case "Jual/Sewakan":
                loadFragment(new JualSewakanFragment());
                break;
            case "Profile":
                loadFragment(new ProfileFragment());
                break;
        }
    }

    //fragment loader
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.accountFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}