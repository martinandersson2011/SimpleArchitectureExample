package com.martinandersson.simplearchitectureexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.martinandersson.simplearchitectureexample.api.RestClient;
import com.martinandersson.simplearchitectureexample.model.Song;
import com.martinandersson.simplearchitectureexample.model.SongsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsViewModel extends ViewModel {

    private static final String TAG = SongsViewModel.class.getSimpleName();

    private MutableLiveData<List<Song>> mSongsLiveData = new MutableLiveData<>();

    public LiveData<List<Song>> getSongsLiveData() {
        return mSongsLiveData;
    }

    public void searchForSongs(String searchTerm) {
        Log.w(TAG, "searchForSongs: " + searchTerm);
        Call<SongsResponse> call = RestClient.getSearchApi().getItunesSearchResults(searchTerm);
        call.enqueue(new Callback<SongsResponse>() {
            @Override
            public void onResponse(Call<SongsResponse> call, Response<SongsResponse> response) {
                final SongsResponse songsResponse = response.body();
                if (songsResponse != null) {
                    mSongsLiveData.postValue(songsResponse.getSongs());
                } else {
                    mSongsLiveData.postValue(new ArrayList<Song>());
                }
            }

            @Override
            public void onFailure(Call<SongsResponse> call, Throwable t) {
                mSongsLiveData.postValue(new ArrayList<Song>());
            }
        });
    }


}
