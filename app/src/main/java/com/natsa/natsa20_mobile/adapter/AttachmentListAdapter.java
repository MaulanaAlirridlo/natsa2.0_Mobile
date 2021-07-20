package com.natsa.natsa20_mobile.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.natsa.natsa20_mobile.R;
import com.natsa.natsa20_mobile.helper.GlideLoader;
import com.natsa.natsa20_mobile.model.AttachmentListData;

import java.util.ArrayList;

public class AttachmentListAdapter extends RecyclerView.Adapter<AttachmentListAdapter.AttachmentListViewHolder> {
    ArrayList<AttachmentListData> newAttachmentList;
    Activity activity;


    public AttachmentListAdapter(Activity activity, ArrayList<AttachmentListData> list) {
        this.newAttachmentList = list;
        this.activity = activity;
    }

    @Override
    public AttachmentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newattachment_list, parent, false);

        AttachmentListViewHolder holder = new AttachmentListViewHolder(view, newAttachmentList);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AttachmentListViewHolder holder, int position) {
        holder.attachedImageName.setText((newAttachmentList.get(position).getImageName()));
        AttachmentListData list = newAttachmentList.get(position);
        if (!list.getImageUri().isEmpty()) {
            new GlideLoader().glideLoader(activity ,holder.itemView, holder.attachedImageId, list.getImageUri());
        }
    }

    @Override
    public int getItemCount() {
        return newAttachmentList.size();
    }

    class AttachmentListViewHolder extends RecyclerView.ViewHolder {
        ImageView attachedImageId;
        TextView attachedImageName;
        ImageView cancelAttachment;
        View itemView;



        public AttachmentListViewHolder(View view, ArrayList<AttachmentListData> attachmentList) {
            super(view);

            attachedImageId = view.findViewById(R.id.attachedImageId);
            attachedImageName = view.findViewById(R.id.attachedImageName);
            cancelAttachment = view.findViewById(R.id.cancelAttachment);
            itemView = view;

            cancelAttachment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    attachmentList.remove(pos);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
