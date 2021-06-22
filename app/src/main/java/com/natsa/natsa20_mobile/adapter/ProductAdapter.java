package com.natsa.natsa20_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.model.products.product.RiceField;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<RiceField> productDataList;

    public ProductAdapter(List<RiceField> productsDataList) {
        this.productDataList = productsDataList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_layout, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        holder.productTitle.setText(productDataList.get(position).getTitle());
        holder.productPrice.setText(String.valueOf(productDataList.get(position).getHarga()));
        holder.address.setText(productDataList.get(position).getAlamat());
//        holder.region.setText(productDataList.get(position).getAlamat());
        holder.certification.setText(productDataList.get(position).getSertifikasi());
        holder.type.setText(productDataList.get(position).getTipe());
//        holder.category.setText(productDataList.get(position).getTipe());
    }

    @Override
    public int getItemCount() {
        return (productDataList != null) ? productDataList.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView productTitle, productPrice, address,
        //            region,
        certification, type
//        , category
                ;

        public ProductViewHolder(View view) {
            super(view);
            productTitle = view.findViewById(R.id.productTitle);
            productPrice = view.findViewById(R.id.productPrice);
            address = view.findViewById(R.id.address);
//        region = view.findViewById(R.id.region);
            certification = view.findViewById(R.id.certification);
            type = view.findViewById(R.id.type);
//        category = view.findViewById(R.id.category);
        }
    }

}
