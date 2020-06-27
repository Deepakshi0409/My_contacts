package com.example.mycontacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class addViewModel extends AndroidViewModel {
    private contactRepository repository;

    private LiveData<PagedList<contact>> pagedListLiveData;

    public addViewModel(@NonNull Application application) {
        super(application);
        repository = new contactRepository(application);
    }

    public void insertTask(contact task){
        repository.insertTask(task);
    }

    public void updateTask(contact task){
        repository.updateTask(task);
    }

}
