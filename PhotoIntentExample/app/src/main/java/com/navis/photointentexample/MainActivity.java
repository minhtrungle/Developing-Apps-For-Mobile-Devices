package com.navis.photointentexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    Uri uri = null;
    int ACTION_SELECT_PHOTO = 1;

    @Override
    protected void onDestroy() {
        Log.d("VINH", "OnDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d("VINH", "OnStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d("VINH", "OnPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("VINH", "OnResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("VINH", "OnStart");
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (uri != null)
        {
            outState.putParcelable("ImageURI", uri);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uri = savedInstanceState.getParcelable("ImageURI");
        if (uri != null)
        {
            try {
                InputStream stream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("VINH", "OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.getAction() == Intent.ACTION_SEND)
        {
            uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
            try {
                InputStream stream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (intent.getAction() == Intent.ACTION_SEND_MULTIPLE)
        {

        }
    }
    public void onSharePhoto(View v)
    {
        if (uri != null) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(intent.createChooser(intent, "Hay chon ung dung de share"));
        }
    }
    public void onOpenPhoto(View v)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ACTION_SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_SELECT_PHOTO)
        {
            if (resultCode == RESULT_OK)
            {
                if (data != null)
                {
                    uri = data.getData();
                    try {
                        InputStream stream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(stream);
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
