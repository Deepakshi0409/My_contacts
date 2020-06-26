package com.example.mycontacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class contactListPagingAdapter extends PagedListAdapter<contact,listViewHolder> {
    protected contactListPagingAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item, parent,false);
        return new listViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
    final contact currTask = getItem(position);
    if(currTask != null) {
        holder.bind(currTask);
    }
    }
    private static DiffUtil.ItemCallback<contact> DIFF_CALLBACK = new DiffUtil.ItemCallback<contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull contact oldItem, @NonNull contact newItem) {
            return (oldItem.getTaskName().equals(newItem.getTaskName()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull contact oldItem, @NonNull contact newItem) {
            return oldItem.isContactEqual(newItem);
        }
    };

}
