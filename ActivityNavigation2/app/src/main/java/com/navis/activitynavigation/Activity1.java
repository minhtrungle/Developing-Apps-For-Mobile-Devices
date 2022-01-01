package com.navis.activitynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
    }

    public void onOpenActivity2(View v)
    {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
