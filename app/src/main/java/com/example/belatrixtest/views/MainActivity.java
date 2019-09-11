package com.example.belatrixtest.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.example.belatrixtest.R;
import com.example.belatrixtest.utils.NavigationHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToShake(View view) {
        NavigationHelper.goToActivity(this, ShakeActivity.class);
    }

    public void goToCompass(View view) {
        NavigationHelper.goToActivity(this, CompassActivity.class);
    }
}
