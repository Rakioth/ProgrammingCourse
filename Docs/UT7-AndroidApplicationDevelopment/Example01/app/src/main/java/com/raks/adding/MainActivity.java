package com.raks.adding;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText firstNum  = findViewById(R.id.first_number);
        final EditText secondNum = findViewById(R.id.second_number);

        findViewById(R.id.add_button)
                .setOnClickListener(view -> {
                    int num1 = Integer.parseInt(firstNum.getText().toString());
                    int num2 = Integer.parseInt(secondNum.getText().toString());

                    ((TextView) findViewById(R.id.result))
                            .setText(String.valueOf(num1 + num2));
                });
    }

}