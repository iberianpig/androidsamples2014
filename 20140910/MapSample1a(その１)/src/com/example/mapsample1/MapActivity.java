package com.example.mapsample1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		// フラグメントトランザクションを開始する
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();

		LocationMapFragment fragment = new LocationMapFragment();
		if (getIntent() != null && getIntent().getExtras() != null) {
			// インテントがあれば、その値をフラグメントに設定する
			fragment.setArguments(getIntent().getExtras());
		}
		// 表示
		transaction.add(R.id.container, fragment);
		transaction.commit();
	}

}
