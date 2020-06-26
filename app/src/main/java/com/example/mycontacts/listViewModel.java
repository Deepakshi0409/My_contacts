package com.example.mycontacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class listViewModel extends AndroidViewModel {

    private contactRepository ContactRepository;
    LiveData<PagedList<contact>> pagedListLiveData;
    public listViewModel(@NonNull Application application){
        super(application);
        ContactRepository = new contactRepository(application);
        pagedListLiveData = ContactRepository.getTasks();
    }
    public void insertTask(contact task){
        ContactRepository.insertTask(task);
    }

    public void deleteTask(contact task){ ContactRepository.deleteTask(task);
    }

}
