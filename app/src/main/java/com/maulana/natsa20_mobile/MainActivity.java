package com.maulana.natsa20_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
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

    public void showFAQ(View v) {
        Intent i = new Intent(MainActivity.this, BackActivity.class);
        i.putExtra("page", "FAQ");
        startActivity(i);
        menu.setVisibility(LinearLayout.GONE);
    }

    public void showContact(View v) {
        Intent i = new Intent(MainActivity.this, BackActivity.class);
        i.putExtra("page", "contact");
        startActivity(i);
        menu.setVisibility(LinearLayout.GONE);
    }
}