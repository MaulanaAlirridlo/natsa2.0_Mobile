package com.maulana.natsa20_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maulana.natsa20_mobile.R;
import com.maulana.natsa20_mobile.model.Products;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private final ArrayList<Products> productsList;
//    private showDetailSawahListener showDetailSawahListener;

    public ProductsAdapter(ArrayList<Products> productsList) {
        this.productsList = productsList;
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
//        holder.productsImage.set(dataList.get(position).());
        holder.productsTitle.setText(productsList.get(position).getTitle());
        holder.productsPrice.setText(productsList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return (productsList != null) ? productsList.size() : 0;
    }

    public class ProductsViewHolder extends  RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice;
        private int position;
        private String id;

        public ProductsViewHolder(View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);

//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    position = getAdapterPosition();
//                    id = productsList.get(position).getId();
//                    show(id);
//                }
//            });
        }
    }

//    private void show(final String id) {
//        showDetailSawahListener.showDetailSawah(id);
//    }
//
//    public interface showDetailSawahListener {
//        void showDetailSawah(String id);
//    }
}
