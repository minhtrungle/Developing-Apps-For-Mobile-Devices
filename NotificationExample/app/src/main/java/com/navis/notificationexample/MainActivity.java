package com.navis.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String MY_CHANNEL = "My Notification Channel";
    String MY_CHANNEL_NAME = "My Notification Channel";
    int MY_NOTIFICATION1_ID = 1;
    NotificationChannel channel = null;
    NotificationManager manager = null;
    NotificationCompat.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(MY_CHANNEL, MY_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        }
        builder = new NotificationCompat.Builder(this, MY_CHANNEL);
    }
    public void onNotify(View v)
    {
        try {
            Intent photoIntent = new Intent();
            photoIntent.setType("image/*");
            photoIntent.setAction(Intent.ACTION_GET_CONTENT);
            PendingIntent openPhoto = PendingIntent.getActivity(this, 0, photoIntent, 0);

            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationCompat.BigPictureStyle  bigPictureStyle = new NotificationCompat.BigPictureStyle();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
            bigPictureStyle.bigPicture(bitmap);
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder.setContentTitle("Chu y");
            builder.setContentText("Da download xong.");
            builder.setContentIntent(pendingIntent);
            builder.addAction(0, "Select Photo", openPhoto);
            builder.setStyle(bigPictureStyle);
            Notification notification = builder.build();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.createNotificationChannel(channel);
            }
            manager.notify(MY_NOTIFICATION1_ID, notification);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void onUpdate(View v)
    {
        builder.setContentText("Da update noi dung.");
        Notification notification = builder.build();
        manager.notify(MY_NOTIFICATION1_ID, notification);
    }
    public void onCancel(View v)
    {
        manager.cancel(MY_NOTIFICATION1_ID);
    }
}
