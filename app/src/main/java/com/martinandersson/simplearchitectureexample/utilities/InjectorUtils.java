package com.martinandersson.simplearchitectureexample.utilities;

import com.martinandersson.simplearchitectureexample.data.SongsRepository;
import com.martinandersson.simplearchitectureexample.ui.list.MainActivityViewModelFactory;

public class InjectorUtils {

    public static SongsRepository provideRepository() {
        return SongsRepository.getInstance();
    }

    public static MainActivityViewModelFactory provideMainActivityViewModelFactory() {
        SongsRepository repository = provideRepository();
        return new MainActivityViewModelFactory(repository);
    }

}
