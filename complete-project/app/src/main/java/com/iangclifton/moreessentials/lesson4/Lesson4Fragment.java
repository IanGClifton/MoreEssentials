package com.iangclifton.moreessentials.lesson4;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iangclifton.moreessentials.R;
import com.iangclifton.moreessentials.lesson3.ButtonClickContract;

/**
 * Fragment for running the Lesson 4 examples.
 *
 * ContentProviders are an important part of the Android framework, but they
 * require a lot of boilerplate code. This code demonstrates the basic use
 * by showing querying, but the same concepts apply for other methods (such
 * as inserting) as well.
 *
 * @author Ian G. Clifton
 */
public class Lesson4Fragment extends Fragment {
    private static final String TAG = "Lesson4Fragment";

    public Lesson4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson4, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Define the columns we want
        String[] projection = {
                ButtonClickContract._ID,
                ButtonClickContract.COLUMN_TIMESTAMP
        };

        Log.d(TAG, "Querying " + ButtonClickContract.CONTENT_URI);

        // Does a query against the table and returns a Cursor object
        Cursor cursor = getActivity().getContentResolver().query(
                ButtonClickContract.CONTENT_URI,  // The content URI of the words table
                projection,                       // The columns to return for each row
                null,                             // Either null, or the word the user entered
                null,                             // Either empty, or the string the user entered
                null);                            // The sort order for the returned rows


        if (cursor == null) {
            Log.w(TAG, "ContentResolver returned null");
        } else {
            // We have a valid cursor
            if (cursor.getCount() < 1) {
                // No results, do something
                Log.d(TAG, "ContentProvider returned an empty cursor");
            } else {
                int idColumnIndex = cursor.getColumnIndex(ButtonClickContract._ID);
                int timestampColumnIndex = cursor.getColumnIndex(ButtonClickContract.COLUMN_TIMESTAMP);
                while (cursor.moveToNext()) {
                    // Use the data from this row
                    int id = cursor.getInt(idColumnIndex);
                    long timestamp = cursor.getLong(timestampColumnIndex);
                    Log.d(TAG, "Got result: " + id + " -- " + timestamp);
                }
            }
            cursor.close();
        }
    }
}
