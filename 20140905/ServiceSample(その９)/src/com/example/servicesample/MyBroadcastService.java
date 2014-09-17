package com.example.servicesample;

import android.app.IntentService;
import android.content.Intent;

public class MyBroadcastService extends IntentService {

	public MyBroadcastService(String name) {
		super("MyBroadcastService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
