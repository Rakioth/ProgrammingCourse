package com.raks.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName(MainActivity.class.getPackageName(), MainActivity.class.getName());
            startActivity(intent);
        });
    }
}
