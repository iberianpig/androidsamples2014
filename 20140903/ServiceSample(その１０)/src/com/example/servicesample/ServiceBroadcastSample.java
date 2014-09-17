package com.example.servicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ServiceBroadcastSample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.id.button_service_broadcast);
		findViewById(R.id.button_service_broadcast).setOnClickListener(startButton);

		receiver = new MyBroadcastReceiver();
	}
	private OnClickListener startButton = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent =
					new Intent(ServiceBroadcastSample.this, MyBroadcastService.class);
				startService(intent);
		}
	};
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	

}
