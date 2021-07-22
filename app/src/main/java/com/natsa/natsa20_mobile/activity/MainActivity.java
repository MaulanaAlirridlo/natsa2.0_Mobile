package com.natsa.natsa20_mobile.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.server.Server;
import com.natsa.natsa20_mobile.server.process.auth.Logout;
import com.natsa.natsa20_mobile.helper.Preferences;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout menu, search, accountBeforeLogin, accountAfterLogin;
    ImageView showMenu, showSearch, showBookmark, showAccountMenu, mic, clearInput;
    TextView showSawah, showAbout, showFaq, showContact, showRegister, showLogin, showDashboard, logout;
    EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnclickListener();
    }

    public void onResume() {
        super.onResume();
        if (Preferences.getPhotoUrl(getApplicationContext()).contains("https://")) {
            new GlideLoader().glideImageRoundedLoader(MainActivity.this, showAccountMenu, showAccountMenu,
                    Preferences.getPhotoUrl(getApplicationContext()));
        } else {
            new GlideLoader().glideImageRoundedLoader(MainActivity.this, showAccountMenu, showAccountMenu,
                    Server.urlWithoutSlash+Preferences.getPhotoUrl(getApplicationContext()));
        }
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
        searchInput = findViewById(R.id.searchInput);
        mic = findViewById(R.id.mic);
        clearInput = findViewById(R.id.clear_input);
    }

    // event listener function
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

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    mic.setVisibility(View.GONE);
                    clearInput.setVisibility(View.VISIBLE);
                } else {
                    mic.setVisibility(View.VISIBLE);
                    clearInput.setVisibility(View.GONE);
                }
            }
        });

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent i = new Intent(MainActivity.this, BackActivity.class);
                    i.putExtra("page", "sawah");
                    i.putExtra("keyword", searchInput.getText().toString());
                    startActivity(i);
                    checkSearchVisibility();
                    return true;
                }
                return false;
            }
        });

        mic.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            openMicActivityResultLauncher.launch(Intent.createChooser(intent, "Get Voice"));
        });

        clearInput.setOnClickListener(v -> {
            searchInput.setText("");
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

    ActivityResultLauncher<Intent> openMicActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    List<String> results = result.getData().getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS);
                    String spokenText = results.get(0);
                    Intent i = new Intent(MainActivity.this, BackActivity.class);
                    i.putExtra("page", "sawah");
                    i.putExtra("keyword", spokenText);
                    startActivity(i);
                    checkSearchVisibility();
                }
            });
}