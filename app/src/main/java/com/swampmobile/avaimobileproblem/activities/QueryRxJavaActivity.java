package com.swampmobile.avaimobileproblem.activities;

import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoApi;
import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoObservableApi;
import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Duck Duck Go Activity that uses RxJava and an Observable to run the API query.  In this instance
 * the Observable solution isn't much different than the AsyncTask version.  However, in many
 * situations, multiple sequential API calls are required to collect data.  In that case, those
 * calls can easily be composed using the Observable system.  Additionally, the API behind
 * Observables lends itself to building in intermediate steps (like caching), and the API is not
 * based on Android at all, so Observables can easily be used anywhere within an app, whereas
 * AsyncTasks have an API designed primarily to speak with the UI.
 */
public class QueryRxJavaActivity extends BaseQueryActivity {

    @Override
    protected void doQuery(String query) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(DuckDuckGoApi.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        DuckDuckGoObservableApi api = restAdapter.create(DuckDuckGoObservableApi.class);

        api.getQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<DuckDuckGoResponse>() {
                @Override
                public void call(DuckDuckGoResponse response) {
                    displayResult(response);
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    onError((RetrofitError) throwable);
                }
            });
    }

}
