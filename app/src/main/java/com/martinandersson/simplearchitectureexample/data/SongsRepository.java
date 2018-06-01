package com.martinandersson.simplearchitectureexample.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.martinandersson.simplearchitectureexample.utilities.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsRepository {

    private static final String TAG = SongsRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static SongsRepository sInstance;

    private final SongDao mSongDao;
    private final AppExecutors mExecutors;

    private SongsRepository(SongDao songDao, AppExecutors executors) {
        mSongDao = songDao;
        mExecutors = executors;
    }

    public synchronized static SongsRepository getInstance(SongDao songDao, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SongsRepository(songDao, executors);
            }
        }
        return sInstance;
    }

    public LiveData<List<SongEntity>> getSongsLiveData() {
        return mSongDao.getSongs();
    }

    public void searchForSongs(String searchTerm) {
        Log.w(TAG, "searchForSongs: " + searchTerm);
        Call<SongsResponse> call = RestClient.getSearchApi().getItunesSearchResults(searchTerm);
        call.enqueue(new Callback<SongsResponse>() {
            @Override
            public void onResponse(Call<SongsResponse> call, Response<SongsResponse> response) {
                final SongsResponse songsResponse = response.body();
                mExecutors.diskIO().execute(() -> {
                    if (songsResponse != null) {
                        mSongDao.deleteAllSongs();
                        mSongDao.bulkInsert(songsResponse.getSongs());
                    } else {
                        mSongDao.deleteAllSongs();
                    }
                });
            }

            @Override
            public void onFailure(Call<SongsResponse> call, Throwable t) {
                Log.w(TAG, "searchForSongs - onFailure");
            }
        });
    }

}
