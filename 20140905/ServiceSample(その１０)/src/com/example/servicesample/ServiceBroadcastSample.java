package com.example.servicesample;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ServiceBroadcastSample extends Activity {

	private MyBroadcastReceiver receiver;
	private IntentFilter intentFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_broadcast);
		findViewById(R.id.button_service_broadcast).setOnClickListener(startButton);

		receiver = new MyBroadcastReceiver();
		intentFilter = new IntentFilter();
		intentFilter.addAction("MY_ACTION");
		registerReceiver(receiver, intentFilter);
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

	public void setMessage(String message) {
		TextView text = (TextView)findViewById(R.id.text_broadcast);
	}

}
