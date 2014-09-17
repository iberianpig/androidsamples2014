package com.example.multiactivitysample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDBOpenHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "MEMO_DATA";
	private static final int DATABASE_VERSION = 1;

	private static final String SQL_CREATE_TABLE =
		String.format("CREATE TABLE %1$s ( %2$s INTEGER PRIMARY KEY AUTOINCREMENT, %3$s TEXT NOT NULL, %4$s TEXT);"	,
				MainActivity.TABLE_NAME, MainActivity.COLUMN_ID,
				MainActivity.COLUMN_TITLE, MainActivity.COLUMN_BODY);

	public MemoDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
