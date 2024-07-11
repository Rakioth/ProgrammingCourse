package com.raks.intents.explicit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText firstNum  = findViewById(R.id.first_number);
        final EditText secondNum = findViewById(R.id.second_number);

        findViewById(R.id.button)
                .setOnClickListener(view -> {
                    int num1 = Integer.parseInt(firstNum.getText().toString());
                    int num2 = Integer.parseInt(secondNum.getText().toString());

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ResultActivity.class);
                    intent.putExtra("NUM1", num1);
                    intent.putExtra("NUM2", num2);

                    startActivity(intent);
                });
    }

}