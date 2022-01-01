package com.navis.startactivityforresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int ACTION_INTENT1 = 1;
    int ACTION_INTENT2 = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickIntent1(View v)
    {
        Intent intent = new Intent(this, Intent1.class);
        startActivityForResult(intent, ACTION_INTENT1);
    }

    public void onClickIntent2(View v)
    {
        Intent intent = new Intent(this, Intent2.class);
        startActivityForResult(intent, ACTION_INTENT2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_INTENT1)
        {
            if (resultCode == RESULT_OK)
            {
                if (data != null)
                {
                    String result = data.getStringExtra("Intent1");
                    Toast t = Toast.makeText(this, result, Toast.LENGTH_LONG);
                    t.show();;
                }
            }else
            {
                Toast t = Toast.makeText(this, "Intent1 Cancelled", Toast.LENGTH_LONG);
                t.show();;
            }
        }else if (requestCode == ACTION_INTENT2)
        {
            if (resultCode == RESULT_OK)
            {
                if (data != null)
                {
                    String result = data.getStringExtra("Intent2");
                    Toast t = Toast.makeText(this, result, Toast.LENGTH_LONG);
                    t.show();;
                }
            }else
            {
                Toast t = Toast.makeText(this, "Intent2 Cancelled", Toast.LENGTH_LONG);
                t.show();;
            }
        }
    }
}
