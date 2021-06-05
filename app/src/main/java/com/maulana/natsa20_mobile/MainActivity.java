package com.maulana.natsa20_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.maulana.natsa20_mobile.adapter.ProductsAdapter;

public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    LinearLayout menu, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init variable
        myDialog = new Dialog(this);
        menu = this.findViewById(R.id.menu);
        search = this.findViewById(R.id.search);
    }


    // onClick function

    public void showMenu(View v) {
        if (menu.getVisibility() == LinearLayout.GONE) {
            menu.setVisibility(LinearLayout.VISIBLE);
        } else {
            menu.setVisibility(LinearLayout.GONE);
        }
    }

    public void showSearch(View v) {
        if (search.getVisibility() == LinearLayout.GONE) {
            search.setVisibility(LinearLayout.VISIBLE);
        } else {
            search.setVisibility(LinearLayout.GONE);
        }
    }

    public void showSawah(View v) {
        showProcces("sawah");
    }



    public void showAbout(View v) {
        showProcces("about");
    }

    public void showFAQ(View v) {
        showProcces("FAQ");
    }

    public void showContact(View v) {
        showProcces("contact");
    }

    private void showProcces(String page){
        Intent i = new Intent(MainActivity.this, BackActivity.class);
        i.putExtra("page", page);
        startActivity(i);
        menu.setVisibility(LinearLayout.GONE);
    }
}