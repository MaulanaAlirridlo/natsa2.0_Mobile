package com.natsa.natsa20_mobile.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.model.products.product.RandomRiceFields;
import com.natsa.natsa20_mobile.server.Server;

import java.util.List;

public class RandomRiceFieldsAdapter extends RecyclerView.Adapter<RandomRiceFieldsAdapter.RandomRiceFieldsViewHolder> {

    private final Context context;
    private final List<RandomRiceFields> randomRiceFieldsDataList;
    private showDetailSawahListener showDetailSawahListener;

    public RandomRiceFieldsAdapter(Context context, List<RandomRiceFields> randomRiceFieldsDataList) {
        this.context = context;
        this.randomRiceFieldsDataList = randomRiceFieldsDataList;
    }

    @NonNull
    @Override
    public RandomRiceFieldsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.products_card, parent, false);
        return new RandomRiceFieldsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRiceFieldsViewHolder holder, int position) {
        new GlideLoader().glideLoader(holder.itemView, holder.productsImage,
                Server.storage + randomRiceFieldsDataList.get(position).getPhoto().getPhoto_path());
        holder.productsTitle.setText(randomRiceFieldsDataList.get(position).getTitle());
        holder.productsPrice.setText(String.valueOf(randomRiceFieldsDataList.get(position).getHarga()));

        try {
            showDetailSawahListener = (RandomRiceFieldsAdapter.showDetailSawahListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (randomRiceFieldsDataList != null) ? randomRiceFieldsDataList.size() : 0;
    }

    public class RandomRiceFieldsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice;
        private int position;
        private int id;
        private View itemView;

        public RandomRiceFieldsViewHolder(View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);

            v.setOnClickListener(v1 -> {
                position = getAdapterPosition();
                id = randomRiceFieldsDataList.get(position).getId();
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
