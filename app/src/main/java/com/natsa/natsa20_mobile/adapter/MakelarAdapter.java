package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.activity.BackActivity;
import com.natsa.natsa20_mobile.helper.DateFormater;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.server.process.makelar.GetMakelar;

import java.util.List;

public class MakelarAdapter extends RecyclerView.Adapter<MakelarAdapter.MakelarViewHolder> {
    private final Activity activity;
    private final List<User> userData;

    public MakelarAdapter(Activity activity, List<User> userData) {
        this.activity = activity;
        this.userData = userData;
    }

    @NonNull
    @Override
    public MakelarAdapter.MakelarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.makelar_layout, parent, false);

        RecyclerView socialMediaRecyclerView = view.findViewById(R.id.social_media_recycler_view);
        SocialMediaAdapter socialMediaAdapter = new SocialMediaAdapter(activity,
                GetMakelar.getMakelarSocialMediaDataList());
        RecyclerView.LayoutManager productsLayoutManager = new LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL, false);
        socialMediaRecyclerView.setLayoutManager(productsLayoutManager);
        socialMediaRecyclerView.setAdapter(socialMediaAdapter);

        return new MakelarAdapter.MakelarViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MakelarAdapter.MakelarViewHolder holder, int position) {
        User user = userData.get(position);
        holder.makelarName.setText(user.getName());
        holder.makelarEmail.setText(user.getEmail());
        if (Preferences.isLogin(activity)) {
            holder.makelarNoHp.setText(user.getNo_hp() == null ? "Tidak ada no Hp" : user.getNo_hp());
            holder.showMakelarButton.setOnClickListener(v -> {
                Intent i = new Intent(activity, BackActivity.class);
                i.putExtra("page", "detailMakelar");
                i.putExtra("makelar_name", user.getName());
                i.putExtra("makelar_id", user.getId());
                activity.startActivity(i);
            });

        } else {
            holder.makelarNoHp.setText("Harap login terlebih dahulu");
            holder.showMakelarButton.setVisibility(Button.GONE);
        }
        holder.makelarCreated.setText(new DateFormater().parseDateToddMMMyyyy(user.getCreated_at()));
        new GlideLoader().glideImageRoundedLoader(activity, holder.itemView, holder.makelarPhoto,
                user.getProfile_photo_url());
    }

    @Override
    public int getItemCount() {
        return (userData != null) ? userData.size() : 0;
    }

    public class MakelarViewHolder extends RecyclerView.ViewHolder {

        private final TextView makelarName, makelarEmail, makelarNoHp, makelarCreated;
        final ImageView makelarPhoto;
        final Button showMakelarButton;

        public MakelarViewHolder(View view) {
            super(view);
            makelarName = view.findViewById(R.id.makelar_name);
            makelarEmail = view.findViewById(R.id.makelar_email);
            makelarNoHp = view.findViewById(R.id.makelar_no_hp);
            makelarCreated = view.findViewById(R.id.makelar_created);
            makelarPhoto = view.findViewById(R.id.makelar_photo);
            showMakelarButton = view.findViewById(R.id.show_makelar_button);
            if (activity.getIntent().getExtras().getString("page").equals("detailMakelar")) {
                showMakelarButton.setVisibility(Button.GONE);
            }
        }
    }

}

