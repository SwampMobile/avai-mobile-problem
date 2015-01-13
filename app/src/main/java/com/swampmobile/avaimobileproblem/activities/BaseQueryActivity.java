package com.swampmobile.avaimobileproblem.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.swampmobile.avaimobileproblem.R;
import com.swampmobile.avaimobileproblem.adapters.RelatedTopicsAdapter;
import com.swampmobile.avaimobileproblem.app.net.apis.DuckDuckGoApi;
import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoResponse;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public abstract class BaseQueryActivity extends BaseActivity {

    public static final String EXTRA_KEY_QUERY = "query";

    private TextView mHeaderTextView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_async_task);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mHeaderTextView = (TextView) findViewById(R.id.textview_header);
        mListView = (ListView) findViewById(R.id.listview);

        String query = getIntent().getStringExtra(EXTRA_KEY_QUERY);

        if (null == query) {
            // The calling Activity did not provide a query. We will immediately finish
            finish();
        } else {
            // Query results don't change during this Activity, so we start query here
            doQuery(query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    abstract protected void doQuery(String query);

    protected void displayResult(DuckDuckGoResponse response) {
        // In case the Activity was torn down before this was invoked.
        if (null == mHeaderTextView) {
            return;
        }

        mHeaderTextView.setText( TextUtils.isEmpty(response.getDefinition()) ?
                getString(R.string.no_definition) : response.getDefinition());

        RelatedTopicsAdapter adapter = new RelatedTopicsAdapter(this);
        adapter.setData(response.getRelatedTopics());
        mListView.setAdapter(adapter);
    }

    protected void onError(RetrofitError error) {
        mHeaderTextView.setText(error.getMessage());
    }
}
