package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.server.process.auth.Logout;
import com.natsa.natsa20_mobile.helper.Preferences;

public class MainActivity extends AppCompatActivity {

    LinearLayout menu, search, accountBeforeLogin, accountAfterLogin;
    ImageView showMenu, showSearch, showBookmark, showAccountMenu;
    TextView showSawah, showAbout, showFaq, showContact, showRegister, showLogin, showDashboard, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnclickListener();
    }


    // init variable
    private void init() {
        menu = findViewById(R.id.menu);
        search = findViewById(R.id.search);
        accountBeforeLogin = findViewById(R.id.accountBeforeLogin);
        accountAfterLogin = findViewById(R.id.accountAfterLogin);
        showMenu = findViewById(R.id.show_menu);
        showSearch = findViewById(R.id.show_search);
        showBookmark = findViewById(R.id.show_bookmark);
        showAccountMenu = findViewById(R.id.show_account_menu);
        showSawah = findViewById(R.id.show_sawah);
        showAbout = findViewById(R.id.show_about);
        showFaq = findViewById(R.id.show_faq);
        showContact = findViewById(R.id.show_contact);
        showRegister = findViewById(R.id.show_register);
        showLogin = findViewById(R.id.show_login);
        showDashboard = findViewById(R.id.show_dashboard);
        logout = findViewById(R.id.logout);
    }

    // onClick function
    private void setOnclickListener() {
        showMenu.setOnClickListener(v -> {
            if (menu.getVisibility() == LinearLayout.GONE) {
                menu.setVisibility(LinearLayout.VISIBLE);
            } else {
                menu.setVisibility(LinearLayout.GONE);
            }
            checkSearchVisibility();
            checkAccountVisibility();
        });

        showSearch.setOnClickListener(v -> {
            if (search.getVisibility() == LinearLayout.GONE) {
                search.setVisibility(LinearLayout.VISIBLE);
            } else {
                search.setVisibility(LinearLayout.GONE);
            }
            checkMenuVisibility();
            checkAccountVisibility();
        });

        showAccountMenu.setOnClickListener(v -> {
            //cek login
            if (Preferences.isLogin(getApplicationContext())) {
                if (accountAfterLogin.getVisibility() == LinearLayout.GONE) {
                    accountAfterLogin.setVisibility(LinearLayout.VISIBLE);
                } else {
                    accountAfterLogin.setVisibility(LinearLayout.GONE);
                }
            } else {
                if (accountBeforeLogin.getVisibility() == LinearLayout.GONE) {
                    accountBeforeLogin.setVisibility(LinearLayout.VISIBLE);
                } else {
                    accountBeforeLogin.setVisibility(LinearLayout.GONE);
                }
            }
            checkSearchVisibility();
            checkMenuVisibility();

        });

        showSawah.setOnClickListener(v -> {
            showBackActivity("sawah");

        });

        showAbout.setOnClickListener(v -> {
            showBackActivity("about");

        });

        showFaq.setOnClickListener(v -> {
            showBackActivity("FAQ");

        });

        showContact.setOnClickListener(v -> {
            showBackActivity("contact");

        });

        showRegister.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
            finish();
        });

        showLogin.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });

        showDashboard.setOnClickListener(v -> {
            showAccountPage("Dashboard");

        });

        showBookmark.setOnClickListener(v -> {
            showAccountPage("Bookmark");

        });

        logout.setOnClickListener(v -> {
            new Logout().LogoutProcess(MainActivity.this);

        });
    }

    //helper funtion
    private void showBackActivity(String page) {
        Intent i = new Intent(MainActivity.this, BackActivity.class);
        i.putExtra("page", page);
        startActivity(i);
        menu.setVisibility(LinearLayout.GONE);
    }

    private void showAccountPage(String page) {
        Intent i = new Intent(MainActivity.this, BackActivity.class);
        i.putExtra("page", "account");
        i.putExtra("accountPage", page);
        startActivity(i);
        checkAccountVisibility();
    }


    private void checkSearchVisibility() {
        if (search.getVisibility() == LinearLayout.VISIBLE) {
            search.setVisibility(LinearLayout.GONE);
        }
    }

    private void checkMenuVisibility() {
        if (menu.getVisibility() == LinearLayout.VISIBLE) {
            menu.setVisibility(LinearLayout.GONE);
        }
    }

    private void checkAccountVisibility() {
        if (accountBeforeLogin.getVisibility() == LinearLayout.VISIBLE) {
            accountBeforeLogin.setVisibility(LinearLayout.GONE);
        }
        if (accountAfterLogin.getVisibility() == LinearLayout.VISIBLE) {
            accountAfterLogin.setVisibility(LinearLayout.GONE);
        }
    }
}