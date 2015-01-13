package com.swampmobile.avaimobileproblem.app.net.apis;

import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Retrofit API definition for DuckDuckGo.
 */
public interface DuckDuckGoObservableApi {

    public static final String ENDPOINT = "http://duckduckgo.com"; // Could be provided by some sort of DI in real app

    // For this project we leave pretty printing on. In a real project we could turn it into a
    // Query parameter and toggle it between "production" and "debug" modes.
    @GET("/?format=json&pretty=1&skip_disambig=1")
    public Observable<DuckDuckGoResponse> getQuery(@Query("q") String query);
}
