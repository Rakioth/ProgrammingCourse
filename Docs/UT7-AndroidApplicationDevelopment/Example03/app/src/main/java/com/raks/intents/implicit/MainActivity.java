package com.raks.intents.implicit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String GEO_0_0_Q_S = "geo:0,0?q=%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input = findViewById(R.id.address);

        input.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                String address   = Uri.encode(textView.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                Uri    uri       = Uri.parse(String.format(GEO_0_0_Q_S, address));

                mapIntent.setData(uri);
                startActivity(mapIntent);
                return true;
            }
            return false;
        });
    }

}