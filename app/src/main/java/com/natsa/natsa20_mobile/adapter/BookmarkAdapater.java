package com.natsa.natsa20_mobile.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.server.Server;
import com.natsa.natsa20_mobile.server.process.bookmark.DeleteBookmark;
import com.natsa.natsa20_mobile.server.process.bookmark.GetBookmark;

import java.util.List;

public class BookmarkAdapater extends PagedListAdapter<Data, BookmarkAdapater.BookmarkViewHolder> {

    private final Context context;

    public BookmarkAdapater(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.bookmark_card, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        if (getItem(position).getPhoto() != null) {
            new GlideLoader().glideLoader(holder.itemView, holder.productsImage,
                    Server.storage + getItem(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);
        }
        holder.productsTitle.setText(getItem(position).getTitle());
        holder.productsPrice.setText(String.valueOf(getItem(position).getHarga()));
        holder.deleteBookmark.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setMessage("Apakah anda yakin ingin menghapusnya?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            new DeleteBookmark().deleteBookmarkProcess(
                                    getItem(position).getBookmarks_id(),
                                    context);
                            new GetBookmark().getBookmarkFromApi(BookmarkAdapater.this, context);
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface a) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                            context.getResources().getColor(R.color.black));
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources()
                            .getColor(R.color.black));
                }
            });
            dialog.show();
        });
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice, deleteBookmark;

        public BookmarkViewHolder(@NonNull View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);
            deleteBookmark = v.findViewById(R.id.delete_bookmark);
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
