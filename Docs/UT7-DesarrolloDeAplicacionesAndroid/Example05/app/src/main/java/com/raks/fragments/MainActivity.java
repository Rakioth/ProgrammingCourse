package com.raks.fragments;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName(SecondaryActivity.class.getPackageName(), SecondaryActivity.class.getName());
            startActivity(intent);
        });
    }
}