package com.example.servicesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MySimpleService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v("TEST", "MySimpleService#onCreate");
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v("TEST", "MySimpleService#onStartCommand");
		for(int i = 0; i < 10; i++) {
			Log.v("TEST", "count: " + (i + 1));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		Log.v("TEST", "MySimpleService#onDestroy");
		super.onDestroy();
	}
}
