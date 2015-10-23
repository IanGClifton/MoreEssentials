package com.iangclifton.moreessentials.lesson4;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.iangclifton.moreessentials.lesson3.ButtonClickContract;
import com.iangclifton.moreessentials.lesson3.DbHelper;

public class ClickProvider extends ContentProvider {
    private static final String TAG = "ClickProvider";

    private static final String AUTHORITY = "com.iangclifton.moreessentials";

    private static final String MIME_SINGULAR = "vnd.android.cursor.item/vnd.com.iangclifton.moreessentials." + ButtonClickContract.TABLE_NAME;
    private static final String MIME_PLURAL = "vnd.android.cursor.dir/vnd.com.iangclifton.moreessentials." + ButtonClickContract.TABLE_NAME;

    // For URI matcher to use with a switch statement
    private static final int CLICKS = 1;
    private static final int CLICKS_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY, ButtonClickContract.TABLE_NAME, CLICKS);
        sUriMatcher.addURI(AUTHORITY, ButtonClickContract.TABLE_NAME + "/#", CLICKS_ID);
    }

    private DbHelper mDbHelper;

    public ClickProvider() {
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CLICKS:
                return MIME_PLURAL;
            case CLICKS_ID:
                return MIME_SINGULAR;
            default:
                Log.w(TAG, "Invalid URI: " + uri);
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.d(TAG, "ClickProvider's query called with\n" + uri);
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CLICKS:
                // Querying multiple clicks
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = ButtonClickContract.COLUMN_TIMESTAMP + " DESC";
                }
                break;
            case CLICKS_ID:
                // Querying a single click
                selection = selection + " " + ButtonClickContract._ID + " = " + uri.getLastPathSegment();
                break;
        }

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                ButtonClickContract.TABLE_NAME, // Table
                projection, // Columns
                selection, // Selection
                selectionArgs, // Selection args
                null, // groupBy
                null, // Having
                sortOrder, // orderBy
                null // limit
        );
        //db.close();

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
