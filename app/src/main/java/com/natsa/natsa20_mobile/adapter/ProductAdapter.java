package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.DateFormater;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.helper.Preferences;
import com.natsa.natsa20_mobile.model.products.product.Product;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.model.regions.Data;
import com.natsa.natsa20_mobile.model.user.User;
import com.natsa.natsa20_mobile.server.process.bookmark.AddBookmark;
import com.natsa.natsa20_mobile.server.process.irrigations.GetIrrigations;
import com.natsa.natsa20_mobile.server.process.products.GetProduct;
import com.natsa.natsa20_mobile.server.process.regions.GetRegions;
import com.natsa.natsa20_mobile.server.process.vestiges.GetVestiges;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final Activity activity;
    private final List<RiceField> productDataList;
    private final ProductImageAdapter productImageAdapter;
    public ProductAdapter(Activity activity, List<RiceField> productsDataList,
                          ProductImageAdapter productImageAdapter) {
        this.activity = activity;
        this.productDataList = productsDataList;
        this.productImageAdapter = productImageAdapter;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_layout, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        SliderView sliderView = holder.sliderView;
        sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        sliderView.setAutoCycle(false);
        sliderView.setSliderAdapter(productImageAdapter);

        RiceField product = productDataList.get(position);

        holder.productTitle.setText(product.getTitle());
        holder.productPrice.setText(String.valueOf(product.getHarga()));
        holder.address.setText(product.getAlamat());
        Data regionData = GetRegions.getRegionData();
        if (regionData != null) {
            holder.region.setText(regionData.getProvinsi()+", "+regionData.getKabupaten());
        }
        holder.description.setText(product.getDeskripsi());
        holder.certification.setText(product.getSertifikasi().toUpperCase());
        holder.type.setText(product.getTipe());
        com.natsa.natsa20_mobile.model.vestiges.Data vestigeData = GetVestiges.getVestigeData();
        com.natsa.natsa20_mobile.model.irrigations.Data irrigationData = GetIrrigations.getIrrigationData();
        if (vestigeData != null && irrigationData != null) {
            holder.category.setText(vestigeData.getVestige()+", "+irrigationData.getIrrigation());
        }
        holder.addBookmarkButton.setOnClickListener(v -> {
            new AddBookmark().addBookmarkProcess(product.getId(), activity);
        });
    }

    @Override
    public int getItemCount() {
        return (productDataList != null) ? productDataList.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView productTitle, productPrice, address, region, description, certification,
                type, category;
        private final Button addBookmarkButton;
        private final SliderView sliderView;

        public ProductViewHolder(View view) {
            super(view);
            productTitle = view.findViewById(R.id.productTitle);
            productPrice = view.findViewById(R.id.productPrice);
            address = view.findViewById(R.id.address);
            region = view.findViewById(R.id.region);
            description = view.findViewById(R.id.description);
            certification = view.findViewById(R.id.certification);
            type = view.findViewById(R.id.type);
            category = view.findViewById(R.id.category);
            addBookmarkButton = view.findViewById(R.id.addBookmarkButton);
            sliderView = view.findViewById(R.id.imageSlider);
        }
    }

}
