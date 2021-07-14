package com.natsa.natsa20_mobile.adapter;

import android.app.Activity;
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
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.model.products.product.Photo;
import com.natsa.natsa20_mobile.model.products.product.Photos;
import com.natsa.natsa20_mobile.server.Server;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductImageAdapter extends SliderViewAdapter<ProductImageAdapter.SliderAdapterVH> {
    Activity activity;
    List<Photos> sliderItems = new ArrayList<>();

    public ProductImageAdapter(Activity activity, List<Photos> sliderItems) {
        this.activity = activity;
        this.sliderItems = sliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_image, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH holder, final int position) {
        if (sliderItems.get(position) != null){
            new GlideLoader().glideLoader(activity, holder.itemView, holder.imageViewBackground,
                    Server.storage + sliderItems.get(position).getPhoto_path());
        } else {
            holder.imageViewBackground.setImageResource(R.drawable.no_image);
        }
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
}
