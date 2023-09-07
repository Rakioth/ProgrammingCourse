package com.raks.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        EditText          globalPrefText = findViewById(R.id.global_pref_text);
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        globalPrefText.setText(appPreferences.getString(getString(R.string.global_pref), ""));

        EditText          namedFilePrefText = findViewById(R.id.named_file_pref_text);
        SharedPreferences namedPreferences  = this.getSharedPreferences(getString(R.string.NAMED_PREFERENCES_FILE), Context.MODE_PRIVATE);
        namedFilePrefText.setText(namedPreferences.getString(getString(R.string.named_pref), ""));

        EditText          activityPrefText = findViewById(R.id.activity_pref_text);
        SharedPreferences preferences      = this.getPreferences(Context.MODE_PRIVATE);
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
            intent.setClassName(MainActivity.class.getPackage().getName(), MainActivity.class.getName());
            startActivity(intent);
        });
    }
}