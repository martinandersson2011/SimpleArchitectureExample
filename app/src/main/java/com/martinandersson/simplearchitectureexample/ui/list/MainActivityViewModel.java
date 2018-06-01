package com.martinandersson.simplearchitectureexample.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.martinandersson.simplearchitectureexample.data.database.Track;
import com.martinandersson.simplearchitectureexample.data.TracksRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private TracksRepository mRepository;
    private LiveData<List<Track>> mTracks;

    public MainActivityViewModel(TracksRepository repository) {
        mRepository = repository;
        mTracks = repository.getTracks();
    }

    public LiveData<List<Track>> getTracks() {
        return mTracks;
    }

    public void performSearch(String searchTerm) {
        mRepository.searchOnITunes(searchTerm);
    }

}
