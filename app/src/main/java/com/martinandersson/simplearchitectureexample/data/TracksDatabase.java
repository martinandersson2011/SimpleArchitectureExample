package com.martinandersson.simplearchitectureexample.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Track.class}, version = 1)
public abstract class TracksDatabase extends RoomDatabase {

    public abstract TrackDao trackDao();

    private static final String DATABASE_NAME = "tracks";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile TracksDatabase sInstance;

    public static TracksDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), TracksDatabase.class, TracksDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}

