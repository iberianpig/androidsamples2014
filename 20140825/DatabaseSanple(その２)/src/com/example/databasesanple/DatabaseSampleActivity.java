package com.example.databasesanple;

import android.app.Activity;
import android.os.Bundle;

public class DatabaseSampleActivity extends Activity {

	private DBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_sample);

		dbAdapter = new DBAdapter(this);
		loadNote();
	}

	protected void loadNote() {
		dbAdapter.open();

		dbAdapter.close();
	}



}

