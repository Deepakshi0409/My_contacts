package com.example.mycontacts;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactDao {
    @Insert
    void insertTask(Contact task);

    @Update
    void updateTask(Contact task);

    @Delete
    void deleteTask(Contact task);

    @Query("Select * from Contact ORDER BY Name asc")
    DataSource.Factory<Integer, Contact> getAllTasks();
}
