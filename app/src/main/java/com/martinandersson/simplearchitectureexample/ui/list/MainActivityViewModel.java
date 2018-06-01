package com.martinandersson.simplearchitectureexample.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.martinandersson.simplearchitectureexample.data.SongEntity;
import com.martinandersson.simplearchitectureexample.data.SongsRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private SongsRepository mSongsRepository;
    private LiveData<List<SongEntity>> mSongsLiveData;

    public MainActivityViewModel(SongsRepository repository) {
        mSongsRepository = repository;
        mSongsLiveData = repository.getSongsLiveData();
    }

    public LiveData<List<SongEntity>> getSongsLiveData() {
        return mSongsLiveData;
    }

    public void searchForSongs(String searchTerm) {
        mSongsRepository.searchForSongs(searchTerm);
    }

}
