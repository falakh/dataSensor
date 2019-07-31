package com.example.bantumei;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment =  new DataTanah();
            String key = "";
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    key ="temperatur";

                    break;
                case R.id.navigation_dashboard:
                    key = "tanah";
                    break;
                case R.id.navigation_notifications:
                    key = "angin";
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putString("id",key);
            fragment.setArguments(bundle);
            loadFragment(fragment);
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        String key ="temperatur";
        Fragment fragment =  new DataTanah();
        Bundle bundle = new Bundle();
        bundle.putString("id",key);
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_mainactivity, fragment)
                    .commit();
            return true;
        }
        return false;
    }



}
