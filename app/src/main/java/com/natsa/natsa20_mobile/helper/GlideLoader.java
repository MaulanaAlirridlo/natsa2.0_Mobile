package com.natsa.natsa20_mobile.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
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

    public void glideLoader(Activity activity, View view, ImageView imageView, String imgLocation) {
        countImageLoaded = 0;
        glideProcess(activity, view, imageView, imgLocation);
    }

    private void glideProcess(Activity activity, View view, ImageView imageView, String imgLocation) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideProcess(activity, view, imageView, imgLocation);
        final Runnable runnableWithError = () -> glideProcessWithError(activity, view, imageView, imgLocation);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!activity.isDestroyed()) {
                Glide.with(view)
                        .load(imgLocation)
                        .centerCrop()
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                if (countImageLoaded < 10) {
                                    weakHandler.postDelayed(runnable, 100);
                                    countImageLoaded++;
                                } else if (countImageLoaded == 10) {
                                    weakHandler.postDelayed(runnableWithError, 100);
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
        } else {
            Glide.with(view)
                    .load(imgLocation)
                    .centerCrop()
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            if (countImageLoaded < 10) {
                                weakHandler.postDelayed(runnable, 100);
                                countImageLoaded++;
                            } else if (countImageLoaded == 10) {
                                weakHandler.postDelayed(runnableWithError, 100);
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

    private void glideProcessWithError(Activity activity, View view, ImageView imageView, String imgLocation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!activity.isDestroyed()) {
                Glide.with(view)
                        .load(imgLocation)
                        .centerCrop()
                        .error(R.drawable.ic_error)
                        .into(imageView);
            }
        } else {
            Glide.with(view)
                    .load(imgLocation)
                    .centerCrop()
                    .error(R.drawable.ic_error)
                    .into(imageView);
        }
    }

    public void glideImageRoundedLoader(Activity activity, View view, ImageView imageView, String imgLocation) {
        countRoundedImageLoaded = 0;
        glideImageRoundedProcess(activity, view, imageView, imgLocation);
    }

    private void glideImageRoundedProcess(Activity activity, View view, ImageView imageView, String imgLocation){
        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideImageRoundedProcess(activity, view, imageView, imgLocation);
        final Runnable runnableWithError = () -> glideImageRoundedProcessWithError(activity, view,
                imageView, imgLocation);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        Log.d("TAG", "glideImageRoundedProcess: "+activity.isDestroyed());
            if (!activity.isDestroyed()){
                Glide.with(view)
                        .load(imgLocation)
                        .centerCrop()
                        .circleCrop()
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                if (countRoundedImageLoaded < 10) {
                                    weakHandler.postDelayed(runnable, 100);
                                    countRoundedImageLoaded++;
                                } else if (countRoundedImageLoaded == 10) {
                                    weakHandler.postDelayed(runnableWithError, 100);
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
        } else {
            Glide.with(view)
                    .load(imgLocation)
                    .centerCrop()
                    .circleCrop()
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            if (countRoundedImageLoaded < 10) {
                                weakHandler.postDelayed(runnable, 100);
                                countRoundedImageLoaded++;
                            } else if (countRoundedImageLoaded == 10) {
                                weakHandler.postDelayed(runnableWithError, 100);
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

    private void glideImageRoundedProcessWithError(Activity activity, View view, ImageView imageView, String imgLocation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!activity.isDestroyed()) {
                Glide.with(view)
                        .load(imgLocation)
                        .centerCrop()
                        .circleCrop()
                        .error(R.drawable.ic_account)
                        .into(imageView);
            }
        } else {
            Glide.with(view)
                    .load(imgLocation)
                    .centerCrop()
                    .circleCrop()
                    .error(R.drawable.ic_account)
                    .into(imageView);
        }
    }
}
