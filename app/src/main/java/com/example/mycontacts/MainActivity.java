package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private listViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    viewModel = new ViewModelProvider(this).get(listViewModel.class);
    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final contactListPagingAdapter pagingAdapter = new contactListPagingAdapter();
    recyclerView.setAdapter(pagingAdapter);
    recyclerView.setLayoutManager(this, new Observer<PagedList<contact>>()){
        @Override
                public void onChanged(PagedList<contact> tasks) {
            pagingAdapter.submitList(tasks);
            }
        }
 public boolean iscontactEqual(contact t2){
        return ()
        }
    }
}
