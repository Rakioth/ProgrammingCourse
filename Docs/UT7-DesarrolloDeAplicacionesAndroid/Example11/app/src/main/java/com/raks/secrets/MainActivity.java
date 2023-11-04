package com.raks.secrets;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView) findViewById(R.id.secret_one_holder)).setText(BuildConfig.SECRET_ONE);
        ((TextView) findViewById(R.id.secret_two_holder)).setText(BuildConfig.SECRET_TWO);
    }

}