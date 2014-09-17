package com.example.listactivitysample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListActivityNext extends ListActivity {

	private ListView list;
	private ArrayAdapter<String> adapter;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_activity_next);

		adapter = new ArrayAdapter<String>(this,
				R.layout.list_row);
		setListAdapter(adapter);

		Button button = (Button)findViewById(R.id.button_add_item);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				counter++;
				adapter.add("Item" + counter);
			}
		});

		list = getListView();
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String item = (String)list.getItemAtPosition(position);
				adapter.remove(item);
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		super.onListItemClick(l, v, position, id);
	}

}
