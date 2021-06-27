package com.natsa.natsa20_mobile.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.model.products.product.Photo;
import com.natsa.natsa20_mobile.model.products.product.Photos;
import com.natsa.natsa20_mobile.server.Server;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductImageAdapter extends SliderViewAdapter<ProductImageAdapter.SliderAdapterVH> {
    private List<Photos> sliderItems = new ArrayList<>();

    public ProductImageAdapter(List<Photos> sliderItems) {
        this.sliderItems = sliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_image, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH holder, final int position) {
        glideLoader(holder, position);
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    class SliderAdapterVH extends ProductImageAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }

    private void glideLoader(SliderAdapterVH holder, int position) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideLoader(holder, position);

        Glide.with(holder.itemView)
                .load(Server.storage + sliderItems.get(position).getPhoto_path())
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
                .into(holder.imageViewBackground);
    }
}
