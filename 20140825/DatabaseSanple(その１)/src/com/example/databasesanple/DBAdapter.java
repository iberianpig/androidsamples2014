package com.example.databasesanple;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	static final String DATABASE_NAME = "mynote.db";
	static final int DATABASE_VERSION = 1;

	private Context context;

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
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

}
