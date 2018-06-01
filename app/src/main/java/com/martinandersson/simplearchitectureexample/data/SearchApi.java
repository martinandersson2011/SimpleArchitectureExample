package com.martinandersson.simplearchitectureexample.data;


import com.martinandersson.simplearchitectureexample.data.SongsResponse;

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
