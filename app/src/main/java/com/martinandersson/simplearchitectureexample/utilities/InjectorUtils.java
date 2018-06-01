package com.martinandersson.simplearchitectureexample.utilities;

import android.content.Context;

import com.martinandersson.simplearchitectureexample.data.TracksRepository;
import com.martinandersson.simplearchitectureexample.data.database.TracksDatabase;
import com.martinandersson.simplearchitectureexample.ui.detail.DetailActivityViewModelFactory;
import com.martinandersson.simplearchitectureexample.ui.list.MainActivityViewModelFactory;

public class InjectorUtils {

    public static TracksRepository provideRepository(Context context) {
        TracksDatabase database = TracksDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        return TracksRepository.getInstance(database.trackDao(), executors);
    }

    public static MainActivityViewModelFactory provideMainActivityViewModelFactory(Context context) {
        TracksRepository repository = provideRepository(context);
        return new MainActivityViewModelFactory(repository);
    }

    public static DetailActivityViewModelFactory provideDetailActivityViewModelFactory(Context context) {
        TracksRepository repository = provideRepository(context);
        return new DetailActivityViewModelFactory(repository);
    }

}
