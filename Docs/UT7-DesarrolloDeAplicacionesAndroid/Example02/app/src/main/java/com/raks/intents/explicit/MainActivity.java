package com.raks.intents.explicit;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText firstNum  = findViewById(R.id.first_number);
        final EditText secondNum = findViewById(R.id.second_number);
        Button         btn       = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            int    num1   = Integer.parseInt(firstNum.getText().toString());
            int    num2   = Integer.parseInt(secondNum.getText().toString());
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ResultActivity.class);
            intent.putExtra("NUM1", num1);
            intent.putExtra("NUM2", num2);
            startActivity(intent);
        });
    }
}
