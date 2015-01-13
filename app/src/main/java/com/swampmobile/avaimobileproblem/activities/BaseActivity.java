package com.swampmobile.avaimobileproblem.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.swampmobile.avaimobileproblem.R;

/**
 * Provides configuration used by all Activitys in this project.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configure ActionBar
        Log.d("BaseActivity", "Actionbar: " + getActionBar());
//        getActionBar().setIcon(R.drawable.ic_launcher);
    }
}
