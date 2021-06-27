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
import androidx.recyclerview.widget.RecyclerView;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;
import com.natsa.natsa20_mobile.model.products.product.RiceField;
import com.natsa.natsa20_mobile.server.Server;
import com.natsa.natsa20_mobile.server.process.bookmark.DeleteBookmark;
import com.natsa.natsa20_mobile.server.process.bookmark.GetBookmark;

import java.util.List;

public class BookmarkAdapater extends RecyclerView.Adapter<BookmarkAdapater.BookmarkViewHolder>  {

    private final Context context;
    private final List<Data> bookmarkDataList;

    public BookmarkAdapater(Context context, List<Data> bookmarkDataList) {
        this.context = context;
        this.bookmarkDataList = bookmarkDataList;
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
        glideLoader(holder, position);
        holder.productsTitle.setText(bookmarkDataList.get(position).getTitle());
        holder.productsPrice.setText(String.valueOf(bookmarkDataList.get(position).getHarga()));
        holder.deleteBookmark.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setMessage("Apakah anda yakin ingin menghapusnya?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            new DeleteBookmark().deleteBookmarkProcess(
                                    bookmarkDataList.get(position).getBookmarks_id(),
                                    context);
                            new GetBookmark().getBookmarkFromApi(BookmarkAdapater.this, context);
                        }})
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

    @Override
    public int getItemCount() {
        return (bookmarkDataList != null) ? bookmarkDataList.size() : 0;
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

    //glide image loader and setter
    private void glideLoader(BookmarkViewHolder holder, int position) {

        final WeakHandler weakHandler = new WeakHandler();
        final Runnable runnable = () -> glideLoader(holder, position);

        Glide.with(context)
                .load(Server.storage + bookmarkDataList.get(position).getPhoto().getPhoto_path())
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
