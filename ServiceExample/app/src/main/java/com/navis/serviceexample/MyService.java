package com.navis.serviceexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    class MyBinder extends Binder{
        MyService getService()
        {
            return MyService.this;
        }
    }
    class DownloadTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL uri = new URL("http://212.183.159.230/5MB.zip");
                HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    InputStream stream = connection.getInputStream();
                    int oneByte = 0;
                    do {
                        oneByte = stream.read();
                        if (oneByte >= 0) {
                            count += 1;
                        }
                    }while (oneByte >= 0);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
    int NOTIFICATION_ID = 1;
    NotificationCompat.Builder builder = null;
    NotificationChannel channel = null;
    NotificationManager manager = null;
    Timer timer = null;
    public static Handler uiHandler = null;
    int count = 0;
    public int getCount()
    {
        return count;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    TimerTask serviceTask = new TimerTask() {
        @Override
        public void run() {
            try {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Toast t = Toast.makeText(MyService.this, "Service is running: " + Integer.toString(count), Toast.LENGTH_SHORT);
                            t.show();
                            builder.setContentText("My service is running: " + Integer.toString(count));
                            Notification notification = builder.build();
                            manager.notify(NOTIFICATION_ID, notification);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("MY_CHANNEL_ID", "MY_CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        Intent launchIntent = new Intent(this, MainActivity.class);
        PendingIntent launchPending = PendingIntent.getActivity(this, 0, launchIntent,0);
        builder = new NotificationCompat.Builder(MyService.this, "MY_CHANNEL_ID");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("My service is running");
        builder.setContentText("Count: 0");
        builder.setContentIntent(launchPending);
        Notification notification = builder.build();
        startForeground(NOTIFICATION_ID, notification);

        Toast t = Toast.makeText(MyService.this, "Service Started", Toast.LENGTH_LONG);
        t.show();
        new DownloadTask().execute();

        timer = new Timer();
        timer.schedule(serviceTask,0, 5000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast t = Toast.makeText(MyService.this, "Service Stopped", Toast.LENGTH_LONG);
        t.show();
        if (timer != null)
        {
            timer.cancel();
        }
        super.onDestroy();
    }
}
