package com.martinandersson.simplearchitectureexample.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsRepository {

    private static final String TAG = SongsRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static SongsRepository sInstance;

    private SongsRepository() {
    }

    public synchronized static SongsRepository getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SongsRepository();
                Log.d(TAG, "getInstance -> new repository");
            }
        }
        return sInstance;
    }

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
                    mSongsLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<SongsResponse> call, Throwable t) {
                mSongsLiveData.postValue(new ArrayList<>());
            }
        });
    }

}
