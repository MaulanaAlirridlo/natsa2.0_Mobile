package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.server.process.auth.Logout;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    LinearLayout menu, search, accountBeforeLogin, accountAfterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init variable
        myDialog = new Dialog(this);
        menu = this.findViewById(R.id.menu);
        search = this.findViewById(R.id.search);
        accountBeforeLogin = this.findViewById(R.id.accountBeforeLogin);
        accountAfterLogin = this.findViewById(R.id.accountAfterLogin);
    }


    // onClick function

    public void showMenu(View view) {
        if (menu.getVisibility() == LinearLayout.GONE) {
            menu.setVisibility(LinearLayout.VISIBLE);
        } else {
            menu.setVisibility(LinearLayout.GONE);
        }
        checkSearchVisibility();
        checkAccountVisibility();
    }

    public void showSearch(View view) {
        if (search.getVisibility() == LinearLayout.GONE) {
            search.setVisibility(LinearLayout.VISIBLE);
        } else {
            search.setVisibility(LinearLayout.GONE);
        }
        checkMenuVisibility();
        checkAccountVisibility();
    }

    public void showAccount(View view) {
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
    }

    public void showSawah(View view) {
        showBackActivity("sawah");
    }

    public void showAbout(View view) {
        showBackActivity("about");
    }

    public void showFAQ(View view) {
        showBackActivity("FAQ");
    }

    public void showContact(View view) {
        showBackActivity("contact");
    }

    public void showRegister(View view) {
        Intent i = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    public void showLogin(View view) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void showDashboard(View view) {
        showAccountPage("Dashboard");
    }

    public void showBookmark(View view) {
        showAccountPage("Bookmark");
    }

    public void logout(View view) {
        new Logout().LogoutProcess(MainActivity.this);
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