package com.martinandersson.simplearchitectureexample.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class Song implements Serializable {

    @SerializedName("artworkUrl100")
    private String artistUrl100;

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("trackName")
    private String trackName;

    public Song() {
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
