package com.example.mycontacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class addViewModel extends AndroidViewModel {
    private ContactRepository repository;

    private LiveData<PagedList<Contact>> pagedListLiveData;

    public addViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
    }

    public void insertTask(Contact task){
        repository.insertTask(task);
    }

    public void updateTask(Contact task){
        repository.updateTask(task);
    }

}
