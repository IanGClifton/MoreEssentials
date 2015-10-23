package com.iangclifton.moreessentials.lesson2;


import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 2 examples.
 *
 * For any foreground usage, you almost always want to register your listener
 * in onResume and unregister it in onPause. We moved code to onAttach and
 * onDetach in order to demonstrate background batching easily, but you
 * would typically create a service for listening to sensor events in the
 * background.
 *
 * @author Ian G. Clifton
 */
public class Lesson2Fragment extends Fragment implements SensorEventListener {
    private static final String TAG = "Lesson2Fragment";

    private static final int REPORT_LATENCY = 1000 * 1000 * 5;

    private Sensor mAccelerometer;
    private Sensor mLightSensor;

    private TextView mTextView;
    private View mEmptyView;

    public Lesson2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lesson2, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.text);
        mEmptyView = rootView.findViewById(R.id.emptyView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTextView = null;
        mEmptyView = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLightSensor == null) {
            Log.d(TAG, "No light sensor available");
        } else {
            sensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_UI);
        }

        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (mAccelerometer == null) {
            Log.d(TAG, "No accelerometer");
        } else {
//            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI, REPORT_LATENCY);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (mLightSensor != null) {
            sensorManager.unregisterListener(this, mLightSensor);
        }
        if (mAccelerometer != null) {
            sensorManager.unregisterListener(this, mAccelerometer);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
//        mLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        if (mLightSensor == null) {
//            Log.d(TAG, "No light sensor available");
//        } else {
//            sensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_UI);
//        }
//
//        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
//        if (mAccelerometer == null) {
//            Log.d(TAG, "No accelerometer");
//        } else {
////            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
//            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI, REPORT_LATENCY);
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
//        if (mLightSensor != null) {
//            sensorManager.unregisterListener(this, mLightSensor);
//        }
//        if (mAccelerometer != null) {
//            sensorManager.unregisterListener(this, mAccelerometer);
//        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mLightSensor) {
            // Light reading
            float lux = event.values[0];
//            mTextView.setText(lux + "lx; max value: " + mLightSensor.getMaximumRange());
        } else {
            // Accelerometer reading
//            Log.d(TAG, "Accelerometer values: " + event.values[0] + " " + event.values[1] + " " + event.values[2]);
            float sensorReading = Math.abs(Math.min(event.values[0], 1));
            int red = (int) (255 * sensorReading);
            sensorReading = Math.abs(Math.min(event.values[1], 1));
            int green = (int) (255 * sensorReading);
            sensorReading = Math.abs(Math.min(event.values[2], 1));
            int blue = (int) (255 * sensorReading);
            mEmptyView.setBackgroundColor(Color.rgb(red, green, blue));
//            Log.d(TAG, "New color: " + Color.rgb(red, green, blue));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged called: " + sensor + "; " + accuracy);
    }
}
