package com.maulana.natsa20_mobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maulana.natsa20_mobile.R;
import com.maulana.natsa20_mobile.model.products.Data;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context context;
    private final List<Data> productsDataList;
    private showDetailSawahListener showDetailSawahListener;

    public ProductsAdapter(Context context, List<Data> productsDataList) {
        this.context = context;
        this.productsDataList = productsDataList;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.products_card, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
//        holder.productsImage.set(productsDataList.get(position).());
        holder.productsTitle.setText(productsDataList.get(position).getTitle());
        holder.productsPrice.setText(productsDataList.get(position).getHarga()+"");

        try {
            showDetailSawahListener = (ProductsAdapter.showDetailSawahListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (productsDataList != null) ? productsDataList.size() : 0;
    }

    public class ProductsViewHolder extends  RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice;
        private int position;
        private int id;

        public ProductsViewHolder(View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    id = productsDataList.get(position).getId();
                    show(id);
                }
            });
        }
    }

    private void show(final int id) {
        showDetailSawahListener.showDetailSawah(id);
    }

    public interface showDetailSawahListener {
        void showDetailSawah(int id);
    }
}
