package com.example.servicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class IntentServiceSample extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_common);

		findViewById(R.id.button_start).setOnClickListener(startClick);
	}

	private OnClickListener startClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent =
					new Intent(IntentServiceSample.this, MyIntentService.class);
			startService(intent);
		}
	};

}
