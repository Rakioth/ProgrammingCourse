package com.raks.intents.explicit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int    num1   = (int) intent.getSerializableExtra("NUM1");
        int    num2   = (int) intent.getSerializableExtra("NUM2");

        ((TextView) findViewById(R.id.result))
                .setText(String.format("%d + %d = %d", num1, num2, num1 + num2));
    }

}