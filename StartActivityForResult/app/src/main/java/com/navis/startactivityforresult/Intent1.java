package com.navis.startactivityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Intent1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);
    }
    public void onOK(View v)
    {
        EditText edit = findViewById(R.id.editText1);
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Intent1", edit.getText().toString());
        setResult(RESULT_OK, replyIntent);
        finish();
    }
    public void onCancel(View v)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}
