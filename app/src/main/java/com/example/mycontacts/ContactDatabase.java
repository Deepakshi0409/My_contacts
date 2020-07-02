package com.example.mycontacts;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    public static ContactDatabase INSTANCE = null;

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static ContactDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class,
                            "contactDatabase"
                    ).addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            executor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    prepopulateDb(context.getAssets(), INSTANCE.contactDao());
                                }
                            });
                        }
                    }).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
    private static void prepopulateDb(AssetManager assetManager, ContactDao contactDao) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String json = "";
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(assetManager.open("contacts.json"))
            );
            String mLine;
            while ((mLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(mLine);
            }
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray contacts = jsonObject.getJSONArray("contact");
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject contact = contacts.getJSONObject(i);
                String name = contact.getString("name");
                String phone = contact.getString("phone");
                String email = contact.getString("email");
                String age = contact.getString("age");
                String city = contact.getString("city");
                String college = contact.getString("college");
                contactDao.insertTask(new Contact(name,phone, email,age,city,college));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
