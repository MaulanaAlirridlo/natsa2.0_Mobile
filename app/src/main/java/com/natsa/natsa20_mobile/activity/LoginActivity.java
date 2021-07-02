package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.server.process.auth.Login;
import com.natsa.natsa20_mobile.helper.Preferences;

public class LoginActivity extends AppCompatActivity {
    Context context;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        context = getApplicationContext();

        if (Preferences.isLogin(context)) {
            startMainActivity();
        }
    }

    //onclick function
    public void login(View view) {
        new Login(email.getText().toString(), password.getText().toString())
                .LoginProcess(LoginActivity.this);
    }

    public void showRegister(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    public void skip(View view) {
        startMainActivity();
    }

    private void startMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}