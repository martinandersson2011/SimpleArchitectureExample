package com.martinandersson.simplearchitectureexample.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.martinandersson.simplearchitectureexample.data.SongsRepository;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SongsRepository mRepository;

    public MainActivityViewModelFactory(SongsRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}