package com.example.databasesanple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class DatabaseSampleActivity extends Activity implements OnClickListener{

	private DBAdapter dbAdapter;
	private ListView itemListView;
	private EditText noteEditText;
	private Button saveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_sample);
		findViews();
		setListener();

		dbAdapter = new DBAdapter(this);
		loadNote();
	}

	protected void findViews() {
		itemListView = (ListView)findViewById(R.id.itemListView);
		noteEditText = (EditText)findViewById(R.id.memoEditText);
		saveButton = (Button)findViewById(R.id.saveButton);
	}

	protected void loadNote() {
		dbAdapter.open();

		dbAdapter.close();
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

}

