package com.example.databasesanple;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	static final String DATABASE_NAME = "mynote.db";
	static final int DATABASE_VERSION = 1;

	public static final String TABLE_NAME = "notes";
	public static final String COL_ID = "_id";
	public static final String COL_NOTE = "note";
	public static final String COL_LASTUPDATE = "lastupdate";

	protected  Context context;
	protected  DatabaseHelper dbHelper;
	protected  SQLiteDatabase db;

	public DBAdapter(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
					+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ COL_NOTE + " TEXT NOT NULL,"
					+ COL_LASTUPDATE + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	public DBAdapter open() {
		db = dbHelper.getWritableDatabase();	// 書き込みありの場合
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public void saveNote(String note) {
		Date dateNow = new Date();

		ContentValues values = new ContentValues();
		values.put(COL_NOTE, note);
		values.put(COL_LASTUPDATE, dateNow.toLocaleString());
		db.insertOrThrow(TABLE_NAME, null, values);
	}


}
