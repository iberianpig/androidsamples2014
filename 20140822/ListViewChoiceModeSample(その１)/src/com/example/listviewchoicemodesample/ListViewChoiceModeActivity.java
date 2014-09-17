package com.example.listviewchoicemodesample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewChoiceModeActivity extends Activity {

	private ArrayAdapter<String> adapter;
	private String[] items = {"Item1", "Item2", "Item3", "Item4" };
	private ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_choice_mode);

		adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, items);

		list = (ListView)findViewById(R.id.list_data);
		list.setAdapter(adapter);
	}
}
