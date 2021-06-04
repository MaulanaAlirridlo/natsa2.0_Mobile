package com.maulana.natsa20_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BackActivity extends AppCompatActivity {

    String page;
    TextView title;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        title = findViewById(R.id.title);

        Bundle extras = getIntent().getExtras();
        page = extras.getString("page");
        switch (page) {
            case "sawah" :
                loadFragment(new ProductsFragment());
                title.setText("Sawah");
            break;
            case "about" :
                loadFragment(new AboutFragment());
                title.setText("About");
            break;
            case "FAQ" :
                loadFragment(new FaqFragment());
                title.setText("FAQ");
            break;
            case "contact" :
                loadFragment(new ContactFragment());
                title.setText("Our Contact");
            break;
            case "email" :
                loadFragment(new EmailFragment());
                title.setText("Send Email");
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