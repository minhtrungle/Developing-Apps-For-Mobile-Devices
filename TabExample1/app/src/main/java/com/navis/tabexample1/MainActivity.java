package com.navis.tabexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout = null;
    View tab1 = null;
    View tab2 = null;
    View tab3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("TAB 1"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 2"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 3"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("TAB 1"))
                {
                    tab1.setVisibility(View.VISIBLE);
                    tab2.setVisibility(View.INVISIBLE);
                    tab3.setVisibility(View.INVISIBLE);
                }
                if (tab.getText().equals("TAB 2"))
                {
                    tab1.setVisibility(View.INVISIBLE);
                    tab2.setVisibility(View.VISIBLE);
                    tab3.setVisibility(View.INVISIBLE);
                }
                if (tab.getText().equals("TAB 3"))
                {
                    tab1.setVisibility(View.INVISIBLE);
                    tab2.setVisibility(View.INVISIBLE);
                    tab3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
