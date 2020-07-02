package com.example.mycontacts;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    private static ContactRepository INSTANCE = null;

    private ContactDao contactDao;

    private static int PAGE_SIZE = 15;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ContactRepository(Application application) {
        ContactDatabase ContactDatabase = com.example.mycontacts.ContactDatabase.getInstance(application);
        contactDao = ContactDatabase.contactDao();
    }

    public ContactRepository getRepository(Application application) {
        if (INSTANCE == null) {
            synchronized (ContactRepository.class) {
                INSTANCE = new ContactRepository(application);

            }

        }
        return INSTANCE;

    }

    public void insertTask(final Contact task) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insertTask(task);
            }
        });
    }

    public void deleteTask(final Contact task) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.deleteTask(task);
            }
        });
    }

    public void updateTask(final Contact task) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.updateTask(task);
            }
        });

    }
    public LiveData<PagedList<Contact>> getAllTasks() {
        return new LivePagedListBuilder<>(
                contactDao.getAllTasks(), PAGE_SIZE
        ).build();
    }
}