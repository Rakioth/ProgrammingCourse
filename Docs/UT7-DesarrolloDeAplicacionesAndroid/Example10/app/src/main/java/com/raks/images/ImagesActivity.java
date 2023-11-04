package com.raks.images;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        ArrayList<ImageView.ScaleType> scaleTypes = new ArrayList<>();
        scaleTypes.add(ImageView.ScaleType.CENTER);
        scaleTypes.add(ImageView.ScaleType.CENTER_CROP);
        scaleTypes.add(ImageView.ScaleType.CENTER_INSIDE);
        scaleTypes.add(ImageView.ScaleType.MATRIX);
        scaleTypes.add(ImageView.ScaleType.FIT_CENTER);
        scaleTypes.add(ImageView.ScaleType.FIT_END);
        scaleTypes.add(ImageView.ScaleType.FIT_START);
        scaleTypes.add(ImageView.ScaleType.FIT_XY);

        Spinner                           spinner = findViewById(R.id.spinner);
        ArrayAdapter<ImageView.ScaleType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, scaleTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = findViewById(R.id.image_view);
                imageView.setScaleType(scaleTypes.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ToggleButton toggleButton = findViewById(R.id.toggle);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ImageView imageView = findViewById(R.id.image_view);
            imageView.setAdjustViewBounds(isChecked);
        });
    }

}