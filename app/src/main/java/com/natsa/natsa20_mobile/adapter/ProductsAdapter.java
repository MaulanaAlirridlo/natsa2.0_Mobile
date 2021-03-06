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

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private final Activity activity;
    private final List<Data> productsDataList;
    private ProductsAdapter.showDetailSawahListener showDetailSawahListener;

    public ProductsAdapter(Activity activity, List<Data> productsDataList) {
        this.activity = activity;
        this.productsDataList = productsDataList;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.products_card, parent, false);
        return new ProductsAdapter.ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsViewHolder holder, int position) {
        if (productsDataList.get(position).getPhoto() != null) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.productsImage,
                    Server.storage + productsDataList.get(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);
        }
        holder.productsTitle.setText(productsDataList.get(position).getTitle());
        holder.productsPrice.setText(String.valueOf(productsDataList.get(position).getHarga()));
        holder.productsRegion.setText(productsDataList.get(position).getRegions());

        try {
            showDetailSawahListener = (ProductsAdapter.showDetailSawahListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (productsDataList != null) ? productsDataList.size() : 0;
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
                id = productsDataList.get(position).getId();
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
}