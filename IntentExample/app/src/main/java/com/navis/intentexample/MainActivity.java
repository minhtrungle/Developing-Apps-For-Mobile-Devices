package com.navis.intentexample;

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

public class MainActivity extends AppCompatActivity {
    Uri uri = null;
    int ACTION_SELECT_PHOTO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent != null)
        {
            if (intent.getAction() == Intent.ACTION_SEND)
            {
                uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                try {
                    InputStream stream = getContentResolver().openInputStream(uri);
                    Bitmap bm = BitmapFactory.decodeStream(stream);
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageBitmap(bm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (intent.getAction() == Intent.ACTION_SEND_MULTIPLE)
            {

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("VINH","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("VINH","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("VINH","onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("VINH","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("VINH","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("VINH","onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (uri != null) {
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
                Bitmap bm = BitmapFactory.decodeStream(stream);
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSharePhoto(View v)
    {
        if (uri != null) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setAction(Intent.ACTION_SEND);
            startActivity(intent.createChooser(intent, "Hay chon ung dung"));
        }
    }
    public void onSelectPhoto(View v)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
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
                        InputStream is = getContentResolver().openInputStream(uri);
                        Bitmap bm = BitmapFactory.decodeStream(is);
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
