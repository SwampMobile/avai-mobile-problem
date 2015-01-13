package com.swampmobile.avaimobileproblem.services;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoApi;

/**
 * A SpiceService that uses Retrofit to run requests, and Gson to deserialize the responses.
 */
public class DuckDuckGoRobospiceService extends RetrofitGsonSpiceService {

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(DuckDuckGoApi.class);
    }

    @Override
    protected String getServerUrl() {
        return DuckDuckGoApi.ENDPOINT;
    }
}
