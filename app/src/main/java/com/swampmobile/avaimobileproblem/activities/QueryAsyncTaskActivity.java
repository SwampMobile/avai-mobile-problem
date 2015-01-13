package com.swampmobile.avaimobileproblem.activities;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.swampmobile.avaimobileproblem.R;
import com.swampmobile.avaimobileproblem.adapters.RelatedTopicsAdapter;
import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoApi;
import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;

import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class QueryAsyncTaskActivity extends BaseQueryActivity {

    public static final String EXTRA_KEY_QUERY = "query";

    private DoQueryAsyncTask queryTask;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_query_async_task);
//    }

    @Override
    protected void onDestroy() {
        // Make sure to clean up the async task
        if (queryTask.getStatus() != AsyncTask.Status.FINISHED) {
            queryTask.cancel(true);
        }
        queryTask = null;

        // Forward super call
        super.onDestroy();
    }

    @Override
    protected void doQuery(String query) {
        queryTask = new DoQueryAsyncTask(query, this);
        queryTask.execute();
    }

    private static class DoQueryAsyncTask extends AsyncTask<Void, Void, DuckDuckGoResponse> {

        private String mQuery;
        private QueryAsyncTaskActivity mActivity;
        private RetrofitError mError;

        public DoQueryAsyncTask(String query, QueryAsyncTaskActivity activity) {
            mQuery = query;
            mActivity = activity;
        }

        @Override
        protected DuckDuckGoResponse doInBackground(Void... params) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(DuckDuckGoApi.ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            DuckDuckGoApi api = restAdapter.create(DuckDuckGoApi.class);

            try {
                return api.getQuery(mQuery);
            } catch (RetrofitError error) {
                mError = error;
                return null;
            }
        }

        @Override
        protected void onCancelled() {
            mActivity = null;
        }

        @Override
        protected void onPostExecute(DuckDuckGoResponse result) {
            if (null != mActivity && !mActivity.isFinishing()) {
                if (null != result) {
                    mActivity.displayResult(result);
                } else {
                    mActivity.onError(mError);
                }
            }
        }
    }
}
