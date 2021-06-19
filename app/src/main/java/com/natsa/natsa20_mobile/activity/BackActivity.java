package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.ProductsAdapter;
import com.natsa.natsa20_mobile.fragment.AboutFragment;
import com.natsa.natsa20_mobile.fragment.ContactFragment;
import com.natsa.natsa20_mobile.fragment.EmailFragment;
import com.natsa.natsa20_mobile.fragment.FaqFragment;
import com.natsa.natsa20_mobile.fragment.ProductFragment;
import com.natsa.natsa20_mobile.fragment.ProductsFragment;

public class BackActivity extends AppCompatActivity implements
        ProductsAdapter.showDetailSawahListener {

    String page;
    TextView title;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        title = findViewById(R.id.title);

        page = getIntent().getExtras().getString("page");
        switch (page) {
            case "sawah" :
                loadFragment(new ProductsFragment());
                title.setText("Sawah");
                break;
            case "detailSawah" :
                loadFragment(new ProductFragment());
                title.setText("Detail Sawah");
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

    public void showDetailSawah(int id) {
        showProces("detailSawah", id);
    }


    //fragment loader
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    // process
    private void showProces(String page, int id){
        Intent i = new Intent(BackActivity.this, BackActivity.class);
        i.putExtra("page", page);
        i.putExtra("id", id);
        startActivity(i);
    }
}