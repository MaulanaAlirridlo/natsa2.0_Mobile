package com.maulana.natsa20_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    LinearLayout menu, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init vairable
        myDialog = new Dialog(this);
        menu = this.findViewById(R.id.menu);
        search = this.findViewById(R.id.search);
    }


    // onClick function

    public void ShowMenu(View v) {
        if (menu.getVisibility() == LinearLayout.GONE) {
            menu.setVisibility(LinearLayout.VISIBLE);
        } else {
            menu.setVisibility(LinearLayout.GONE);
        }
    }

    public void ShowSearch(View v) {
        if (search.getVisibility() == LinearLayout.GONE) {
            search.setVisibility(LinearLayout.VISIBLE);
        } else {
            search.setVisibility(LinearLayout.GONE);
        }
    }

//    public void ShowContact(View v) {
//        loadFragment(new ContactFragment());
//        menu.setVisibility(LinearLayout.GONE);
//    }
//
//    //fragment loader
//    private  void loadFragment(Fragment fragment) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        fragmentTransaction.commit();
//    }
}