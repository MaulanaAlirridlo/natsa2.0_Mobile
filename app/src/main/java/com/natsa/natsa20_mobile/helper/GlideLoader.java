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
import com.natsa.natsa20_mobile.R;

public class GlideLoader {
    Integer countImageLoaded, countRoundedImageLoaded;

    public void glideLoader(View view, ImageView imageView, String imgLocation) {
        countImageLoaded = 0;
        glideProcess(view, imageView, imgLocation);
    }

    private void glideProcess(View view, ImageView imageView, String imgLocation) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideProcess(view, imageView, imgLocation);

        Glide.with(view)
                .load(imgLocation)
                .centerCrop()
                .error(R.drawable.ic_error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (countImageLoaded <= 10) {
                            weakHandler.postDelayed(runnable, 100);
                            countImageLoaded++;
                        }
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
        countRoundedImageLoaded = 0;
        glideImageRoundedProcess(view, imageView, imgLocation);
    }

    private void glideImageRoundedProcess(View view, ImageView imageView, String imgLocation){
        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideImageRoundedProcess(view, imageView, imgLocation);

        Glide.with(view)
                .load(imgLocation)
                .centerCrop()
                .circleCrop()
                .error(R.drawable.ic_error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (countRoundedImageLoaded <= 10) {
                            weakHandler.postDelayed(runnable, 100);
                            countRoundedImageLoaded++;
                        }
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
