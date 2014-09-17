package com.example.listactivitysample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivitySampleActivity extends ListActivity {
	private String[] items =
		{"abc", "123", "xyz", "456"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_activity_sample);
		ArrayAdapter<String> adapter =
			new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				items);

		setListAdapter(adapter);

		// 定義されているＬｉｓｔＶｉｅｗを取得する
		ListView list = getListView();
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view,
					int position,
					long id) {
				Toast.makeText(ListActivitySampleActivity.this,
						"CLICK: " + position, Toast.LENGTH_SHORT)
					.show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_activity_sample, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_next_list) {
			Intent intent =
					new Intent(ListActivitySampleActivity.this,
					ListActivityNext.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
