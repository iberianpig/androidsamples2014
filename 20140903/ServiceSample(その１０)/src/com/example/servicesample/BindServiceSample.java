package com.example.servicesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class BindServiceSample extends Activity {


	private MyBindService myService;
	private Intent serviceIntent;

	ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v("TEST", "BindServiceSample#onServiceConnected");
			myService = ((MyBindService.MyBinder)service).getService();
			myService.setMessage("Hello, IBinder");
			startService(serviceIntent);
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v("TEST", "BindServiceSample#onServiceDisconnected");
			myService = null;
		}
	};

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
			serviceIntent = new Intent(
				BindServiceSample.this, MyBindService.class);
			bindService(
				serviceIntent,
				serviceConnection,
				BIND_AUTO_CREATE);
		}
	};

	private OnClickListener unbindButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};
}
