package ru.bu1b0b.jokes.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.bu1b0b.jokes.model.JokeModel;
import rx.Observable;

/**
 * Created by bu1b0b on 13.02.2017.
 */

public interface JokesApi {

    @GET("api/random")
    Call<List<JokeModel>> getJokes(@Query("num") int count);

    @GET("api/get")
    Call<List<JokeModel>> getJokes(@QueryMap(encoded = true) Map<String, String> params);

    @GET("api/get?site=bash.im")
    Observable<JokeModel> getProjects(@QueryMap(encoded = true) Map<String, String> params);

}
