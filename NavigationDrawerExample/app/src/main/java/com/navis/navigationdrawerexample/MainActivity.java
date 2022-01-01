package com.navis.navigationdrawerexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    View viewHome = null;
    View viewGallery = null;
    DrawerLayout mainDrawer = null;
    ActionBarDrawerToggle drawerToggle = null;
    androidx.appcompat.widget.Toolbar mainToolbar = null;
    NavigationView navigationView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewHome = findViewById(R.id.layoutHome);
        viewGallery = findViewById(R.id.layoutGallery);

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        mainDrawer = findViewById(R.id.main_activity_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, mainDrawer, R.string.draw_open, R.string.draw_close);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mainDrawer.closeDrawers();
                item.setChecked(true);
                if (item.getItemId() == R.id.menuHome)
                {
                    viewHome.setVisibility(View.VISIBLE);
                    viewGallery.setVisibility(View.INVISIBLE);
                }else if (item.getItemId() == R.id.menuGallery)
                {
                    viewHome.setVisibility(View.INVISIBLE);
                    viewGallery.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        drawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        drawerToggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item) == true)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
