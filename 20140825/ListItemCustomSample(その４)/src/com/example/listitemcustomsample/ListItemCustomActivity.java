package com.example.listitemcustomsample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListItemCustomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_custom);

		List<SampleData> list = new ArrayList<SampleData>();
		for(int i = 0; i < 10; i++) {
			list.add(
				new SampleData(
						R.drawable.ic_launcher, "項目:",  String.valueOf(i)));
		}

		ListView listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new CustomAdapter(this, list));
	}

}
