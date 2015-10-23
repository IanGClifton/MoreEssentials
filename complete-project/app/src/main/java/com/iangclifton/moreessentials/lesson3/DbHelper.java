package com.iangclifton.moreessentials.lesson3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Button;

import com.iangclifton.moreessentials.lesson1.ExecutorHelper;
import com.iangclifton.moreessentials.lesson1.ResultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author Ian G. Clifton
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";

    private static final String DATABASE_NAME = "myDatabase";

    private static final int VERSION = 2;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static void addClick(final Context context, final long timestamp) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(ButtonClickContract.COLUMN_TIMESTAMP, timestamp);

                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long row = db.insert(ButtonClickContract.TABLE_NAME, null, contentValues);
                db.close();
                Log.d(TAG, "Successfully inserted row: " + row);
            }
        };
        // Run on database thread
        ExecutorHelper.staticExecute(runnable);
    }

    /**
     * Gets the most recent five click timestamps on a background thread
     *
     * @param context Context to get the database access with
     * @param handler Handler to receive results; results will be an empty list if table is empty
     */
    public static void getLastFiveClickTimes(final Context context, final TimestampHandler handler) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Query the database
                DbHelper dbHelper = new DbHelper(context);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String[] columns = {ButtonClickContract._ID, ButtonClickContract.COLUMN_TIMESTAMP};
                Cursor cursor = db.query(
                        ButtonClickContract.TABLE_NAME, // Table
                        columns, // Columns
                        null, // Selection
                        null, // Selection args
                        null, // groupBy
                        null, // Having
                        ButtonClickContract.COLUMN_TIMESTAMP + " DESC", // orderBy
                        "5" // limit
                );

                List<Long> results = new ArrayList<>();
                int columnIndex = cursor.getColumnIndex(ButtonClickContract.COLUMN_TIMESTAMP);
                while (cursor.moveToNext()) {
                    // _ID, COLUMN_TIMESTAMP
                    results.add(cursor.getLong(columnIndex));
                }
                handler.sendSuccess(results);

                cursor.close();
                db.close();
            }
        };
        // Run on database thread
        ExecutorHelper.staticExecute(runnable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create necessary tables
        Log.d(TAG, "Creating table:\n" + ButtonClickContract.CREATE_STATEMENT);
        db.execSQL(ButtonClickContract.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Called when version changes
    }
}
