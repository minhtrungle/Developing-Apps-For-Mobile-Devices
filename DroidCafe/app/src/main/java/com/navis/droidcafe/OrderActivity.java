package com.navis.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String order = intent.getStringExtra("Order");
        TextView textView = findViewById(R.id.textOrderTitle);
        textView.setText("You ordered a/an " + order.toLowerCase());
    }
}
