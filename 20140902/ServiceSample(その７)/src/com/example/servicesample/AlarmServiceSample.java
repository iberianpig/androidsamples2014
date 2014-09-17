package com.example.servicesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AlarmServiceSample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servie_alarm);

		findViewById(R.id.button_schedule).setOnClickListener(scheduleButton);
		findViewById(R.id.button_cancel).setOnClickListener(cancelButton);
	}

	private OnClickListener scheduleButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};
	private OnClickListener cancelButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};
}
