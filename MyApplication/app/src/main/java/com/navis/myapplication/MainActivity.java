package com.navis.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int _count = 0;
    TextView tv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("VINH", "Ung dung HelloWorld!");
        tv = (TextView)findViewById(R.id.textView2);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("VINH", "BUTTON2");
                _count = _count + 1;
                tv.setText(Integer.toString(_count));
            }
        });
    }

    public void onShowToast(View v)
    {
        Log.d("VINH","BUTTON1");
    }
}
