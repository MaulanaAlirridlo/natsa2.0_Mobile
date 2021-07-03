package com.natsa.natsa20_mobile.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.natsa.natsa20_mobile.model.products.products.Data;
import com.natsa.natsa20_mobile.server.Server;

import java.util.List;

public class UserProductsadapter extends RecyclerView.Adapter<UserProductsadapter.UserProductsViewHolder> {

    private final Context context;
    private final List<Data> userProductsDataList;

    public UserProductsadapter(Context context, List<Data> userProductsDataList) {
        this.context = context;
        this.userProductsDataList = userProductsDataList;
    }

    @NonNull
    @Override
    public UserProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_products_card, parent, false);
        return new UserProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductsViewHolder holder, int position) {
        if (userProductsDataList.get(position).getPhoto() != null) {
            new GlideLoader().glideLoader(holder.itemView, holder.productsImage,
                    Server.storage + userProductsDataList.get(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);

        }
        holder.productsTitle.setText(userProductsDataList.get(position).getTitle());
        holder.productsPrice.setText(String.valueOf(userProductsDataList.get(position).getHarga()));
        holder.lihatProduct.setOnClickListener(v -> {

        });
        holder.editProduct.setOnClickListener(v -> {

        });
        holder.deleteProduct.setOnClickListener(v -> {
//            AlertDialog dialog = new AlertDialog.Builder(context)
//                    .setMessage("Apakah anda yakin ingin menghapusnya?")
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int whichButton) {
//                            new DeleteBookmark().deleteBookmarkProcess(
//                                    userProductsDataList.get(position).getBookmarks_id(),
//                                    context);
//                            new GetBookmark().getBookmarkFromApi(BookmarkAdapater.this, context);
//                        }})
//                    .setNegativeButton("Tidak", null)
//                    .create();
//            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                @Override
//                public void onShow(DialogInterface a) {
//                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
//                            context.getResources().getColor(R.color.black));
//                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources()
//                            .getColor(R.color.black));
//                }
//            });
//            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return (userProductsDataList != null) ? userProductsDataList.size() : 0;
    }

    public class UserProductsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice, lihatProduct, editProduct, deleteProduct;

        public UserProductsViewHolder(@NonNull View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);
            lihatProduct = v.findViewById(R.id.lihat_product);
            editProduct = v.findViewById(R.id.edit_product);
            deleteProduct = v.findViewById(R.id.delete_product);
        }
    }

    //glide image loader and setter
    private void glideLoader(UserProductsViewHolder holder, int position) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideLoader(holder, position);

        Glide.with(context)
                .load(Server.storage + userProductsDataList.get(position).getPhoto().getPhoto_path())
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
                .into(holder.productsImage);
    }
}
