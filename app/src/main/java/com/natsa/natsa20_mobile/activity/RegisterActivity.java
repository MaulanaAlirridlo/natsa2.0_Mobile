package com.natsa.natsa20_mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.fragment.Register.RegisterEmailPasswordFragment;
import com.natsa.natsa20_mobile.fragment.Register.RegisterKtpFragment;
import com.natsa.natsa20_mobile.fragment.Register.RegisterNameUsernameFragment;
import com.natsa.natsa20_mobile.server.process.auth.Register;
import com.natsa.natsa20_mobile.shared_preference.Preferences;

public class RegisterActivity extends AppCompatActivity {

    private Context context;
    private String name, email, password, password_confirmation, username, ktp;
    private Button registerButton, nextButton;
    private TextView back;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nextButton = findViewById(R.id.nextButton);
        registerButton = findViewById(R.id.registerButton);
        back = findViewById(R.id.back);
        context = getApplicationContext();
        fragmentManager = getSupportFragmentManager();

        addFragment(new RegisterNameUsernameFragment());
    }

    //onclick function
    public void nextFragment(View view) {
        getFragmentInput();
        Fragment fragment = getLastFragment();
        switch (fragment.getClass().getSimpleName()){
            case "RegisterNameUsernameFragment" :
                addFragment(new RegisterKtpFragment());
                back.setVisibility(TextView.VISIBLE);
                break;
            case "RegisterKtpFragment" :
                addFragment(new RegisterEmailPasswordFragment());
                registerButton.setVisibility(Button.VISIBLE);
                nextButton.setVisibility(Button.GONE);
                break;
            case "RegisterEmailPasswordFragment" :

                break;
        }
    }

    public void prevFragment(View view) {
        getFragmentInput();
        fragmentManager.popBackStack();
    }

    public void signup(View view) {
        getFragmentInput();
        new Register(name, email, password, password_confirmation, username, ktp)
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

    //fragment loader
    private void addFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    //get active/last fragment
    private Fragment getLastFragment() {
        int fragementIndex = getSupportFragmentManager().getBackStackEntryCount()-1;
        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager()
                .getBackStackEntryAt(fragementIndex);
        String tag = backEntry.getName();
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    //get input from last fragment
    private void getFragmentInput() {
        Fragment fragment = getLastFragment();
        switch (fragment.getClass().getSimpleName()){
            case "RegisterNameUsernameFragment" :
                name = ((EditText)fragment.getView().findViewById(R.id.name)).getText().toString();
                username = ((EditText)fragment.getView()
                        .findViewById(R.id.username)).getText().toString();
                break;
            case "RegisterKtpFragment" :
                ktp = ((EditText)fragment.getView().findViewById(R.id.ktp)).getText().toString();
                break;
            case "RegisterEmailPasswordFragment" :
                email = ((EditText)fragment.getView().findViewById(R.id.email)).getText().toString();
                password = ((EditText)fragment.getView()
                        .findViewById(R.id.password)).getText().toString();
                password_confirmation = ((EditText)fragment.getView()
                        .findViewById(R.id.password_confirmation)).getText().toString();
                break;
        }
    }
}