package com.example.mapsample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		LocationMapFragment fragment = new LocationMapFragment();
		transaction.add(R.id.container, fragment);
		transaction.commit();
	}

}
