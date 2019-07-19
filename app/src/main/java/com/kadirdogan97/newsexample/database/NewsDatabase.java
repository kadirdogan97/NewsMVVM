package com.kadirdogan97.newsexample.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {News.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

    private static NewsDatabase INSTANCE;

    static NewsDatabase getDatabase(final Context context){
        if(INSTANCE ==null){
            synchronized (NewsDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, "newsDb")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
