package com.example.listviewsimple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewSimpleActivity extends Activity {

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_simple);

		String[] members = {"aaaa", "bbb", "ffff", "ddddd",
			"123", "456", "789"
		};

		lv = (ListView)findViewById(R.id.list);

		ArrayAdapter<String> adapter =
			new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1,
				members);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view,
					int position,
					long id) {
				ListView listView = (ListView)parent;
				String item = (String)listView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(),
						item + " clicked",
                        Toast.LENGTH_LONG).show();
			}
		});
	}

}
