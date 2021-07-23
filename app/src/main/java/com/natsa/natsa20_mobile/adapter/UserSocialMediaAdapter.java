package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;
import com.natsa.natsa20_mobile.server.DeleteHistory;
import com.natsa.natsa20_mobile.server.Server;
import com.natsa.natsa20_mobile.server.process.social_media.DeleteUserSocialMedia;
import com.natsa.natsa20_mobile.server.process.social_media.GetUserSocialMedia;

import java.util.List;

public class UserSocialMediaAdapter extends RecyclerView.Adapter<UserSocialMediaAdapter.SocialMediaViewHolder> {
    private final Activity activity;
    private final List<UserSocialMedia> socialMediaData;

    public UserSocialMediaAdapter(Activity activity, List<UserSocialMedia> socialMediaData) {
        this.activity = activity;
        this.socialMediaData = socialMediaData;
    }

    @NonNull
    @Override
    public UserSocialMediaAdapter.SocialMediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.social_media_card, parent, false);
        return new UserSocialMediaAdapter.SocialMediaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(UserSocialMediaAdapter.SocialMediaViewHolder holder, int position) {
        UserSocialMedia userSocialMedia = socialMediaData.get(position);
        if (Preferences.isLogin(activity)) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.image,
                    Server.storage+userSocialMedia.getSocial_media().getIcon_path());
            holder.name.setText(userSocialMedia.getSocial_media().getSosmed());
            holder.link.setText(userSocialMedia.getLink());

            holder.delete.setOnClickListener(v -> {
                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setMessage("Apakah anda yakin ingin menghapusnya?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton("Tidak", null)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                new DeleteUserSocialMedia().deleteUserSocialMedia(activity, userSocialMedia.getId());
                                new GetUserSocialMedia().getUserSocialMediaFromApi(activity, UserSocialMediaAdapter.this);
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
        }
    }

    @Override
    public int getItemCount() {
        return (socialMediaData != null) ? socialMediaData.size() : 0;
    }

    public class SocialMediaViewHolder extends RecyclerView.ViewHolder {
        final ImageView image, save, delete;
        final TextView name;
        final EditText link;

        public SocialMediaViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
            link = view.findViewById(R.id.link);
            save = view.findViewById(R.id.save);
            delete = view.findViewById(R.id.delete);
        }
    }

}
