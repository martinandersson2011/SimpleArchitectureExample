package com.martinandersson.simplearchitectureexample.data.network;

import com.google.gson.annotations.SerializedName;
import com.martinandersson.simplearchitectureexample.data.database.Track;

import java.io.Serializable;
import java.util.List;

/**
 * Created by martin.andersson on 5/7/18.
 */
public class TracksResponse implements Serializable {
    public static final String PROPERTY_RESULT_COUNT = "resultCount";
    public static final String PROPERTY_RESULTS = "results";

    @SerializedName(PROPERTY_RESULT_COUNT)
    private int resultCount;

    @SerializedName(PROPERTY_RESULTS)
    private List<Track> tracks;

    public TracksResponse() {
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
