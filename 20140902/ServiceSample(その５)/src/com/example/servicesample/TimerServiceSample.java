package com.example.servicesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TimerServiceSample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_common);

		findViewById(R.id.button_start).setOnClickListener(startClick);
		findViewById(R.id.button_stop).setOnClickListener(stopClick);
	}
	private OnClickListener startClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

	private OnClickListener stopClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

}
