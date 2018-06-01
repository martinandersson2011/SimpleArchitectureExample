package com.martinandersson.simplearchitectureexample.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {SongEntity.class}, version = 1)
public abstract class SongsDatabase extends RoomDatabase {

    public abstract SongDao songDao();

    private static final String DATABASE_NAME = "songs";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile SongsDatabase sInstance;

    public static SongsDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), SongsDatabase.class, SongsDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}

