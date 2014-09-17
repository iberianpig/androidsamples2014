package com.example.multiactivitysample.v2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	private static final int REQUEST_ADD = 1;
	private static final int REQUEST_EDIT = 2;

	public static final String EXTRA_ID = "id";
	public static final String EXTRA_TITLE = "title";
	public static final String EXTRA_BODY = "body";

	private MemoDBOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		helper = new MemoDBOpenHelper(this);

		MemoDao memoDao = new MemoDao(helper);
		memoDao.open(MemoDao.OPEN_MODE_READ);
		Cursor c = memoDao.findAllCursor();
		memoDao.close();

		String[] from = new String[] { MemoDao.COLUMN_TITLE, MemoDao.COLUMN_BODY };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, from, to, 0);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = true;
		int id = item.getItemId();

		if (id == R.id.operate_additem) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), EditorActivity.class);
			startActivityForResult(intent, REQUEST_ADD);
		} else if(id == R.id.operate_delteitem) {
			SimpleCursorAdapter adapter = (SimpleCursorAdapter)getListAdapter();
			if(adapter.getCount() > 0) {
				long deleteId = adapter.getItemId(adapter.getCount() - 1);
				MemoDao memoDao = new MemoDao(helper);
				memoDao.open(MemoDao.OPEN_MODE_WRITE);
				memoDao.delete(deleteId);
				memoDao.close();
				reloadCursor();
			}
		} else {
			result = super.onOptionsItemSelected(item);
		}
		return result;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			MemoDao memoDao = new MemoDao(helper);
			memoDao.open(MemoDao.OPEN_MODE_WRITE);
			Memo memo = new Memo(-1,
					data.getStringExtra(EXTRA_TITLE),
					data.getStringExtra(EXTRA_BODY));
			if(requestCode == REQUEST_ADD) {
				memoDao.insert(memo);
			} else if(requestCode == REQUEST_EDIT) {
				long id = data.getLongExtra(EXTRA_ID, 0);
				memo.setId((int)id);
				memoDao.update(memo);
			}
			memoDao.close();
			reloadCursor();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void reloadCursor() {
		MemoDao memoDao = new MemoDao(helper);
		memoDao.open(MemoDao.OPEN_MODE_READ);
		Cursor c = memoDao.findAllCursor();
		memoDao.close();
		SimpleCursorAdapter adapter = (SimpleCursorAdapter) getListAdapter();
		adapter.swapCursor(c);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//super.onListItemClick(l, v, position, id);
		MemoDao memoDao = new MemoDao(helper);
		memoDao.open(MemoDao.OPEN_MODE_READ);
		Cursor c = memoDao.findByIdCursor(id);
		memoDao.close();

		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), EditorActivity.class);
		intent.putExtra(EXTRA_ID, id);
		intent.putExtra(EXTRA_TITLE, c.getString(0));
		intent.putExtra(EXTRA_BODY, c.getString(1));

		startActivityForResult(intent, REQUEST_EDIT);
	}
}
