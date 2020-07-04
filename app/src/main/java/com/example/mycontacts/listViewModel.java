package com.example.mycontacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class listViewModel extends AndroidViewModel {

    private com.example.mycontacts.ContactRepository ContactRepository;
    LiveData<PagedList<Contact>> pagedListLiveData;
    public listViewModel(@NonNull Application application){
        super(application);
        ContactRepository = new ContactRepository(application);
        pagedListLiveData = ContactRepository.getAllTasks();
    }
    public void insertTask(Contact task){
        ContactRepository.insertTask(task);
    }

    public void deleteTask(Contact task) { ContactRepository.deleteTask(task);}


}
