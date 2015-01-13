package com.swampmobile.avaimobileproblem.activities;

import android.os.Bundle;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoApi;
import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;
import com.swampmobile.avaimobileproblem.services.DuckDuckGoRobospiceService;

import retrofit.RetrofitError;

public class QueryRobospiceActivity extends BaseQueryActivity {

    private SpiceManager mSpiceManager;

    private RequestListener<DuckDuckGoResponse> mRequestListener = new RequestListener<DuckDuckGoResponse>() {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            onError((RetrofitError) spiceException.getCause());
        }

        @Override
        public void onRequestSuccess(DuckDuckGoResponse duckDuckGoResponse) {
            displayResult(duckDuckGoResponse);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Our super class invokes doQuery() in its onCreate(), therefore the mSpiceManager is
        // instantiated in our doQuery().  Normally, mSpiceManager would be instantiated here.
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSpiceManager.start(this);
    }

    @Override
    protected void onStop() {
        mSpiceManager.shouldStop();
        super.onStop();
    }

    @Override
    protected void doQuery(String query) {
        mSpiceManager = new SpiceManager(DuckDuckGoRobospiceService.class);
        mSpiceManager.execute(new DuckDuckGoSpiceRequest(query), mRequestListener);
    }

    private static class DuckDuckGoSpiceRequest extends RetrofitSpiceRequest<DuckDuckGoResponse, DuckDuckGoApi> {

        private String mQuery;

        public DuckDuckGoSpiceRequest(String query) {
            super(DuckDuckGoResponse.class, DuckDuckGoApi.class);
            mQuery = query;
        }

        @Override
        public DuckDuckGoResponse loadDataFromNetwork() throws Exception {
            return getService().getQuery(mQuery);
        }
    }
}
