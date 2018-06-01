package com.martinandersson.simplearchitectureexample.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.martinandersson.simplearchitectureexample.data.TracksRepository;
import com.martinandersson.simplearchitectureexample.data.database.Track;

public class DetailActivityViewModel extends ViewModel {

    private TracksRepository mRepository;

    public DetailActivityViewModel(TracksRepository repository) {
        mRepository = repository;
    }

    public LiveData<Track> getTrackWithId(int trackId) {
        return mRepository.getTrackWithId(trackId);
    }


}
