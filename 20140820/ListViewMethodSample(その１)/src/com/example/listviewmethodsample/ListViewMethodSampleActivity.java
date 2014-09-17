package com.example.listviewmethodsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListViewMethodSampleActivity extends Activity {
	private ArrayAdapter<String> adapter;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_method_sample);

		ListView list = (ListView)findViewById(R.id.list_sample);
		adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_expandable_list_item_1);

//		adapter.add("Item1");
//		adapter.add("Item2");
//		adapter.add("Item3");

		list.setAdapter(adapter);

		Button button = (Button)findViewById(R.id.button_add_item);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				counter++;
				adapter.add("Item" + counter);
			}
		});








	}

}

