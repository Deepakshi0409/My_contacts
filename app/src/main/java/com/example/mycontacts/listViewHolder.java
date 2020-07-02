package com.example.mycontacts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class listViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTV,emailTV , phoneTV, ageTV, cityTV, collegeTV;

    public listViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTV = itemView.findViewById(R.id.newname);
        emailTV = itemView.findViewById(R.id.newemail);
        phoneTV = itemView.findViewById(R.id.newnumber);
        ageTV = itemView.findViewById(R.id.newage);
        cityTV = itemView.findViewById(R.id.newcity);
        collegeTV = itemView.findViewById(R.id.newcollege);
    }
    public void bind(Contact task) {
        nameTV.setText(task.getTaskName());
        emailTV.setText(task.getTaskEmail());
        phoneTV.setText(task.getTaskPhone());
        ageTV.setText(task.getTaskAge());
        cityTV.setText(task.getTaskCity());
        collegeTV.setText(task.getTaskCollege());
    }
}
