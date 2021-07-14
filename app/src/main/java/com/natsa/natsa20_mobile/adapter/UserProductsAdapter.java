package com.natsa.natsa20_mobile.adapter;

import android.app.Activity;
import android.content.Context;
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

public class UserProductsAdapter extends PagedListAdapter<Data, UserProductsAdapter.UserProductsViewHolder> {

    private final Activity activity;

    public UserProductsAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
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
        if (getItem(position).getPhoto() != null) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.productsImage,
                    Server.storage + getItem(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);

        }
        holder.productsTitle.setText(getItem(position).getTitle());
        holder.productsPrice.setText(String.valueOf(getItem(position).getHarga()));
        holder.lihatProduct.setOnClickListener(v -> {

        });
        holder.editProduct.setOnClickListener(v -> {

        });
        holder.deleteProduct.setOnClickListener(v -> {
//            AlertDialog dialog = new AlertDialog.Builder(activity)
//                    .setMessage("Apakah anda yakin ingin menghapusnya?")
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int whichButton) {
//                            new DeleteBookmark().deleteBookmarkProcess(
//                                    getItem()(position).getBookmarks_id(),
//                                    activity);
//                            new GetBookmark().getBookmarkFromApi(BookmarkAdapater.this, activity);
//                        }})
//                    .setNegativeButton("Tidak", null)
//                    .create();
//            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                @Override
//                public void onShow(DialogInterface a) {
//                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
//                            activity.getResources().getColor(R.color.black));
//                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources()
//                            .getColor(R.color.black));
//                }
//            });
//            dialog.show();
        });
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
