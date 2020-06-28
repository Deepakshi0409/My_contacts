package com.example.mycontacts;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Dao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class contactRepository {
    private contactDao ContactDao;
    private contactRepository INSTANCE = null;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public contactRepository(Application application) {
        contactDataBase ContactDatabase = contactDataBase.getDatabase(application);
        ContactDao = ContactDatabase.ContactDao();
    }

    public contactRepository getRepository(Application application) {
        if (INSTANCE == null) {
            synchronized (contactRepository.class) {
                INSTANCE = new contactRepository(application);

            }

        }
        return INSTANCE;

    }

    public void insertTask(final contact task) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ContactDao.insertTask(task);
            }
        });
    }

    public void deleteTask(final contact task) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ContactDao.deleteTask(task);
            }
        });
    }

    public void updateTask(final contact task) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ContactDao.updateTask(task);
            }
        });

    }
    public LiveData<PagedList<contact>> getTasks(){
        int PAGE_SIZE = 15;
        return new LivePagedListBuilder<>(
                contactDao.getTasks(), PAGE_SIZE
        ).build();
    }
}