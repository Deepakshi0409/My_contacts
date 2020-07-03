package com.example.mycontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private final static int NEW_DATA_REQUEST_CODE =1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_NAME = "extra_task_name";
    public static final String EXTRA_DATA_PHONE = "extra_task_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_task_email";
    public static final String EXTRA_DATA_AGE = "extra_task_age";
    public static final String EXTRA_DATA_CITY = "extra_task_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_task_college";
    public static final String EXTRA_DATA_GENDER = "extra_task_gender";
    private listViewModel viewModel;
    private com.example.mycontacts.Contact Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent,NEW_DATA_REQUEST_CODE);
            }
        });
        viewModel = new ViewModelProvider(this).get(listViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListPagingAdapter taskListPagingAdapter = new ContactListPagingAdapter();
        recyclerView.setAdapter(taskListPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.pagedListLiveData.observe(this, new Observer<PagedList<com.example.mycontacts.Contact>>() {
            @Override
            public void onChanged(PagedList<com.example.mycontacts.Contact> tasks) {
                taskListPagingAdapter.submitList(tasks);
            }
        });
        ConstraintLayout constraint = findViewById(R.id.ConstraintLayout);
        final Snackbar snackbar = Snackbar.make(constraint,"Task Deleted", BaseTransientBottomBar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.insertTask(Contact);
                    }
                });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Contact = taskListPagingAdapter.getTaskAtPosition(pos);
                viewModel.deleteTask(Contact);
                snackbar.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        taskListPagingAdapter.setOnItemClickListener(new ContactListPagingAdapter.ClickListener() {
            @Override
            public void itemClick(int position, View view) {
                com.example.mycontacts.Contact currentTask = taskListPagingAdapter.getTaskAtPosition(position);
                launchUpdateTaskActivity(currentTask);
            }
        });

        }
    private void launchUpdateTaskActivity(com.example.mycontacts.Contact currentTask) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(EXTRA_DATA_NAME,currentTask.getTaskName());
        intent.putExtra(EXTRA_DATA_PHONE,currentTask.getTaskPhone());
        intent.putExtra(EXTRA_DATA_EMAIL,currentTask.getTaskEmail());
        intent.putExtra(EXTRA_DATA_AGE,currentTask.getTaskAge());
        intent.putExtra(EXTRA_DATA_CITY,currentTask.getTaskCity());
        intent.putExtra(EXTRA_DATA_COLLEGE,currentTask.getTaskCollege());
        intent.putExtra(EXTRA_DATA_GENDER,currentTask.getTaskCollege());
        startActivityForResult(intent,UPDATE_DATA_REQUEST_CODE);
    }
}
