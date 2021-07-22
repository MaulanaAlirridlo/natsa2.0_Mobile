package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.natsa.natsa20_mobile.server.Server;

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
        UserSocialMedia socialMedia = socialMediaData.get(position);
        if (Preferences.isLogin(activity)) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.image,
                    Server.storage+socialMedia.getSocial_media().getIcon_path());
            holder.name.setText(socialMedia.getSocial_media().getSosmed());
            holder.link.setText(socialMedia.getLink());

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
