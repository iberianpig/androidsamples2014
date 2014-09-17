package com.example.multiactivitysample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MemoDBContentProvider extends ContentProvider {

	public static final String AUTHORITY
		= "com.example.multiactivitysample";
	public static final Uri CONTENT_URI
		= Uri.parse("content://" + AUTHORITY + "/");
	private MemoDBOpenHelper dbHelper;

	public MemoDBContentProvider() {
	}

	@Override
	public boolean onCreate() {
		dbHelper = new MemoDBOpenHelper(getContext());
		return false;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int changes = db.delete(
				MainActivity.TABLE_NAME,
				selection,
				selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return changes;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(
				MainActivity.TABLE_NAME,
				null,
				values);
		Uri resulturi = ContentUris.withAppendedId(CONTENT_URI, id);
		Log.v("TEST", "insert: " + resulturi.toString());
		getContext().getContentResolver().notifyChange(uri, null);
		return resulturi;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = db.query(
				MainActivity.TABLE_NAME,
				projection,
				selection, selectionArgs,
				null,
				null,
				sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int changes = db.update(
				MainActivity.TABLE_NAME,
				values,
				selection,
				selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return changes;
	}
}
