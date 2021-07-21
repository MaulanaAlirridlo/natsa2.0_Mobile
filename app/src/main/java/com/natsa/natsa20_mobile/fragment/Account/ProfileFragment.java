package com.natsa.natsa20_mobile.fragment.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.model.user.update_password.Request;
import com.natsa.natsa20_mobile.server.process.User.GetUser;
import com.natsa.natsa20_mobile.server.process.User.UpdatePassword;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;

public class ProfileFragment extends Fragment {
    ImageView photoProfile;
    EditText name, username, email, ktp, noHp, currentPassword, newPassword, confirmPassword;
    Button updatePasswordButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        photoProfile = view.findViewById(R.id.photoProfile);
        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        ktp = view.findViewById(R.id.ktp);
        noHp = view.findViewById(R.id.no_hp);
        currentPassword = view.findViewById(R.id.currentPassword);
        newPassword = view.findViewById(R.id.newPassword);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        updatePasswordButton = view.findViewById(R.id.update_password_button);

        new GetUser().getLoginUserFromApi(getActivity(), view, photoProfile, name, username, email, ktp, noHp);


        updatePasswordButton.setOnClickListener(v -> {
            Request request = new Request(
                    currentPassword.getText().toString(),
                    newPassword.getText().toString(),
                    confirmPassword.getText().toString()
            );
            new UpdatePassword().updateUserPassword(getContext(), request);
        });

        return view;
    }
}