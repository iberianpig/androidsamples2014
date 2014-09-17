package com.example.servicesample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyAlarmService extends IntentService {

	public MyAlarmService() {
		super("MyAlarmService");

	}




	public MyAlarmService(String name) {
		super(name);

	}

	@Override
	protected void onHandleIntent(Intent arg0) {
	Log.v("TEST", "MyAlarmService#onHandleIntent");

	}

}
