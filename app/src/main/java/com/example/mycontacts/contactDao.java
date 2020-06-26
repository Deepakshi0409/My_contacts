package com.example.mycontacts;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface contactDao {
        @Insert
        void insertTask(contact task);

        @Update
        void updateTask(contact task);

        @Delete
        void deleteTask(contact task);

        @Query("SELECT * FROM contact")
        static DataSource.Factory<Integer, contact> getTasks();


    }


