package com.raks.images;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        findViewById(R.id.load_button).setOnClickListener(view -> {
            ImageView imageView = findViewById(R.id.image_view);
            Picasso.get().load("https://picsum.photos/200/300")
                   .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                   .networkPolicy(NetworkPolicy.NO_CACHE)
                   .into(imageView);
        });
    }

}