package com.example.multiactivitysample;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	public static final String TABLE_NAME = "memo_data";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_BODY = "body";

	private static final int REQUEST_ADD = 1;
	private static final int REQUEST_EDIT = 2;

	public static final String EXTRA_ID = "id";
	public static final String EXTRA_TITLE = "title";
	public static final String EXTRA_BODY = "body";

	private static final String SQL_WHERE_ID = COLUMN_ID + " = ?";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		Cursor c = cr.query(MemoDBContentProvider.CONTENT_URI,
				null, null, null, null);

		String[] from = new String[] { COLUMN_TITLE, COLUMN_BODY };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, from, to, 0);

		setListAdapter(adapter);

		LoaderManager manager = getLoaderManager();
		manager.initLoader(1, null, callbacks);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result;
		int id = item.getItemId();

		if (id == R.id.operate_additem) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), EditorActivity.class);
			startActivityForResult(intent, REQUEST_ADD);
			result = true;
		} else if(id == R.id.operate_delteitem) {
			SimpleCursorAdapter adapter = (SimpleCursorAdapter)getListAdapter();
			if(adapter.getCount() > 0) {
				long idx = adapter.getItemId(adapter.getCount() - 1);
				ContentResolver cr = getContentResolver();
				String[] whereargs = new String[] { Long.toString(idx) };
				cr.delete(MemoDBContentProvider.CONTENT_URI,
											SQL_WHERE_ID, whereargs);
			}
			result = true;
		} else {
			result = super.onOptionsItemSelected(item);
		}
		return result;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			ContentResolver cr = getContentResolver();
			ContentValues values = new ContentValues();
			values.put(COLUMN_TITLE, data.getStringExtra(EXTRA_TITLE));
			values.put(COLUMN_BODY, data.getStringExtra(EXTRA_BODY));
			if(requestCode == REQUEST_ADD) {
				cr.insert(MemoDBContentProvider.CONTENT_URI, values);
			} else if(requestCode == REQUEST_EDIT) {
				long id = data.getLongExtra(EXTRA_ID, 0);
				// 画面が既存項目の変更目的で開かれた場合、更新クエリの送信を行う
				String[] whereargs = new String[] { Long.toString(id) };
				cr.update(MemoDBContentProvider.CONTENT_URI,
						values, SQL_WHERE_ID, whereargs);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	private void reloadCursor() {
		MemoDBOpenHelper helper = new MemoDBOpenHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

		SimpleCursorAdapter adapter = (SimpleCursorAdapter) getListAdapter();
		adapter.swapCursor(c);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		ContentResolver cr = getContentResolver();

		String[] projection = new String[] { COLUMN_TITLE, COLUMN_BODY };
		String[] whereargs = new String[] { Long.toString(id) };
		// 値の取得
		Cursor c = cr.query(MemoDBContentProvider.CONTENT_URI,
				 projection, SQL_WHERE_ID, whereargs, null);
		c.moveToFirst();

		// 編集画面を表示する
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), EditorActivity.class);
		intent.putExtra(EXTRA_ID, id);
		// データベースから取得した値の設定
		intent.putExtra(EXTRA_TITLE, c.getString(0));
		intent.putExtra(EXTRA_BODY, c.getString(1));

		// アクティビティの表示
		startActivityForResult(intent, REQUEST_EDIT);
	}

	//---------------------------------------------
	private LoaderCallbacks<Cursor> callbacks = new LoaderCallbacks<Cursor>() {
		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			Loader<Cursor> loader = null;
			if(id == 1) {
				loader = new CursorLoader(MainActivity.this,
						MemoDBContentProvider.CONTENT_URI,
						null, null, null, null);
			}
			return loader;
		}
		@Override
		public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
			SimpleCursorAdapter adapter =
					(SimpleCursorAdapter)getListAdapter();
			adapter.swapCursor(c);
		}
		@Override
		public void onLoaderReset(Loader<Cursor> loader) {
			SimpleCursorAdapter adapter =
					(SimpleCursorAdapter)getListAdapter();
			adapter.swapCursor(null);
		}
	};
	//---------------------------------------------
}
