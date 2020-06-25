package com.example.mycontacts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {contact.class},exportSchema = false, version = 1)
public abstract class contactDataBase extends RoomDatabase {
    abstract contactDao ContactDao();
    private static contactDataBase INSTANCE = null;
    static contactDataBase getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (contactDataBase.class) {
                if (INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),contactDataBase.class,"ContactDatabase").fallbackToDestructiveMigration().build();
                }

            }
        }
        return INSTANCE;
    }
}
