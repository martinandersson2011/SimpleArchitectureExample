package com.martinandersson.simplearchitectureexample.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Track> tracks);

    @Query("SELECT * FROM tracks")
    LiveData<List<Track>> getTracks();

    @Query("SELECT * FROM tracks WHERE trackId = :trackId")
    LiveData<Track> getTrackById(int trackId);

    @Query("DELETE FROM tracks")
    void deleteAllTracks();

}
