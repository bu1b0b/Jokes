package ru.bu1b0b.jokes;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bu1b0b.jokes.api.JokesApi;

/**
 * Created by bu1b0b on 13.02.2017.
 */

public class JokeApp extends Application {

    private static JokesApi jokesApi;
    private static Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder().baseUrl("http://www.umori.li/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jokesApi = retrofit.create(JokesApi.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static JokesApi getApi() {
        return jokesApi;
    }

}
