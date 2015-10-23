package com.iangclifton.moreessentials.lesson3;

import android.util.Log;

import com.iangclifton.moreessentials.lesson1.ResultHandler;

import java.util.List;

/**
 * @author Ian G. Clifton
 */
public class TimestampHandler extends ResultHandler<List<Long>> {
    private static final String TAG = "TimestampHandler";

    @Override
    public void onFailure() {
        Log.w(TAG, "TimestampHandler's onFailure called");
    }

    @Override
    public void onSuccess(List<Long> longList) {
        Log.d(TAG, "Got results; size: " + longList.size());
        for (Long timestamp : longList) {
            Log.d(TAG, "- " + timestamp);
        }
    }
}
