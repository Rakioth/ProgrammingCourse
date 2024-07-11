package com.raks.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText globalPrefText    = findViewById(R.id.global_pref_text);
        final EditText namedFilePrefText = findViewById(R.id.named_file_pref_text);
        final EditText activityPrefText  = findViewById(R.id.activity_pref_text);

        SharedPreferences appPreferences   = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences namedPreferences = this.getSharedPreferences(getString(R.string.NAMED_PREFERENCES_FILE), Context.MODE_PRIVATE);
        SharedPreferences preferences      = this.getPreferences(Context.MODE_PRIVATE);

        globalPrefText.setText(appPreferences.getString(getString(R.string.global_pref), ""));
        namedFilePrefText.setText(namedPreferences.getString(getString(R.string.named_pref), ""));
        activityPrefText.setText(preferences.getString(getString(R.string.activity_pref), ""));

        findViewById(R.id.button).setOnClickListener(v -> {
            preferences
                    .edit()
                    .putString(getString(R.string.activity_pref), activityPrefText.getText().toString())
                    .apply();
            namedPreferences
                    .edit()
                    .putString(getString(R.string.named_pref), namedFilePrefText.getText().toString())
                    .apply();
            appPreferences
                    .edit()
                    .putString(getString(R.string.global_pref), globalPrefText.getText().toString())
                    .apply();

            Intent intent = new Intent();
            intent.setClassName(SecondaryActivity.class.getPackage().getName(), SecondaryActivity.class.getName());
            startActivity(intent);
        });
    }

}