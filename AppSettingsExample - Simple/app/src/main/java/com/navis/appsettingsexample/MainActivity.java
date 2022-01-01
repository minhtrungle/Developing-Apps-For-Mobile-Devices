package com.navis.appsettingsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textCount = null;
    SharedPreferences sharedPreferences = null;
    String preferenceName = "com.navis.appsettingsexample";

    SharedPreferences.OnSharedPreferenceChangeListener changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            Toast t = Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
            t.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCount = findViewById(R.id.textCount);
        sharedPreferences = getSharedPreferences(preferenceName, MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(changeListener);
        int color = sharedPreferences.getInt("COLOR", 0);
        textCount.setBackgroundColor(color);
        int count = sharedPreferences.getInt("COUNT", 0);
        textCount.setText(Integer.toString(count));
    }
    public void onChangeColor(View v)
    {
        int color = ((ColorDrawable)v.getBackground()).getColor();
        textCount.setBackgroundColor(color);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("COLOR", color);
        editor.apply();
    }
    public void onCount(View v)
    {
        int current = Integer.parseInt((String)textCount.getText());
        textCount.setText(Integer.toString(current + 1));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("COUNT", current + 1);
        editor.apply();
    }
}
