package com.swampmobile.avaimobileproblem.app.net.apis;

import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Retrofit API definition for DuckDuckGo.
 */
public interface DuckDuckGoApi {

    public static final String ENDPOINT = "http://duckduckgo.com"; // Could be provided by some sort of DI in real app

    // For this project we leave pretty printing on. In a real project we could turn it into a
    // Query parameter and toggle it between "production" and "debug" modes.
    @GET("/?format=json&pretty=1&skip_disambig=1")
    public DuckDuckGoResponse getQuery(@Query("q") String query);
}
