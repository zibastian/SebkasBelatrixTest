package com.example.belatrixtest.listeners;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeListener implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY   = 2.7F;
    private static final int   SHAKE_SLOP_TIME_MS        = 500;
    private static final int   SHAKE_COUNT_RESET_TIME_MS = 3000;

    public static final int SHAKE    = 1;
    public static final int NO_SHAKE = 2;

    private OnShakeListener mListener;
    private long mShakeTimestamp;

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        void onShake(int type);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (mListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);
            final long now = System.currentTimeMillis();

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }
                mShakeTimestamp = now;
                mListener.onShake(SHAKE);
            }else{
                // reset the shake after 3 seconds of no shakes
                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mListener.onShake(NO_SHAKE);
                }
            }
        }
    }
}
