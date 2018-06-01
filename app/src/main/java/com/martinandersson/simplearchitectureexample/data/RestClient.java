package com.martinandersson.simplearchitectureexample.data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by martin.andersson on 5/7/18.
 */
public final class RestClient {
    public static final String BASE_URL = "http://itunes.apple.com";
    private static SearchApi searchApi;

    private RestClient() {
        // Hidden constructor
    }

    static {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchApi = retrofit.create(SearchApi.class);
    }

    public static SearchApi getSearchApi() {
        return searchApi;
    }
}
