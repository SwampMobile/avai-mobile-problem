package com.swampmobile.avaimobileproblem.activities;

import android.os.AsyncTask;

import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoApi;
import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class QueryRobospiceActivity extends BaseQueryActivity {

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
        private QueryRobospiceActivity mActivity;
        private RetrofitError mError;

        public DoQueryAsyncTask(String query, QueryRobospiceActivity activity) {
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
