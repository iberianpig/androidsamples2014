package com.example.servicesample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyNotificationService extends IntentService {

	public MyNotificationService() {
		super("MyNotificationService");
		
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v("TEST", "Start Service");
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
