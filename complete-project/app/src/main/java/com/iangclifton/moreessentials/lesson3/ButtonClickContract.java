package com.iangclifton.moreessentials.lesson3;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Ian G. Clifton
 */
public class ButtonClickContract implements BaseColumns {

    /**
     * The name of the SQLite table
     */
    public static final String TABLE_NAME = "buttonClick";

    /**
     * When the button was clicked
     */
    public static final String COLUMN_TIMESTAMP = "timestamp";

    /**
     * CREATE TABLE buttonClick (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp LONG NOT NULL);
     */
    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME +
            " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TIMESTAMP + " LONG NOT NULL" +
            ");";



    public static final String AUTHORITY = "com.iangclifton.moreessentials";

    public static final Uri CONTENT_URI;

    // Build out our Uri
    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content")
               .authority(AUTHORITY)
               .path(TABLE_NAME);
        CONTENT_URI = builder.build();
    }



}
