package com.example.mycontacts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class listViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTV , phoneTV;

    public listViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTV = itemView.findViewById(R.id.name);
        phoneTV = itemView.findViewById(R.id.phone);
            }
    public void bind(Contact task) {
        nameTV.setText(task.getTaskName());
        phoneTV.setText(task.getTaskPhone());
            }
}
