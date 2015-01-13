package com.swampmobile.avaimobileproblem.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.swampmobile.avaimobileproblem.R;


public class MainActivity extends BaseActivity {

    private EditText mQueryEditText;
    private Button mSubmitButton;
    private Class<? extends Activity> mQueryActivityClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initQueryActivityClass();

        mQueryEditText = (EditText) findViewById(R.id.edittext_query);
        mSubmitButton = (Button) findViewById(R.id.button_submit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mQueryEditText.getText().toString();

                if (!TextUtils.isEmpty(query)) {
                    Intent intent = new Intent(MainActivity.this, mQueryActivityClass);
                    intent.putExtra(QueryAsyncTaskActivity.EXTRA_KEY_QUERY, query);
                    startActivity(intent);
                } else {
                    // TODO: notify user
                }
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void initQueryActivityClass() {
        // Find the radio button that is currently toggled and then assign the desired Activity
        // class based on the selected radio button.
        onRadioButtonToggle(findViewById(R.id.radiobutton_asynctask));
        onRadioButtonToggle(findViewById(R.id.radiobutton_robospice));
        onRadioButtonToggle(findViewById(R.id.radiobutton_rxjava));
    }

    public void onRadioButtonToggle(View view) {
        // Is the button now checked?
        boolean isChecked = ((RadioButton) view).isChecked();

        if (isChecked) {
            switch (view.getId()) {
                case R.id.radiobutton_asynctask:
                    mQueryActivityClass = QueryAsyncTaskActivity.class;
                    break;
                case R.id.radiobutton_robospice:
                    mQueryActivityClass = QueryRobospiceActivity.class;
                    break;
                case R.id.radiobutton_rxjava:
                    mQueryActivityClass = QueryRxJavaActivity.class;
                    break;
                default:
                    // No default behavior
                    break;
            }
        }
    }
}
