package com.natsa.natsa20_mobile.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GlideLoader {
    public void glideLoader(View view, ImageView imageView, String imgLocation) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideLoader(view, imageView, imgLocation);

        Glide.with(view)
                .load(imgLocation)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        weakHandler.postDelayed(runnable, 1);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }

    public void glideImageRoundedLoader(View view, ImageView imageView, String imgLocation) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideLoader(view, imageView, imgLocation);

        Glide.with(view)
                .load(imgLocation)
                .centerCrop()
                .circleCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        weakHandler.postDelayed(runnable, 1);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
}
