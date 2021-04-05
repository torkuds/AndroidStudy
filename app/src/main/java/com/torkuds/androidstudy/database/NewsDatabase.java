package com.torkuds.androidstudy.database;

import android.content.Context;

import com.torkuds.androidstudy.entity.News;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {News.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    private static volatile NewsDatabase INSTANCE;

    public abstract NewsDao newsDao();

    public static NewsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDatabase.class, "android_study.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
