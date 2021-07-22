package com.natsa.natsa20_mobile.fragment.Account;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.adapter.AttachmentListAdapter;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.PathUri;
import com.natsa.natsa20_mobile.model.AttachmentListData;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.model.user.update_password.Request;
import com.natsa.natsa20_mobile.server.process.User.DeleteUser;
import com.natsa.natsa20_mobile.server.process.User.GetUser;
import com.natsa.natsa20_mobile.server.process.User.UpdatePassword;
import com.natsa.natsa20_mobile.server.process.User.UpdateProfile;
import com.natsa.natsa20_mobile.server.process.bookmark.DeleteBookmark;
import com.natsa.natsa20_mobile.server.process.irrigations.GetIrrigations;
import com.natsa.natsa20_mobile.server.process.products.GetRiceField;
import com.natsa.natsa20_mobile.server.process.regions.GetRegions;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileFragment extends Fragment {
    ImageView photoProfile;
    EditText name, username, email, ktp, noHp, currentPassword, newPassword, confirmPassword;
    TextView updateImageButton;
    Button updateProfileButton, updatePasswordButton, deleteUserButton;
    AttachmentListData newAttachment;
    final Integer REQUEST_CODE = 2545;
    Activity activity;

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
        updateImageButton = view.findViewById(R.id.update_image_button);
        updateProfileButton = view.findViewById(R.id.update_profile_button);
        updatePasswordButton = view.findViewById(R.id.update_password_button);
        deleteUserButton = view.findViewById(R.id.delete_user_button);

        new GetUser().getLoginUserFromApi(getActivity(), view, photoProfile, name, username, email, ktp, noHp);

        activity = getActivity();

        updateProfileButton.setOnClickListener(v -> {
            MultipartBody.Part productImagesParts;
            File file = new File(newAttachment.getImagePath());
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            productImagesParts = MultipartBody.Part.createFormData("photo",
                    file.getName(), body);

            new UpdateProfile().uploadDataProfile(getActivity(),
                    RequestBody.create(MediaType.parse("text/plain"), name.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), email.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), username.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), ktp.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), noHp.getText().toString()),
                    productImagesParts);
        });

        updatePasswordButton.setOnClickListener(v -> {
            Request request = new Request(
                    currentPassword.getText().toString(),
                    newPassword.getText().toString(),
                    confirmPassword.getText().toString()
            );
            new UpdatePassword().updateUserPassword(getContext(), request);
        });

        updateImageButton.setOnClickListener(v -> {
            openGalery();
        });

        deleteUserButton.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(activity)
                    .setMessage("Apakah anda yakin ingin menghapusnya?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setNegativeButton("Tidak", null)
                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            new DeleteUser().deleteLoginUser(getContext());
                        }
                    })
                    .create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface a) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                            activity.getResources().getColor(R.color.black));
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources()
                            .getColor(R.color.black));
                }
            });
            dialog.show();
        });

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            new GetUser().getLoginUserFromApi(getActivity(), view, photoProfile, name, username, email, ktp, noHp);
            new Handler(Looper.getMainLooper()).postDelayed(() -> pullToRefresh.setRefreshing(false), 700);
        });

        return view;
    }

    private void openGalery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                openGaleryActivityResultLauncher.launch(Intent.createChooser(intent, "Select File"));
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            openGaleryActivityResultLauncher.launch(Intent.createChooser(intent, "Select File"));
        }
    }

    ActivityResultLauncher<Intent> openGaleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (data.getData() != null) {
                            Uri returnUri = data.getData();
                            setData(returnUri);
                        }
                    }
                }

                private void setData(Uri returnUri) {
                    Cursor returnCursor = getActivity().getContentResolver().query(returnUri, null, null, null, null);
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    returnCursor.moveToFirst();
                    newAttachment = new AttachmentListData();
                    newAttachment.setImageName(returnCursor.getString(nameIndex));
                    newAttachment.setImageUri(returnUri.toString());
                    newAttachment.setImagePath(new PathUri().getRealPathFromURI(getActivity(), returnUri));
                    newAttachment.setRealUri(returnUri);
                    new GlideLoader().glideImageRoundedLoader(getActivity(), getView(), photoProfile, returnUri.toString());
                }
            }
    );

}