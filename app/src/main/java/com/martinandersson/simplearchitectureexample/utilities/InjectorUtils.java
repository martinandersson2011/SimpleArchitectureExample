package com.martinandersson.simplearchitectureexample.utilities;

import android.content.Context;

import com.martinandersson.simplearchitectureexample.data.SongsDatabase;
import com.martinandersson.simplearchitectureexample.data.SongsRepository;
import com.martinandersson.simplearchitectureexample.ui.list.MainActivityViewModelFactory;

public class InjectorUtils {

    public static SongsRepository provideRepository(Context context) {
        SongsDatabase database = SongsDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        return SongsRepository.getInstance(database.songDao(), executors);
    }

    public static MainActivityViewModelFactory provideMainActivityViewModelFactory(Context context) {
        SongsRepository repository = provideRepository(context);
        return new MainActivityViewModelFactory(repository);
    }

}
