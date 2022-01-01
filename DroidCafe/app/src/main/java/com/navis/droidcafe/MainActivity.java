package com.navis.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String selectedItem = "";
    View.OnTouchListener ImageTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    view.setAlpha(0.5f);
                    break;
                case MotionEvent.ACTION_UP:
                    view.setAlpha(1.0f);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageDonut = findViewById(R.id.imageDonut);
        imageDonut.setOnTouchListener(ImageTouch);
        ImageView imageIcecream = findViewById(R.id.imageIcecream);
        imageIcecream.setOnTouchListener(ImageTouch);
        ImageView imageFoyo = findViewById(R.id.imageFroyo);
        imageFoyo.setOnTouchListener(ImageTouch);
    }

    public void donutClick(View v)
    {
        selectedItem = "Donut";
        Toast t = Toast.makeText(this, "You ordered a donut", Toast.LENGTH_SHORT);
        t.show();
    }
    public void icecreamClick(View v)
    {
        selectedItem = "Icecream";
        Toast t = Toast.makeText(this, "You ordered an icecream", Toast.LENGTH_SHORT);
        t.show();
    }
    public void froyoClick(View v)
    {
        selectedItem = "Froyo";
        Toast t = Toast.makeText(this, "You ordered a froyo", Toast.LENGTH_SHORT);
        t.show();
    }
    public void floatingButtonClick(View v)
    {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("Order", selectedItem);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast t = Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT);
        t.show();
        return super.onOptionsItemSelected(item);
    }
}
