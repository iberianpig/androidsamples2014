package com.example.multiactivitysample.v2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MemoDao {
	public static final int OPEN_MODE_WRITE = 0;
	public static final int OPEN_MODE_READ = 0;

	public static final String TABLE_NAME = "memo_data";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_BODY = "body";
	public static final String SQL_WHERE_ID = COLUMN_ID + " = ?";

	private MemoDBOpenHelper helper;
	private SQLiteDatabase db = null;

	public MemoDao(MemoDBOpenHelper helper) {
		this.helper = helper;
	}

	public void open(int mode) {
		if(db == null) {
			db = (mode == OPEN_MODE_WRITE)
					? helper.getWritableDatabase()
					: helper.getReadableDatabase();
		}
	}

	public void close() {
		if(db != null) {
			db.close();
			db = null;
		}
	}

	public long insert(Memo memo) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, memo.getTitle());
		values.put(COLUMN_BODY, memo.getBody());
		return db.insert(TABLE_NAME, null, values);
	}

	public int update(Memo memo) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, memo.getTitle());
		values.put(COLUMN_BODY, memo.getBody());
		long id = memo.getId();
		String[] whereargs = new String[] { Long.toString(id) };
		return db.update(TABLE_NAME, values, SQL_WHERE_ID, whereargs);
	}

	public int delete(long id) {
		String[] whereargs = new String[] { Long.toString(id) };
		return db.delete(TABLE_NAME, SQL_WHERE_ID, whereargs);
	}

	public List<Memo> findAll() {
		List<Memo> memoList = new ArrayList<Memo>();
		Cursor c = findAllCursor();
		if(c.moveToFirst()) {
			do {
				Memo memo = new Memo(c.getInt(0), c.getString(1), c.getString(2));
				memoList.add(memo);
			} while(c.moveToNext());
		}
		return memoList;
	}

	public Cursor findAllCursor() {
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		c.moveToFirst();
		return c;
	}

	public Memo findById(long id) {
		Cursor c = findByIdCursor(id);
		return new Memo(c.getInt(0), c.getString(1), c.getString(2));
	}

	public Cursor findByIdCursor(long id) {
		String[] columns = new String[] { COLUMN_TITLE, COLUMN_BODY };
		String[] whereargs = new String[] { Long.toString(id) };
		Cursor c = db.query(TABLE_NAME, columns, SQL_WHERE_ID, whereargs, null,	null, null);
		c.moveToFirst();
		return c;
	}

}
