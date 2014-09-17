/**
 *
 */
package com.example.mapsample;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class LocationListFragment extends ListFragment {

	public LocationListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> list = new ArrayAdapter<String>(
				getActivity(),
				android.R.layout.simple_list_item_1);
		list.add("富士山");
		list.add("横浜駅");
		list.add("日本Google株式会社");
		list.add("Google Inc.");
		setListAdapter(list);
	}




}
