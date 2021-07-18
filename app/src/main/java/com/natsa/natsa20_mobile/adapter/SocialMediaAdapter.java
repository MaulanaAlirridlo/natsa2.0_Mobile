package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.social_media.UserSocialMedia;
import com.natsa.natsa20_mobile.server.Server;

import java.util.List;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.SocialMediaViewHolder> {
    private final Activity activity;
    private final List<UserSocialMedia> socialMediaData;

    public SocialMediaAdapter(Activity activity, List<UserSocialMedia> socialMediaData) {
        this.activity = activity;
        this.socialMediaData = socialMediaData;
    }

    @NonNull
    @Override
    public SocialMediaAdapter.SocialMediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.social_media_layout, parent, false);
        return new SocialMediaAdapter.SocialMediaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SocialMediaAdapter.SocialMediaViewHolder holder, int position) {
        UserSocialMedia socialMedia = socialMediaData.get(position);
        if (Preferences.isLogin(activity)) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.socialMediaImage,
                    Server.storage+socialMedia.getSocial_media().getIcon_path());
            holder.socialMediaImage.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(socialMedia.getLink()));
                activity.startActivity(i);
            });
        }
    }

    @Override
    public int getItemCount() {
        return (socialMediaData != null) ? socialMediaData.size() : 0;
    }

    public class SocialMediaViewHolder extends RecyclerView.ViewHolder {
        final ImageView socialMediaImage;

        public SocialMediaViewHolder(View view) {
            super(view);
            socialMediaImage = view.findViewById(R.id.social_media_image);
        }
    }

}

