package com.swampmobile.avaimobileproblem.activities;

import android.app.ProgressDialog;
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
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_async_task);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mHeaderTextView = (TextView) findViewById(R.id.textview_header);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setEmptyView(findViewById(R.id.textview_empty_view));

        createProgressDialog();

        String query = getIntent().getStringExtra(EXTRA_KEY_QUERY);

        if (null == query) {
            // The calling Activity did not provide a query. We will immediately finish
            finish();
        } else {
            // Query results don't change during this Activity, so we start query here
            showProgressDialog();
            doQuery(query);
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();

        super.onDestroy();
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
        dismissProgressDialog();

        // In case the Activity was torn down before this was invoked.
        if (null == mHeaderTextView || isFinishing()) {
            return;
        }

        mHeaderTextView.setText( TextUtils.isEmpty(response.getDefinition()) ?
                getString(R.string.no_definition) : response.getDefinition());

        RelatedTopicsAdapter adapter = new RelatedTopicsAdapter(this);
        adapter.setData(response.getRelatedTopics());
        mListView.setAdapter(adapter);
    }

    protected void onError(RetrofitError error) {
        dismissProgressDialog();
        mHeaderTextView.setText(getString(R.string.search_error));
    }

    private void createProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setTitle(getString(R.string.dialog_title_searching));
    }

    private void showProgressDialog() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
