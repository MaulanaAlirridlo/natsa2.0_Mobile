package com.maulana.natsa20_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class BackActivity extends AppCompatActivity {

    private String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        Bundle extras = getIntent().getExtras();
        page = extras.getString("page");
        switch (page) {
            case "about" :

            break;
            case "FAQ" :

            break;
            case "contact" :
                loadFragment(new ContactFragment());
            break;
            case "email" :
                loadFragment(new EmailFragment());
            break;
        }
    }

    // onclick function

    public void back(View v) {
        this.finish();
    }


    //fragment loader
    private  void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}