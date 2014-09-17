package com.example.servicesample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyTimerService extends Service {

	final static int INTERVAL_REIOD = 5000;
	Timer timer = new Timer();

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v("TEST", "MyTimerService#onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v("TEST", "MyTimerService#onStartCommand");
		//return super.onStartCommand(intent, flags, startId);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Log.v("TEST", "Hello!");
			}
		}, 0, INTERVAL_REIOD);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v("TEST", "MyTimerService#onDestroy");
		if(timer != null) {
			timer.cancel();
		}
	}


}
