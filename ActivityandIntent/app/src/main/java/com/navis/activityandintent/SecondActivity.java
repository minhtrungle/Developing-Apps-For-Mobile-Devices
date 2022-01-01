package com.navis.activityandintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String param = intent.getStringExtra("MyParameter");
        Toast toast = Toast.makeText(getApplicationContext(), param, Toast.LENGTH_LONG);
        toast.show();
    }
}
