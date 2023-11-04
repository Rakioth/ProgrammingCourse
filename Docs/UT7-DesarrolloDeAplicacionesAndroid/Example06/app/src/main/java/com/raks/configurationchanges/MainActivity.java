package com.raks.configurationchanges;

import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TIMES_CALLED = "TIMES_CALLED";
    private final static String TAG          = MainActivity.class.getName();

    private int _counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            for (String key : savedInstanceState.keySet())
                Log.i(TAG, savedInstanceState.get(key).toString());
            _counter = savedInstanceState.getInt(TIMES_CALLED);
        }
        ((TextView) findViewById(R.id.times)).setText(String.format(getString(R.string.times_called), _counter));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        _counter++;
        outState.putInt(TIMES_CALLED, _counter);
    }

}