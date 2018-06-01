package com.martinandersson.simplearchitectureexample.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.martinandersson.simplearchitectureexample.utilities.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksRepository {

    private static final String TAG = TracksRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static TracksRepository sInstance;

    private final TrackDao mTrackDao;
    private final AppExecutors mExecutors;

    private TracksRepository(TrackDao trackDao, AppExecutors executors) {
        mTrackDao = trackDao;
        mExecutors = executors;
    }

    public synchronized static TracksRepository getInstance(TrackDao trackDao, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new TracksRepository(trackDao, executors);
            }
        }
        return sInstance;
    }

    public LiveData<List<Track>> getTracks() {
        return mTrackDao.getTracks();
    }

    public void searchOnITunes(String searchTerm) {
        Log.w(TAG, "searchOnITunes: " + searchTerm);
        Call<TracksResponse> call = RestClient.getSearchApi().getItunesSearchResults(searchTerm);
        call.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                final TracksResponse tracksResponse = response.body();
                mExecutors.diskIO().execute(() -> {
                    if (tracksResponse != null) {
                        mTrackDao.deleteAllTracks();
                        mTrackDao.bulkInsert(tracksResponse.getTracks());
                    } else {
                        mTrackDao.deleteAllTracks();
                    }
                });
            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                Log.w(TAG, "searchOnITunes - onFailure");
            }
        });
    }

}
