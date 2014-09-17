package com.example.databasesanple;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class DatabaseSampleActivity extends Activity implements OnClickListener{

	private DBAdapter dbAdapter;
	private ListView itemListView;
	private EditText noteEditText;
	private Button saveButton;
	private NoteListAdapter listAdapter;
	private List<Note> noteList =  new ArrayList<Note>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_sample);
		findViews();
		setListener();

		dbAdapter = new DBAdapter(this);
		listAdapter = new NoteListAdapter();
		itemListView.setAdapter(listAdapter);

		loadNote();
	}

	protected void findViews() {
		itemListView = (ListView)findViewById(R.id.itemListView);
		noteEditText = (EditText)findViewById(R.id.memoEditText);
		saveButton = (Button)findViewById(R.id.saveButton);
	}

	protected void loadNote() {
		noteList.clear();
		dbAdapter.open();
		Cursor c = dbAdapter.getAllNotes();
		if(c.moveToFirst()) {
			do {
				Note note = new Note(
					c.getInt(c.getColumnIndex(DBAdapter.COL_ID)),
					c.getString(c.getColumnIndex(DBAdapter.COL_NOTE)),
					c.getString(c.getColumnIndex(DBAdapter.COL_LASTUPDATE)));
				noteList.add(note);
			} while(c.moveToNext()) ;
		}
		dbAdapter.close();
		listAdapter.notifyDataSetChanged();
	}

	protected void saveItem() {
		dbAdapter.open();
		dbAdapter.saveNote(noteEditText.getText().toString());
		dbAdapter.close();

		noteEditText.setText("");
	}

	protected void setListener() {
		saveButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.saveButton:
			saveItem();
			break;
		}
	}

	private class NoteListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
		}
	}
}

