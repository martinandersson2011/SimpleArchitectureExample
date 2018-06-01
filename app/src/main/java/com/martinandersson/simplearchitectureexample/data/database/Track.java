package com.martinandersson.simplearchitectureexample.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by martin.andersson on 5/7/18.
 */


@Entity(tableName = "tracks")
public class Track implements Serializable {

    @PrimaryKey
    @SerializedName("trackId")
    private int trackId;

    @SerializedName("artworkUrl100")
    private String artistUrl100;

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("trackName")
    private String trackName;

    public Track() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getArtistUrl100() {
        return artistUrl100;
    }

    public void setArtistUrl100(String artistUrl100) {
        this.artistUrl100 = artistUrl100;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

}
