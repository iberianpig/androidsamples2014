package com.example.servicesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class BindServiceSample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_bind);

		findViewById(R.id.button_bind).setOnClickListener(bindButton);
		findViewById(R.id.button_unbind).setOnClickListener(unbindButton);
	}

	private OnClickListener bindButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};
	private OnClickListener unbindButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};
}
