package com.natsa.natsa20_mobile.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.natsa.natsa20_mobile.model.bookmark.get_bookmark.Data;
import com.natsa.natsa20_mobile.server.Server;
import com.natsa.natsa20_mobile.server.process.Paging.bookmark.BookmarksViewModel;
import com.natsa.natsa20_mobile.server.process.bookmark.DeleteBookmark;
import com.natsa.natsa20_mobile.server.process.bookmark.GetBookmark;

public class BookmarkAdapater extends PagedListAdapter<Data, BookmarkAdapater.BookmarkViewHolder> {

    final Activity activity;
    final BookmarksViewModel bookmarksViewModel;
    private BookmarkAdapater.showDetailSawahListener showDetailSawahListener;

    public BookmarkAdapater(Activity activity, BookmarksViewModel bookmarksViewModel) {
        super(DIFF_CALLBACK);
        this.activity = activity;
        this.bookmarksViewModel = bookmarksViewModel;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.account_menu_product_card, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        if (getItem(position).getPhoto() != null) {
            new GlideLoader().glideLoader(activity, holder.itemView, holder.productsImage,
                    Server.storage + getItem(position).getPhoto()
                            .getPhoto_path());
        } else {
            holder.productsImage.setImageResource(R.drawable.no_image);
        }

        try {
            showDetailSawahListener = (BookmarkAdapater.showDetailSawahListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        holder.productsTitle.setText(getItem(position).getTitle());
        holder.productsPrice.setText(String.valueOf(getItem(position).getHarga()));
        holder.lihatProduct.setOnClickListener(v -> {
            show(getItem(position).getId());
        });
        holder.deleteBookmark.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(activity)
                    .setMessage("Apakah anda yakin ingin menghapusnya?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setNegativeButton("Tidak", null)
                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            new DeleteBookmark().deleteBookmarkProcess(
                                    getItem(position).getBookmarks_id(),
                                    activity);
                            bookmarksViewModel.refresh();
                        }
                    })
                    .create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface a) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                            activity.getResources().getColor(R.color.black));
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources()
                            .getColor(R.color.black));
                }
            });
            dialog.show();
        });
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productsImage;
        private final TextView productsTitle, productsPrice, deleteBookmark, lihatProduct;

        public BookmarkViewHolder(@NonNull View v) {
            super(v);
            productsImage = v.findViewById(R.id.productsImage);
            productsTitle = v.findViewById(R.id.productsTitle);
            productsPrice = v.findViewById(R.id.productsPrice);
            deleteBookmark = v.findViewById(R.id.delete_product);
            lihatProduct = v.findViewById(R.id.lihat_product);
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
