package com.example.listviewchoicemodesample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
				this, android.R.layout.simple_list_item_checked, items);

		list = (ListView)findViewById(R.id.list_data);
		//list.setChoiceMode(ListView.CHOICE_MODE_NONE);
		//list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		list.setAdapter(adapter);
		list.setOnItemClickListener(itemClick);

		findViewById(R.id.button_select).setOnClickListener(buttonClick);
	}

	private OnItemClickListener itemClick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			Log.v("TEST", "CLICK: " + position);
		}
	};

	private OnClickListener buttonClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 選択状態を取得
			SparseBooleanArray checked =
						list.getCheckedItemPositions();
			for(int i = 0; i < checked.size(); i++) {
				Log.v("TEST",
						"I(" + i + ")   K:" + checked.keyAt(i) +
						"  V:" + checked.valueAt(i));
			}

		}
	};
}
