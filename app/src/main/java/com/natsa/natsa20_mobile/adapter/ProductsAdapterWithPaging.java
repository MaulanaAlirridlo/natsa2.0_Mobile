package com.natsa.natsa20_mobile.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.Server;

public class ProductsAdapterWithPaging extends PagedListAdapter<Data, ProductsAdapterWithPaging.ProductsViewHolder> {

    private final Activity activity;
    private showDetailSawahListener showDetailSawahListener;

    public ProductsAdapterWithPaging(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.products_card, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        if (getItem(position).getPhoto() != null) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.productsImage,
                    Server.storage + getItem(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);
        }
        holder.productsTitle.setText(getItem(position).getTitle());
        holder.productsPrice.setText(String.valueOf(getItem(position).getHarga()));
        holder.productsRegion.setText(getItem(position).getRegions());

        try {
            showDetailSawahListener = (ProductsAdapterWithPaging.showDetailSawahListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice, productsRegion;
        private int position;
        private int id;
        View itemView;

        public ProductsViewHolder(View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);
            productsRegion = v.findViewById(R.id.product_region);

            v.setOnClickListener(v1 -> {
                position = getAdapterPosition();
                id = getItem(position).getId();
                show(id);
            });

            itemView = v;
        }
    }

    private void show(final int id) {
        showDetailSawahListener.showDetailSawah(id);
    }

    public interface showDetailSawahListener {
        void showDetailSawah(int id);
    }

    private static DiffUtil.ItemCallback<Data> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Data>() {
                @Override
                public boolean areItemsTheSame(Data oldData, Data newData) {
                    return oldData.getId() == newData.getId();
                }

                @Override
                public boolean areContentsTheSame(Data oldData, Data newData) {
                    return oldData.equals(newData);
                }
            };
}
