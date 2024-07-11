package com.raks.images;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.glide_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(this.getClass().getPackageName(), GlideActivity.class.getName());
                startActivity(intent);
            }
        });

        findViewById(R.id.picasso_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(this.getClass().getPackageName(), PicassoActivity.class.getName());
                startActivity(intent);
            }
        });

        findViewById(R.id.scale_type_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(this.getClass().getPackageName(), ImagesActivity.class.getName());
                startActivity(intent);
            }
        });
    }

}