package com.example.belatrixtest.views;

import androidx.appcompat.app.AppCompatActivity;
import com.example.belatrixtest.R;
import com.example.belatrixtest.utils.CompassView;
import android.os.Bundle;
import android.view.MenuItem;

public class CompassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        setTitle(R.string.view_compass_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}