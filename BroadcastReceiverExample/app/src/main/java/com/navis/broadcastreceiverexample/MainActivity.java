package com.navis.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver = null;
    String MY_ACTION = BuildConfig.APPLICATION_ID + ".MY_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(myReceiver, intentFilter);

        IntentFilter customFilter = new IntentFilter(MY_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, customFilter);
    }
    public void sendBroadcast(View v)
    {
        Intent intent = new Intent(MY_ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
