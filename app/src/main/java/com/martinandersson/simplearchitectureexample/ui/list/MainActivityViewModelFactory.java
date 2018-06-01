package com.martinandersson.simplearchitectureexample.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.martinandersson.simplearchitectureexample.data.TracksRepository;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TracksRepository mRepository;

    public MainActivityViewModelFactory(TracksRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}