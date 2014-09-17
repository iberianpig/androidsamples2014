package com.example.multiactivitysample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MemoDBContentProvider extends ContentProvider {

	public static final String AUTHORITY = " com.example.multiactivitysample";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/");
	private MemoDBOpenHelper dbHelper;

	public MemoDBContentProvider() {
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int changes = db.delete("", selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		
		return changes;
//		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public String getType(Uri uri) {
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(MainActivity.TABLE_NAME, null, values);
		Uri resulturi = ContentUris.withAppendedId(CONTENT_URI, id);
		Log.v("TEST", "insert: " + resulturi.toString());
		
		getContext().getContentResolver().notifyChange(null, null);
		
		return uri;
		
//		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public boolean onCreate() {
		dbHelper = new MemoDBOpenHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		Cursor c=db.query("", projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
		
//		throw new UnsupportedOperationException("Not yet implemented");
	}
}
