package com.martinandersson.simplearchitectureexample.data.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by martin.andersson on 5/7/18.
 */
public interface SearchApi {

    @GET("/search/")
    Call<TracksResponse> getItunesSearchResults(@Query("term") String term);

}
