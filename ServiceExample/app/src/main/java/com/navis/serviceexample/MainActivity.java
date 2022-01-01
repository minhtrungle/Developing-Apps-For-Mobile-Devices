package com.navis.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MyService myService = null;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService = ((MyService.MyBinder)iBinder).getService();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textCount = findViewById(R.id.textCount);
                            textCount.setText("My service is running: " + Integer.toString(myService.getCount()));
                        }
                    });
                }
            }, 0, 5000);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onStartService(View v)
    {
        MyService.uiHandler = new Handler();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
    public void onBindService(View v)
    {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }
    public void onUnBindService(View v)
    {
        if (connection != null) {
            Intent intent = new Intent(this, MyService.class);
            unbindService(connection);
        }
    }
    public void onStopService(View v)
    {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
