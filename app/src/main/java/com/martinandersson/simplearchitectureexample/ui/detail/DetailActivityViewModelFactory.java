package com.martinandersson.simplearchitectureexample.ui.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.martinandersson.simplearchitectureexample.data.TracksRepository;
import com.martinandersson.simplearchitectureexample.ui.list.MainActivityViewModel;

public class DetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TracksRepository mRepository;

    public DetailActivityViewModelFactory(TracksRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailActivityViewModel(mRepository);
    }
}