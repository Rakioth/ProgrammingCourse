package com.raks.adding;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(v -> {
            EditText firstNumberEditText  = findViewById(R.id.first_number);
            String   firstNumberText      = firstNumberEditText.getText().toString();
            int      firstNumber          = Integer.parseInt(firstNumberText);
            EditText secondNumberEditText = findViewById(R.id.second_number);
            String   secondNumberText     = secondNumberEditText.getText().toString();
            int      secondNumber         = Integer.parseInt(secondNumberText);
            ((TextView) findViewById(R.id.result)).setText(String.valueOf(firstNumber + secondNumber));
        });
    }
}
