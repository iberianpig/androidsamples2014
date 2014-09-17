package com.example.servicesample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	private boolean isLoop = true;

	public MyIntentService() {
		super("MyIntentService");
	}

	public MyIntentService(String name) {
		super(name);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v("TEST", "MyIntentService#onCreate");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.v("TEST", "MyIntentService#onHandleIntent");
		isLoop = true;
		for(int i = 0; i < 10 && isLoop == true; i++) {
			try {
				Thread.sleep(1000);
				Log.v("TEST", "COUNT: " + (i + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isLoop = false;
		Log.v("TEST", "MyIntentService#onDestroy");
	}

}
