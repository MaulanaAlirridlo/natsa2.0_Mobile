package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.server.Server;
import com.natsa.natsa20_mobile.server.process.User.GetUser;
import com.natsa.natsa20_mobile.server.process.auth.Login;
import com.natsa.natsa20_mobile.helper.Preferences;

public class LoginActivity extends AppCompatActivity {
    Context context;
    private EditText email, password;
    TextView showForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        if (Preferences.isLogin(context)) {
            new GetUser().getLoginUserFromApiWithoutSetView(context);
            startMainActivity();
        }
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        showForgetPassword = findViewById(R.id.forget_password);

        showForgetPassword.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(Server.forgetPassword));
            startActivity(i);
        });
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