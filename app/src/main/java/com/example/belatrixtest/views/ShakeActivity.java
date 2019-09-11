package com.example.belatrixtest.views;

import androidx.appcompat.app.AppCompatActivity;
import com.example.belatrixtest.R;
import com.example.belatrixtest.listeners.ShakeListener;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ShakeActivity extends AppCompatActivity implements ShakeListener.OnShakeListener {

    private SensorManager mSensorManager;
    private Sensor        mAccelerometer;
    private ShakeListener mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        setTitle(R.string.view_shake_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeListener();
        mShakeDetector.setOnShakeListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShake(int type) {
        TextView tv = findViewById(R.id.shake_result);
        int colorResultTextView = Color.BLACK;
        int textResultTextView  = R.string.shake_default_no_shake_text;

        switch (type){
            case ShakeListener.SHAKE:
                textResultTextView = R.string.shake_default_shake_text;
                colorResultTextView = Color.GREEN;
                break;
            case ShakeListener.NO_SHAKE:
                textResultTextView = R.string.shake_default_no_shake_text;
                colorResultTextView = Color.BLACK;
                break;
        }

        tv.setText(textResultTextView);
        tv.setTextColor(colorResultTextView);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
