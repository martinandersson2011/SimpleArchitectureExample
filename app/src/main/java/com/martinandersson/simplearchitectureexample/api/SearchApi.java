package com.martinandersson.simplearchitectureexample.api;


import com.martinandersson.simplearchitectureexample.model.SongsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by martin.andersson on 5/7/18.
 */
public interface SearchApi {

    @GET("/search/")
    Call<SongsResponse> getItunesSearchResults(@Query("term") String term);

}
