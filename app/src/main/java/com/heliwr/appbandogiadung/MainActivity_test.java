package com.heliwr.appbandogiadung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity_test extends AppCompatActivity {
    private ImageView largeImage;
    private ImageButton smallImage1;
    private ImageButton smallImage2;
    private ImageButton smallImage3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        largeImage = findViewById(R.id.largeImage);
        smallImage1 = findViewById(R.id.smallImage1);
        smallImage2 = findViewById(R.id.smallImage2);
        smallImage3 = findViewById(R.id.smallImage3);
        smallImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                largeImage.setImageResource(R.drawable.slide1);
            }
        });
        smallImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                largeImage.setImageResource(R.drawable.slide2);
            }
        });

        smallImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                largeImage.setImageResource(R.drawable.slide3);
            }
        });
    }
}