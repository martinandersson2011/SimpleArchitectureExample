package com.martinandersson.simplearchitectureexample.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class SongsResponse implements Serializable {
    public static final String PROPERTY_RESULT_COUNT = "resultCount";
    public static final String PROPERTY_RESULTS = "results";

    @SerializedName(PROPERTY_RESULT_COUNT)
    private int resultCount;

    @SerializedName(PROPERTY_RESULTS)
    private List<SongEntity> songs;

    public SongsResponse() {
    }

    public List<SongEntity> getSongs() {
        return songs;
    }

    public void setSongs(List<SongEntity> songs) {
        this.songs = songs;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
