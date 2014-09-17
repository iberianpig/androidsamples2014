package com.example.servicesample;

import android.app.IntentService;
import android.content.Intent;

public class MyBroadcastService extends IntentService {

	public MyBroadcastService() {
		super("MyBroadcastService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		try {
			Thread.sleep(5000);

			Intent broadcastIntent = new Intent();
			broadcastIntent.putExtra("message", "Hello Broadcast");
			broadcastIntent.setAction("MY_ACTION");
			sendBroadcast(broadcastIntent);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
