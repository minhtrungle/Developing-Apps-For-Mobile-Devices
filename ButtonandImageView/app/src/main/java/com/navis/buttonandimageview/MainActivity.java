package com.navis.buttonandimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener m_onFloatingButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast t = Toast.makeText(MainActivity.this, "Floating Button Clicked", Toast.LENGTH_SHORT);
            t.show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageSample = findViewById(R.id.imageSample);
        imageSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(MainActivity.this, "Image Clicked", Toast.LENGTH_SHORT);
                t.show();
            }
        });
        FloatingActionButton floatingButton = findViewById(R.id.floatingActionButton);
        floatingButton.setOnClickListener(m_onFloatingButtonClick);
    }
    public void onAlarmButtonClick(View view)
    {
        Toast t = Toast.makeText(this, "Alarm Button Clicked", Toast.LENGTH_SHORT);
        t.show();
    }
}
