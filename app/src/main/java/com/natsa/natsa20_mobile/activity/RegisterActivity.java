package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.server.process.auth.Register;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

public class RegisterActivity extends AppCompatActivity {

    private Context context;
    private EditText name, email, password, password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);
        context = getApplicationContext();
    }

    //onclick function
    public void signup(View view) {
        new Register(name.getText().toString(), email.getText().toString(),
                password.getText().toString(), password_confirmation.getText().toString())
                .RegisterProcess(RegisterActivity.this);
        if (Preferences.getToken(context) != null) {
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void showLogin(View view){
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}