package com.martinandersson.simplearchitectureexample.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<SongEntity> songs);

    @Query("SELECT * FROM songs")
    LiveData<List<SongEntity>> getSongs();

    @Query("SELECT * FROM songs WHERE trackId = :trackId")
    LiveData<SongEntity> getSongByTrackId(String trackId);

    @Query("DELETE FROM songs")
    void deleteAllSongs();

}
