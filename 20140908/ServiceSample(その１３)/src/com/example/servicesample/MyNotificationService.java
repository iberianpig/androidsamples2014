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
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v("TEST", "Destroy Service");



	}

}
